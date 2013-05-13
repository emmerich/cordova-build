package com.github.emmerich.merger;

import com.github.emmerich.config.CordovaConfiguration;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

public class WebOSPlatformMerger implements PlatformMerger {
    @Override
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException {
        throw new MojoFailureException("Blackberry platform not yet supported.");
    }
}
