package com.github.emmerich.merger;

import com.github.emmerich.config.CordovaConfiguration;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

public class WindowsPhonePlatformMerger implements PlatformMerger {

    // TODO(shall): WindowsPhone requires a feature map similar to android
    @Override
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException {
        throw new MojoFailureException("Blackberry platform not yet supported.");
    }
}
