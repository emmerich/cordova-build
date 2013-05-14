package com.github.emmerich.platform;

import com.github.emmerich.merger.*;
import com.github.emmerich.prepare.AndroidPreparer;
import com.github.emmerich.prepare.PlatformPreparer;
import org.apache.maven.plugin.MojoExecutionException;

public class PlatformLookup {

    public static PlatformMerger getMergerForPlatform(MobilePlatform platform) throws MojoExecutionException {

        switch(platform) {
            case android:
                return new AndroidMerger();
            default:
                throw new MojoExecutionException("Cannot find merger for platform: " + platform);
        }

    }

    public static PlatformPreparer getPreparerForPlatform(MobilePlatform platform) throws MojoExecutionException {

        switch(platform) {
            case android:
                return new AndroidPreparer();
            default:
                throw new MojoExecutionException("Cannot find preparer for platform: " + platform);
        }

    }

    public static String getCordovaArtifactId(MobilePlatform platform) throws MojoExecutionException {

        switch(platform) {
            case android:
                return "cordova-android";
            default:
                throw new MojoExecutionException("Cannot find Cordova Artifact ID for platform: " + platform);
        }

    }
}
