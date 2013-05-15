package com.github.emmerich.merger;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.CordovaConfigurationWriter;
import com.github.emmerich.util.FileHelper;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.component.annotations.Requirement;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public abstract class CommonMerger implements PlatformMerger {

    protected CordovaConfigurationWriter configurationFileWriter;
    protected FileHelper fileHelper;

    @Override
    public void merge(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException {
        this.configurationFileWriter = new CordovaConfigurationWriter();

        try {
            copyIconsToNative(applicationContext, context);
            copySplashesToNative(applicationContext, context);

            copySourceToNative(applicationContext, context);
            copyConfigToNative(applicationContext, context);

            writeAccessDeclarations(applicationContext, context);
            writeApplicationPermissions(applicationContext, context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFileHelper(FileHelper helper) {
        fileHelper = helper;
    }

    protected abstract void copyIconsToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException;
    protected abstract void copySplashesToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException;

    protected abstract void copySourceToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException;
    protected abstract void copyConfigToNative(ApplicationContext applicationContext, PlatformContext context) throws IOException;

    protected abstract void writeAccessDeclarations(ApplicationContext applicationContext, PlatformContext context) throws IOException, JAXBException;
    protected abstract void writeApplicationPermissions(ApplicationContext applicationContext, PlatformContext context) throws IOException, JDOMException;
}
