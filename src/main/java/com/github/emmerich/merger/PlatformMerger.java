package com.github.emmerich.merger;


import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public interface PlatformMerger {
    public void merge(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;
}
