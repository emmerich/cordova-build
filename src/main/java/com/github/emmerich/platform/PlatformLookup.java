package com.github.emmerich.platform;

import com.github.emmerich.builder.AndroidBuilder;
import com.github.emmerich.builder.PlatformBuilder;
import com.github.emmerich.merger.AndroidMerger;
import com.github.emmerich.merger.PlatformMerger;
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

    public static PlatformBuilder getBuilderForPlatform(MobilePlatform p) throws MojoExecutionException {

        switch(p) {
            case android:
                return new AndroidBuilder();
            default:
                throw new MojoExecutionException("Cannot find builder for platform: " + p);
        }

    }
}
