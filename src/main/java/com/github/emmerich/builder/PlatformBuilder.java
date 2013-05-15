package com.github.emmerich.builder;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileHelper;
import org.apache.maven.plugin.MojoExecutionException;

public interface PlatformBuilder {
    public void build(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;
    public void setFileHelper(FileHelper helper);
}
