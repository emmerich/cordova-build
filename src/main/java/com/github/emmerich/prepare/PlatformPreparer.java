package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public interface PlatformPreparer {

    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;

    public void cleanApplicationDirectory(ApplicationContext applicationContext, PlatformContext context);
    public void resolveCordovaDependency(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;
    public void buildNativeProject(ApplicationContext applicationContext, PlatformContext context) throws IOException;
}
