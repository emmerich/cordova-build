package com.github.emmerich.cordovabuild.util;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;

public interface MavenHelper {
    public Artifact getDependencyByArtifactId(MavenProject project, String artifactId);
}
