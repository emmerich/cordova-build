package com.github.emmerich.merger;

import com.github.emmerich.config.*;
import com.github.emmerich.feature.AndroidPermissionMap;
import com.github.emmerich.feature.PermissionMap;
import com.github.emmerich.util.MobilePlatform;
import org.codehaus.plexus.util.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AndroidPlatformMerger implements PlatformMerger {

    private PermissionMap permissionMap;

    public AndroidPlatformMerger() {
        permissionMap = new AndroidPermissionMap();
    }

    @Override
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) {

        // Move any defined icons into the correct folders
        for(Drawable i : configFile.getIcons()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(i.getPlatform())) {
                copyDrawableToResourceDir(i, "icon", sourceDir, nativeAppDir);
            }
        }

        // Move splash screens
        for(Drawable s : configFile.getSplashes()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(s.getPlatform())) {
                copyDrawableToResourceDir(s, "splash", sourceDir, nativeAppDir);
            }
        }

        // Copy over the src to the assets folder
        String assetsDir = nativeAppDir.getAbsolutePath() + File.separator + "assets" + File.separator + "www";
        String tempAssetsDir = assetsDir + "-temp";

        File configXMLLoc = new File(nativeAppDir.getAbsolutePath() + File.separator + "res" + File.separator + "xml");

        try {
            FileUtils.mkdir(tempAssetsDir);

            File tempAssetsFile = new File(tempAssetsDir);
            File assetsFile = new File(assetsDir);

            // Copy the Cordova JS into the assets www folder
            // TODO(emmerich): Derive cordova version
            FileUtils.copyFileToDirectory(new File(assetsDir + File.separator + "cordova-2.5.0.js"), tempAssetsFile);
            FileUtils.deleteDirectory(assetsDir);
            FileUtils.rename(tempAssetsFile, assetsFile);

            FileUtils.copyDirectoryStructure(sourceDir, assetsFile);

            File defaultConfigFile = new File(nativeAppDir.getAbsolutePath() + File.separator + "res" + File.separator + "xml" + File.separator + "config.xml");
            FileUtils.forceDelete(defaultConfigFile);

            // Copy the config XML into the res/xml folder
            FileUtils.copyFileToDirectory(configFile.getSource(), configXMLLoc);
        } catch(IOException e) {
            e.printStackTrace();
        }

        // PhoneGap build sets a default access to all origins, do that here
        // see: https://build.phonegap.com/blog/access-tags
        // TODO(shall): PhoneGap don't do the default thing anymore, they have made it slightly
        // more complicated. Update this.
        ArrayList<Access> accesses = configFile.getAccesses();

        if(accesses == null) {
            accesses = new ArrayList<Access>();
        }

        // Only add the default access if the user hasn't specified any
        if(accesses.size() <= 0) {
            Access defaultAccess = new Access();
            defaultAccess.setOrigin("*");
            accesses.add(defaultAccess);
        }

        configFile.setAccesses(accesses);

        try {
            JAXBContext jc = JAXBContext.newInstance(CordovaConfiguration.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(configFile, new File(configXMLLoc.getAbsolutePath() + File.separator + "config.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        // Rewrite the application's permissions based on the features defined in the config xml
        // First remove the permissions made by the default application (which is all)
        try {
            File manifestFile = new File(nativeAppDir.getAbsolutePath() + File.separator + "AndroidManifest.xml");
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(manifestFile);
            Element manifestNode = document.getRootElement();
            manifestNode.removeChildren("uses-permission");

            // PhoneGap allows the use of a permissions preference which, if set to none, will remove all permissions
            // from the application (even the defaults)
            Preference permissionsPreference = configFile.getPreferenceByName("permissions");
            if(permissionsPreference == null || permissionsPreference.getValue() != "none") {
                for(Feature f : configFile.getFeatures()) {
                    for(String permission : permissionMap.getPermissionsForFeature(f)) {
                        Element permissionEl = new Element("uses-permission");
                        permissionEl.setAttribute("name", permission, Namespace.getNamespace("android", "http://schemas.android.com/apk/res/android"));
                        manifestNode.addContent(permissionEl);
                    }
                }

                for(String s : permissionMap.getDefaultPermissions()) {
                    Element permissionEl = new Element("uses-permission");
                    permissionEl.setAttribute("name", s, Namespace.getNamespace("android", "http://schemas.android.com/apk/res/android"));
                    manifestNode.addContent(permissionEl);
                }
            }

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, new FileWriter(manifestFile));
        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void copyDrawableToResourceDir(Drawable drawable, String name, File sourceDir, File rootDir) {
            File drawableFile = new File(sourceDir.getAbsolutePath() + File.separator + drawable.getSrc());
            String extension = drawable.getSrc().split("\\.")[drawable.getSrc().split("\\.").length - 1];
            File destinationFile = new File(rootDir + File.separator + "res" + File.separator + "drawable-" + drawable.getDensity() + File.separator + name + "." + extension);

            try {
                FileUtils.copyFile(drawableFile, destinationFile);
                //FileUtils.copyFileToDirectory(drawableFile, destinationDir);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
    }
}
