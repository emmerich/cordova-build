package com.github.emmerich.merger;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.CordovaConfigurationWriter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public abstract class CommonPlatformMerger implements PlatformMerger {

    protected CordovaConfigurationWriter configurationFileWriter;

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
}
