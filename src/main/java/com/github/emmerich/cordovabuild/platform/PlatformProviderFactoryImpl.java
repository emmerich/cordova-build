package com.github.emmerich.cordovabuild.platform;

import com.github.emmerich.cordovabuild.builder.AndroidBuilder;
import com.github.emmerich.cordovabuild.merger.AndroidMerger;
import com.github.emmerich.cordovabuild.platform.permission.AndroidPermissionMap;
import com.github.emmerich.cordovabuild.platform.provider.AbstractPlatformProvider;
import com.github.emmerich.cordovabuild.platform.provider.PlatformProvider;
import com.github.emmerich.cordovabuild.prepare.AndroidPreparer;
import org.apache.maven.plugin.MojoExecutionException;

public class PlatformProviderFactoryImpl implements PlatformProviderFactory {

    private PlatformProvider androidProvider;

    public PlatformProviderFactoryImpl() {
        androidProvider = new AbstractPlatformProvider(
                new AndroidPreparer(),
                new AndroidMerger(),
                new AndroidBuilder(),
                new AndroidPermissionMap(),
                "cordova-android");
    }


    @Override
    public PlatformProvider getPlatformProvider(MobilePlatform platform) throws MojoExecutionException {
        switch(platform) {
            case android:
                return androidProvider;
            default:
                throw new MojoExecutionException("Platform not supported: " + platform);
        }
    }

}
