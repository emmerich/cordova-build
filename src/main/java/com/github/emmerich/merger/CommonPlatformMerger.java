package com.github.emmerich.merger;

import com.github.emmerich.config.CordovaConfiguration;
import com.github.emmerich.util.CordovaConfigurationWriter;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jdom.JDOMException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public abstract class CommonPlatformMerger implements PlatformMerger {

    protected File sourceDir;
    protected File nativeAppDir;
    protected CordovaConfiguration configurationFile;

    protected CordovaConfigurationWriter configurationFileWriter;

    @Override
    public void perform(File sourceDir, File nativeAppDir, CordovaConfiguration configFile) throws MojoFailureException, MojoExecutionException {
        this.sourceDir = sourceDir;
        this.nativeAppDir = nativeAppDir;
        this.configurationFile = configFile;

        this.configurationFileWriter = new CordovaConfigurationWriter();

        try {
            copyIconsToNative();
            copySplashesToNative();

            copySourceToNative();
            copyConfigToNative();

            writeAccessDeclarations();
            writeApplicationPermissions();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
