package com.github.emmerich.prepare;

import com.github.emmerich.util.FileUtils;
import com.github.emmerich.util.MavenUtils;
import com.github.emmerich.util.PluginPaths;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.Expand;

import java.io.File;
import java.io.IOException;

public abstract class CommonPlatformPreparer implements PlatformPreparer {

    protected File workingDirectory;
    protected MavenProject project;
    protected File libDirectory;

    @Override
    public void prepare(File workingDirectory, MavenProject project) throws MojoExecutionException {
        this.workingDirectory = workingDirectory;
        this.project = project;

        System.out.println("Working with project: " + project);

        try {
            cleanApplicationDirectory();
            resolveCordovaDependency();
            buildNativeProject();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanApplicationDirectory() {
        try {
            FileUtils.deleteDirectory(FileUtils.getFile(workingDirectory.getAbsolutePath(), PluginPaths.NATIVE_APP_DIR));
        } catch(IOException e) {
            // Might be the first time we run so don't do anything here.
        }
    }

    @Override
    public void resolveCordovaDependency() throws MojoExecutionException {
        // Get the dependency
        Artifact dependency = MavenUtils.getDependencyByArtifactId(project, getCordovaArtifactId());

        if(dependency == null) {
            throw new MojoExecutionException("No artifact found for " + getCordovaArtifactId() + ". Did you correctly install it?");
        }

        File dependencyZip = dependency.getFile();
        Expand expand = new Expand();
        expand.setSrc(dependencyZip);
        expand.setDest(FileUtils.getFile(workingDirectory.getAbsolutePath(), PluginPaths.LIB_DIR));
        expand.setOverwrite(true);
        try {
            expand.execute();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            System.out.println("The name of the file is " + FileUtils.stripFileExtension(dependencyZip.getName()));
            libDirectory = FileUtils.getFile(workingDirectory.getAbsolutePath(), PluginPaths.LIB_DIR, FileUtils.stripFileExtension(dependencyZip.getName()));
        }
    }
}
