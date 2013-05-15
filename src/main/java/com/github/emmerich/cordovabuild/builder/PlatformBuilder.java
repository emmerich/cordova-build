package com.github.emmerich.cordovabuild.builder;

import com.github.emmerich.cordovabuild.context.ApplicationContext;
import com.github.emmerich.cordovabuild.context.PlatformContext;
import com.github.emmerich.cordovabuild.util.FileHelper;
import org.apache.maven.plugin.MojoExecutionException;

public interface PlatformBuilder {
    public void build(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;
    public void setFileHelper(FileHelper helper);
}
