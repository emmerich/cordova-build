package com.github.emmerich.cordovabuild.merger;

import com.github.emmerich.cordovabuild.config.*;
import com.github.emmerich.cordovabuild.context.ApplicationContext;
import com.github.emmerich.cordovabuild.context.PlatformContext;
import com.github.emmerich.cordovabuild.platform.MobilePlatform;
import com.github.emmerich.cordovabuild.platform.permission.AndroidPermissionMap;
import com.github.emmerich.cordovabuild.platform.permission.PlatformPermissionMap;
import com.github.emmerich.cordovabuild.util.FileEditor;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AndroidMerger extends CommonMerger {

    private static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";

    private PlatformPermissionMap permissionMap;
    private File mainActivityFile;

    public AndroidMerger() {
        permissionMap = new AndroidPermissionMap();
    }

    @Override
    public void copyIconsToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        for(Drawable i : applicationContext.getCordovaConfiguration().getIcons()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(i.getPlatform())) {
                copyDrawableToResourceDir(i, "icon", applicationContext.getSourceDir(), context.getPlatformNativeDirectory());
            }
        }
    }

    @Override
    public void copySplashesToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        CordovaConfiguration configuration = applicationContext.getCordovaConfiguration();

        for(Drawable s : configuration.getSplashes()) {
            if(MobilePlatform.android.toString().equalsIgnoreCase(s.getPlatform())) {
                copyDrawableToResourceDir(s, "splash", applicationContext.getSourceDir(), context.getPlatformNativeDirectory());
            }
        }

        if(configuration.getSplashes().size() > 0) {
            File mainActivityFile = getMainActivityFile(applicationContext, context);
            FileEditor mainActivityWriter = fileEditorFactory.build(mainActivityFile);

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
    public void copySourceToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        // Copy over the src to the assets folder
        File assetsDir = fileHelper.getFile(context.getPlatformNativeDirectory(), "assets", "www");
        String tempAssetsDir = assetsDir.getAbsolutePath() + "-temp";

        fileHelper.mkdir(tempAssetsDir);

        File tempAssetsFile = new File(tempAssetsDir);

        // Copy the Cordova JS into the assets www folder
        fileHelper.copyFileToDirectory(
                fileHelper.getFile(assetsDir, "cordova-" + applicationContext.getCordovaVersion() + ".js"),
                tempAssetsFile);
        fileHelper.deleteDirectory(assetsDir);
        fileHelper.rename(tempAssetsFile, assetsDir);

        fileHelper.copyDirectoryStructure(applicationContext.getSourceDir(), assetsDir);

        File defaultConfigFile = fileHelper.getFile(context.getPlatformNativeDirectory(), "res", "xml", "config.xml");
        fileHelper.forceDelete(defaultConfigFile);
    }

    @Override
    public void copyConfigToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        File configXMLLoc = fileHelper.getFile(context.getPlatformNativeDirectory(), "res", "xml");
        fileHelper.copyFileToDirectory(applicationContext.getCordovaConfiguration().getSource(), configXMLLoc);
    }

    @Override
    public void writeAccessDeclarations(ApplicationContext applicationContext, PlatformContext context) throws JAXBException {
        CordovaConfiguration configuration = applicationContext.getCordovaConfiguration();

        // PhoneGap build sets a default access to all origins, do that here
        // see: https://build.phonegap.com/blog/access-tags
        // TODO(shall): PhoneGap don't do the default thing anymore, they have made it slightly
        // more complicated. Update this.
        ArrayList<Access> accesses = configuration.getAccesses();

        if(accesses == null) {
            accesses = new ArrayList<Access>();
        }

        // Only add the default access if the user hasn't specified any
        if(accesses.size() <= 0) {
            Access defaultAccess = new Access();
            defaultAccess.setOrigin("*");
            accesses.add(defaultAccess);
        }

        configuration.setAccesses(accesses);
        configurationFileWriter.write(configuration,
                fileHelper.getFile(context.getPlatformNativeDirectory(), "res", "xml", "config.xml"));
    }

    @Override
    public void writeApplicationPermissions(ApplicationContext applicationContext, PlatformContext context) throws IOException, JDOMException {
        CordovaConfiguration configuration = applicationContext.getCordovaConfiguration();

        File manifestFile = fileHelper.getFile(context.getPlatformNativeDirectory(), "AndroidManifest.xml");
        Document applicationManifest = xmlHelper.getDocument(manifestFile);
        Element applicationManifestRoot = applicationManifest.getRootElement();
        Preference permissionsPreference = configuration.getPreferenceByName("permissions");

        // First remove the permissions made by the default application (which is all)
        if(permissionsPreference != null && permissionsPreference.getValue().equalsIgnoreCase("none")) {
            applicationManifestRoot.removeChildren("uses-permission");

            // PhoneGap allows the use of a permissions preference which, if set to none, will remove all permissions
            // from the application (even the defaults)
            for(Feature f : configuration.getFeatures()) {
                for(String permission : permissionMap.getPermissionsForFeature(f)) {
                    applicationManifestRoot.addContent(buildPermissionElement(permission));
                }
            }

            for(String permission : permissionMap.getDefaultPermissions()) {
                applicationManifestRoot.addContent(buildPermissionElement(permission));
            }

            xmlHelper.write(applicationManifest, manifestFile);
        }
    }

    private Element buildPermissionElement(String permission) {
        Element permissionEl = new Element("uses-permission");
        permissionEl.setAttribute("name", permission, Namespace.getNamespace("android", ANDROID_NAMESPACE));
        return permissionEl;
    }

    private void copyDrawableToResourceDir(Drawable drawable, String name, File sourceDir, File rootDir) throws IOException {
        File drawableFile = fileHelper.getFile(sourceDir.getAbsolutePath(), drawable.getSrc());
        String extension = drawable.getSrc().split("\\.")[drawable.getSrc().split("\\.").length - 1];
        File destinationFile = fileHelper.getFile(rootDir.getAbsolutePath(), "res", "drawable-" + drawable.getDensity(), name + "." + extension);

        fileHelper.copyFile(drawableFile, destinationFile);
    }

    private File getMainActivityFile(ApplicationContext applicationContext, PlatformContext context) {
        if(mainActivityFile == null) {
            String[] packagesPath = applicationContext.getPackageName().split("\\.");
            String[] fullPath = new String[packagesPath.length + 2];
            fullPath[0] = "src";

            for(int i = 0; i<packagesPath.length; i++) {
                fullPath[i+1] = packagesPath[i];
            }

            fullPath[fullPath.length - 1] = applicationContext.getApplicationName() + ".java";
            mainActivityFile = fileHelper.getFile(context.getPlatformNativeDirectory(), fullPath);
        }

        return mainActivityFile;
    }
}
