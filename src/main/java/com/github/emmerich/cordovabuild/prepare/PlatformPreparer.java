package com.github.emmerich.cordovabuild.prepare;

import com.github.emmerich.cordovabuild.context.ApplicationContext;
import com.github.emmerich.cordovabuild.context.PlatformContext;
import com.github.emmerich.cordovabuild.util.FileHelper;
import com.github.emmerich.cordovabuild.util.MavenHelper;
import com.github.emmerich.cordovabuild.util.SystemHelper;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public interface PlatformPreparer {
    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;
    public void setFileHelper(FileHelper helper);
    public void setMavenHelper(MavenHelper helper);
    public void setSystemHelper(SystemHelper helper);
}
