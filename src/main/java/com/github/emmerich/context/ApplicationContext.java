package com.github.emmerich.context;

import com.github.emmerich.config.cordova.CordovaConfiguration;
import org.apache.maven.project.MavenProject;

import java.io.File;

public class ApplicationContext {
    private MavenProject project;
    private CordovaConfiguration cordovaConfiguration;
    private File sourceDir;
    private String cordovaVersion;

    public ApplicationContext(MavenProject project, CordovaConfiguration cordovaConfiguration, File sourceDir) {
        this.project = project;
        this.cordovaConfiguration = cordovaConfiguration;
        this.sourceDir = sourceDir;
        cordovaVersion = "2.5.0";
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public CordovaConfiguration getCordovaConfiguration() {
        return cordovaConfiguration;
    }

    public void setCordovaConfiguration(CordovaConfiguration cordovaConfiguration) {
        this.cordovaConfiguration = cordovaConfiguration;
    }

    public String getPackageName() {
        return project.getGroupId();
    }

    public String getApplicationName() {
        return cordovaConfiguration.getName();
    }

    public File getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(File sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String getCordovaVersion() {
        return cordovaVersion;
    }
}
