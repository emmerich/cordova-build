package com.github.emmerich.context;

import org.apache.maven.project.MavenProject;

import java.io.File;

public class PlatformContext {
    private String cordovaArtifactId;
    private File platformWorkingDirectory;
    private File platformLibDirectory;
    private File platformNativeDirectory;

    public PlatformContext(String cordovaArtifactId,
                           File platformWorkingDirectory,
                           File platformLibDirectory,
                           File platformNativeDirectory) {
        this.cordovaArtifactId = cordovaArtifactId;
        this.platformWorkingDirectory = platformWorkingDirectory;
        this.platformLibDirectory = platformLibDirectory;
        this.platformNativeDirectory = platformNativeDirectory;
    }

    public File getPlatformNativeDirectory() {
        return platformNativeDirectory;
    }

    public void setPlatformNativeDirectory(File platformNativeDirectory) {
        this.platformNativeDirectory = platformNativeDirectory;
    }

    public File getPlatformLibDirectory() {
        return platformLibDirectory;
    }

    public void setPlatformLibDirectory(File platformLibDirectory) {
        this.platformLibDirectory = platformLibDirectory;
    }

    public File getPlatformWorkingDirectory() {
        return platformWorkingDirectory;
    }

    public void setPlatformWorkingDirectory(File platformWorkingDirectory) {
        this.platformWorkingDirectory = platformWorkingDirectory;
    }

    public String getCordovaArtifactId() {
        return cordovaArtifactId;
    }

    public void setCordovaArtifactId(String cordovaArtifactId) {
        this.cordovaArtifactId = cordovaArtifactId;
    }
}
