package com.github.emmerich.merger;


import com.github.emmerich.config.CordovaConfiguration;
import com.github.emmerich.config.Drawable;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PlatformMerger {
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException, MojoExecutionException;

    public void copyIconsToNative() throws MojoFailureException, MojoExecutionException, IOException;
    public void copySplashesToNative() throws MojoFailureException, MojoExecutionException, IOException;

    public void copySourceToNative() throws MojoFailureException, MojoExecutionException, IOException;
    public void copyConfigToNative() throws MojoFailureException, MojoExecutionException, IOException;

    public void writeAccessDeclarations() throws MojoFailureException, MojoExecutionException, IOException, JAXBException;
    public void writeApplicationPermissions() throws MojoFailureException, MojoExecutionException, IOException, JDOMException;
}
