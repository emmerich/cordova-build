package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileHelper;
import com.github.emmerich.util.MavenHelper;
import com.github.emmerich.util.SystemHelper;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public interface PlatformPreparer {
    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;
    public void setFileHelper(FileHelper helper);
    public void setMavenHelper(MavenHelper helper);
    public void setSystemHelper(SystemHelper helper);
}
