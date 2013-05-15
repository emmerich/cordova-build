package com.github.emmerich.builder;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public abstract class CommonBuilder implements PlatformBuilder {

    @Override
    public void build(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        try {
            cleanBuildDir(applicationContext, context);
            performBuild(applicationContext, context);
            moveBinaries(applicationContext, context);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void cleanBuildDir(ApplicationContext applicationContext, PlatformContext context) {
        try {
            FileUtils.deleteDirectory(context.getPlatformBinDirectory());
            FileUtils.mkdir(context.getPlatformBinDirectory().getAbsolutePath());
        } catch (IOException e) {
            // Directory might not have existed
        }
    }

    protected abstract void performBuild(ApplicationContext applicationContext, PlatformContext context);
    protected abstract void moveBinaries(ApplicationContext applicationContext, PlatformContext context) throws IOException;
}
