package com.github.emmerich.context;

import java.io.File;

public class PlatformContext {
    private String cordovaArtifactId;
    private File platformWorkingDirectory;
    private File platformLibDirectory;
    private File platformNativeDirectory;
    private File platformBinDirectory;

    public PlatformContext(String cordovaArtifactId,
                           File platformWorkingDirectory,
                           File platformLibDirectory,
                           File platformNativeDirectory,
                           File platformBinDirectory) {
        this.cordovaArtifactId = cordovaArtifactId;
        this.platformWorkingDirectory = platformWorkingDirectory;
        this.platformLibDirectory = platformLibDirectory;
        this.platformNativeDirectory = platformNativeDirectory;
        this.platformBinDirectory = platformBinDirectory;
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

    public File getPlatformBinDirectory() {
        return platformBinDirectory;
    }

    public void setPlatformBinDirectory(File platformBinDirectory) {
        this.platformBinDirectory = platformBinDirectory;
    }
}
