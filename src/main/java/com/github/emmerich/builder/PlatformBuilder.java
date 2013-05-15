package com.github.emmerich.builder;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public interface PlatformBuilder {

    public void build(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;

    public void cleanBuildDir(ApplicationContext applicationContext, PlatformContext context) throws IOException;
    public void performBuild(ApplicationContext applicationContext, PlatformContext context);
    public void moveBinaries(ApplicationContext applicationContext, PlatformContext context) throws IOException;
}
