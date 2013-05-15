package com.github.emmerich.util;

import org.apache.maven.plugin.MojoExecutionException;

public interface SystemHelper {
    public String getExecutable(String executable, String... args) throws MojoExecutionException;
}
