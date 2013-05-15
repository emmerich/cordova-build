package com.github.emmerich.cordovabuild.util;

import org.apache.maven.plugin.MojoExecutionException;

public interface SystemHelper {
    public String getExecutable(String executable, String... args) throws MojoExecutionException;
    public boolean isOsWindows();
    public boolean isOsLinux();
    public String getOsName();
}
