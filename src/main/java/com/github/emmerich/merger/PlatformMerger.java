package com.github.emmerich.merger;


import com.github.emmerich.config.cordova.CordovaConfiguration;
import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PlatformMerger {
    public void perform(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;

    public void copyIconsToNative(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException;
    public void copySplashesToNative(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException;

    public void copySourceToNative(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException;
    public void copyConfigToNative(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException;

    public void writeAccessDeclarations(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException, JAXBException;
    public void writeApplicationPermissions(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException, IOException, JDOMException;
}
