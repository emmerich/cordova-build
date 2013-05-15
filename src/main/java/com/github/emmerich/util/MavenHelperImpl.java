package com.github.emmerich.util;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;

public class MavenHelperImpl implements MavenHelper {

    public Artifact getDependencyByArtifactId(MavenProject project, String artifactId) {
        for(Object o : project.getArtifacts()) {
            Artifact a = (Artifact) o;

            if(a.getArtifactId().equals(artifactId)) {
                return a;
            }
        }

        return null;
    }

}
