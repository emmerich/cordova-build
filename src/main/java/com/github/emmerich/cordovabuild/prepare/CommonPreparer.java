package com.github.emmerich.cordovabuild.prepare;

import com.github.emmerich.cordovabuild.context.ApplicationContext;
import com.github.emmerich.cordovabuild.context.PlatformContext;
import com.github.emmerich.cordovabuild.util.FileHelper;
import com.github.emmerich.cordovabuild.util.MavenHelper;
import com.github.emmerich.cordovabuild.util.SystemHelper;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.Expand;

import java.io.File;
import java.io.IOException;

public abstract class CommonPreparer implements PlatformPreparer {

    protected FileHelper fileHelper;
    protected MavenHelper mavenHelper;
    protected SystemHelper systemHelper;

    @Override
    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        try {
            checkDependencies(applicationContext, context);
            cleanApplicationDirectory(applicationContext, context);
            resolveCordovaDependency(applicationContext, context);
            buildNativeProject(applicationContext, context);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void cleanApplicationDirectory(ApplicationContext applicationContext, PlatformContext context) {
        try {
            fileHelper.deleteDirectory(context.getPlatformNativeDirectory());
        } catch(IOException e) {
            // Might be the first time we run so don't do anything here.
        }
    }

    private void resolveCordovaDependency(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        Artifact dependency = mavenHelper.getDependencyByArtifactId(applicationContext.getProject(), context.getCordovaArtifactId());

        if(dependency == null) {
            throw new MojoExecutionException("No artifact found for " + context.getCordovaArtifactId() + ". Did you correctly install it?");
        }

        File dependencyZip = dependency.getFile();
        Expand expand = new Expand();
        expand.setSrc(dependencyZip);
        expand.setDest(context.getPlatformLibDirectory());
        expand.setOverwrite(true);
        try {
            expand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.setPlatformLibDirectory(fileHelper.getFile(context.getPlatformLibDirectory(), fileHelper.stripFileExtension(dependencyZip.getName())));
        }
    }

    @Override
    public void setFileHelper(FileHelper helper) {
        fileHelper = helper;
    }

    @Override
    public void setMavenHelper(MavenHelper helper) {
        mavenHelper = helper;
    }

    @Override
    public void setSystemHelper(SystemHelper helper) {
        systemHelper = helper;
    }

    protected abstract void buildNativeProject(ApplicationContext applicationContext, PlatformContext context) throws IOException, MojoExecutionException;
    protected abstract void checkDependencies(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException;
}
