package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public interface PlatformPreparer {
    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;
}
