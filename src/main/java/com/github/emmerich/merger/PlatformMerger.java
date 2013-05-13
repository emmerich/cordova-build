package com.github.emmerich.merger;


import com.github.emmerich.config.CordovaConfiguration;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

public interface PlatformMerger {
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException, MojoExecutionException;
}
