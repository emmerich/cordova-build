package com.github.emmerich.util;

import com.github.emmerich.merger.*;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: shall
 * Date: 13/05/13
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public class MergerLookup {

    public static PlatformMerger getMergerForPlatform(MobilePlatform platform) throws MojoExecutionException {

        switch(platform) {
            case android:
                return new AndroidPlatformMerger();
            case blackberry:
                return new BlackberryPlatformMerger();
            case ios:
                return new IOSPlatformMerger();
            case symbian:
                return new SymbianPlatformMerger();
            case webos:
                return new WebOSPlatformMerger();
            case winphone:
                return new WindowsPhonePlatformMerger();
            default:
                throw new MojoExecutionException("Cannot find merger for platform " + platform);
        }

    }
}
