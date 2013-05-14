package com.github.emmerich.merger;

import com.github.emmerich.config.CordovaConfiguration;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class BlackberryPlatformMerger extends CommonPlatformMerger {
    @Override
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException {
        throw new MojoFailureException("Blackberry platform not yet supported.");
    }

    @Override
    public void copyIconsToNative() throws MojoFailureException, MojoExecutionException, IOException {

    }

    @Override
    public void copySplashesToNative() throws MojoFailureException, MojoExecutionException, IOException {

    }

    @Override
    public void copySourceToNative() throws MojoFailureException, MojoExecutionException, IOException {

    }

    @Override
    public void copyConfigToNative() throws MojoFailureException, MojoExecutionException, IOException {

    }

    @Override
    public void writeAccessDeclarations() throws MojoFailureException, MojoExecutionException, IOException, JAXBException {

    }

    @Override
    public void writeApplicationPermissions() throws MojoFailureException, MojoExecutionException, IOException, JDOMException {

    }
}
