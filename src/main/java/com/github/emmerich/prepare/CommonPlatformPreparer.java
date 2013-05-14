package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileUtils;
import com.github.emmerich.util.MavenUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.Expand;

import java.io.File;
import java.io.IOException;

public abstract class CommonPlatformPreparer implements PlatformPreparer {

    @Override
    public void prepare(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        try {
            cleanApplicationDirectory(applicationContext, context);
            resolveCordovaDependency(applicationContext, context);
            buildNativeProject(applicationContext, context);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanApplicationDirectory(ApplicationContext applicationContext, PlatformContext context) {
        try {
            FileUtils.deleteDirectory(context.getPlatformNativeDirectory());
        } catch(IOException e) {
            // Might be the first time we run so don't do anything here.
        }
    }

    @Override
    public void resolveCordovaDependency(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        Artifact dependency = MavenUtils.getDependencyByArtifactId(applicationContext.getProject(), context.getCordovaArtifactId());

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
            context.setPlatformLibDirectory(FileUtils.getFile(context.getPlatformLibDirectory(), FileUtils.stripFileExtension(dependencyZip.getName())));
        }
    }
}
