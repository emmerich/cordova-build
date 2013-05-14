package com.github.emmerich.merger;

import com.github.emmerich.config.*;
import com.github.emmerich.feature.AndroidPermissionMap;
import com.github.emmerich.feature.PermissionMap;
import com.github.emmerich.util.FileEditor;
import com.github.emmerich.util.FileUtils;
import com.github.emmerich.util.MobilePlatform;
import com.github.emmerich.util.XMLUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AndroidMerger extends CommonPlatformMerger {

    private static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";

    private PermissionMap permissionMap;

    public AndroidMerger() {
        permissionMap = new AndroidPermissionMap();
    }

    @Override
    public void copyIconsToNative() throws IOException {
        for(Drawable i : configurationFile.getIcons()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(i.getPlatform())) {
                copyDrawableToResourceDir(i, "icon", sourceDir, nativeAppDir);
            }
        }
    }

    @Override
    public void copySplashesToNative() throws IOException {
        for(Drawable s : configurationFile.getSplashes()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(s.getPlatform())) {
                copyDrawableToResourceDir(s, "splash", sourceDir, nativeAppDir);
            }
        }

        if(configurationFile.getSplashes().size() > 0) {

            // Splash screen also needs to be included in the main Activity.
            File mainActivityFile = FileUtils.getFile(nativeAppDir.getAbsolutePath(), "src", "com", "github", "chrisprice", "CordovaBuildExample.java");
            FileEditor mainActivityWriter = new FileEditor(mainActivityFile);
            mainActivityWriter.open();
            mainActivityWriter.insertBefore(
                    "(super\\.loadUrl\\(Config.getStartUrl\\(\\)\\);)",
                    "super.setIntegerProperty(\"splashscreen\", R.drawable.splash);"
            );

            mainActivityWriter.replace(
                "super\\.loadUrl\\(Config.getStartUrl\\(\\)\\);",
                "super.loadUrl(Config.getStartUrl(), 5000);"
            );

            mainActivityWriter.flush();

        }
    }

    @Override
    public void copySourceToNative() throws IOException {
        // Copy over the src to the assets folder
        File assetsDir = FileUtils.getFile(nativeAppDir.getAbsolutePath(), "assets", "www");
        String tempAssetsDir = assetsDir.getAbsolutePath() + "-temp";

        FileUtils.mkdir(tempAssetsDir);

        File tempAssetsFile = new File(tempAssetsDir);

        // Copy the Cordova JS into the assets www folder
        // TODO(emmerich): Derive cordova version
        FileUtils.copyFileToDirectory(FileUtils.getFile(assetsDir.getAbsolutePath(), "cordova-2.5.0.js"), tempAssetsFile);
        FileUtils.deleteDirectory(assetsDir);
        FileUtils.rename(tempAssetsFile, assetsDir);

        FileUtils.copyDirectoryStructure(sourceDir, assetsDir);

        File defaultConfigFile = FileUtils.getFile(nativeAppDir.getAbsolutePath(), "res", "xml", "config.xml");
        FileUtils.forceDelete(defaultConfigFile);
    }

    @Override
    public void copyConfigToNative() throws IOException {
        File configXMLLoc = FileUtils.getFile(nativeAppDir.getAbsolutePath(), "res", "xml");
        FileUtils.copyFileToDirectory(configurationFile.getSource(), configXMLLoc);
    }

    @Override
    public void writeAccessDeclarations() throws JAXBException {
        // PhoneGap build sets a default access to all origins, do that here
        // see: https://build.phonegap.com/blog/access-tags
        // TODO(shall): PhoneGap don't do the default thing anymore, they have made it slightly
        // more complicated. Update this.
        ArrayList<Access> accesses = configurationFile.getAccesses();

        if(accesses == null) {
            accesses = new ArrayList<Access>();
        }

        // Only add the default access if the user hasn't specified any
        if(accesses.size() <= 0) {
            Access defaultAccess = new Access();
            defaultAccess.setOrigin("*");
            accesses.add(defaultAccess);
        }

        configurationFile.setAccesses(accesses);
        configurationFileWriter.write(configurationFile, FileUtils.getFile(nativeAppDir.getAbsolutePath(), "res", "xml", "config.xml"));
    }

    @Override
    public void writeApplicationPermissions() throws IOException, JDOMException {
        File manifestFile = FileUtils.getFile(nativeAppDir.getAbsolutePath(), "AndroidManifest.xml");
        Document applicationManifest = XMLUtils.getDocument(manifestFile);
        Element applicationManifestRoot = applicationManifest.getRootElement();
        Preference permissionsPreference = configurationFile.getPreferenceByName("permissions");

        // First remove the permissions made by the default application (which is all)
        if(permissionsPreference != null && permissionsPreference.getValue().equalsIgnoreCase("none")) {
            applicationManifestRoot.removeChildren("uses-permission");

            // PhoneGap allows the use of a permissions preference which, if set to none, will remove all permissions
            // from the application (even the defaults)
            for(Feature f : configurationFile.getFeatures()) {
                for(String permission : permissionMap.getPermissionsForFeature(f)) {
                    applicationManifestRoot.addContent(buildPermissionElement(permission));
                }
            }

            for(String permission : permissionMap.getDefaultPermissions()) {
                applicationManifestRoot.addContent(buildPermissionElement(permission));
            }

            XMLUtils.write(applicationManifest, manifestFile);
        }
    }

    private Element buildPermissionElement(String permission) {
        Element permissionEl = new Element("uses-permission");
        permissionEl.setAttribute("name", permission, Namespace.getNamespace("android", ANDROID_NAMESPACE));
        return permissionEl;
    }

    private void copyDrawableToResourceDir(Drawable drawable, String name, File sourceDir, File rootDir) throws IOException {
        File drawableFile = FileUtils.getFile(sourceDir.getAbsolutePath(), drawable.getSrc());
        String extension = drawable.getSrc().split("\\.")[drawable.getSrc().split("\\.").length - 1];
        File destinationFile = FileUtils.getFile(rootDir.getAbsolutePath(), "res", "drawable-" + drawable.getDensity(), name + "." + extension);

        FileUtils.copyFile(drawableFile, destinationFile);
    }
}
