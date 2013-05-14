package com.github.emmerich.prepare;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

public interface PlatformPreparer {

    public void prepare(File workingDirectory, MavenProject project) throws MojoExecutionException;

    public void cleanApplicationDirectory();
    public void resolveCordovaDependency() throws MojoExecutionException;
    public void buildNativeProject() throws IOException;

    String getCordovaArtifactId();
}
