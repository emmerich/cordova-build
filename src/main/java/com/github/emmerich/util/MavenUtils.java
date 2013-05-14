package com.github.emmerich.util;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;

public class MavenUtils {

    public static Artifact getDependencyByArtifactId(MavenProject project, String artifactId) {
        for(Object o : project.getArtifacts()) {
            Artifact a = (Artifact) o;

            if(a.getArtifactId().equals(artifactId)) {
                return a;
            }
        }

        return null;
    }

}
