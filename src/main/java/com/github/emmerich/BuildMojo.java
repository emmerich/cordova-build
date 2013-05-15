package com.github.emmerich;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.emmerich.builder.PlatformBuilder;
import com.github.emmerich.config.CordovaConfiguration;
import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.merger.PlatformMerger;
import com.github.emmerich.platform.MobilePlatform;
import com.github.emmerich.platform.PlatformLookup;
import com.github.emmerich.prepare.PlatformPreparer;
import com.github.emmerich.util.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import javax.xml.bind.*;
import java.io.File;

/**
 * Builds a Cordova project for each chosen platform, merges in the source from the WAR project and builds the
 * native application from the merged native project.
 *
 * @goal build
 * @phase package
 * @requiresDependencyResolution compile
 */
public class BuildMojo extends AbstractMojo {

    private static final String CORDOVA_VERSION = "2.5.0";
    /**
     * Cordova configuration file.
     *
     * @parameter expression="${basedir}/src/main/cordova-build/config.xml"
     */
    private File configFile;

    /**
     * Directory containing web application source.
     *
     * @parameter expression="${project.build.directory}/${project.build.finalName}"
     */
    private File explodedWarDir;

    /**
     * Directory in the target directory that the plugin can work within.
     *
     * @parameter expression="${project.build.directory}/cordova-build
     */
    public File pluginWorkingDir;

    /**
     * @parameter expression="${project}"
     */
    private MavenProject project;

    /**
     * The platforms to build a native application for.
     *
     * @parameter
     */
    private MobilePlatform[] platforms = new MobilePlatform[] {
            MobilePlatform.android,
            MobilePlatform.ios,
            MobilePlatform.blackberry,
            MobilePlatform.symbian,
            MobilePlatform.webos,
            MobilePlatform.winphone
    };

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        CordovaConfiguration config = null;

        try {
            // Parse the Cordova configuration XML into our Java object.
            JAXBContext jc = JAXBContext.newInstance(CordovaConfiguration.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            // Finding tags that don't exist on the model is fine. It means that the plugin hasn't yet implemented that
            // tag. However, for the ease of debugging it is best to report this to the user.
            unmarshaller.setEventHandler(new ValidationEventHandler() {
                @Override
                public boolean handleEvent(ValidationEvent event) {
                getLog().warn("Encountered an error when parsing your config.xml." +
                        " This may be due to a tag present in your config.xml that is not yet supported by the" +
                        " plugin. Please raise an issue if this is the case. The error message was: \n" +
                        event.getMessage() + "\n");
                return true;
                }
            });

            // Perform the unmarshal and set the filesystem source of the configuration.
            config = (CordovaConfiguration) unmarshaller.unmarshal(configFile);
            config.setSource(configFile);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new MojoExecutionException("There was an error parsing your config.xml");
        }

        ApplicationContext applicationContext =
                new ApplicationContext(project, config, explodedWarDir, CORDOVA_VERSION);

        // Iterate over each of the platforms specified by the user.
        for(MobilePlatform p : platforms) {
            File platformWorkingDir = FileUtils.getFile(pluginWorkingDir, p.toString());
            File platformLibDir = FileUtils.getFile(platformWorkingDir, "lib");
            File platformNativeDir = FileUtils.getFile(platformWorkingDir, "native");
            File platformBinDir = FileUtils.getFile(platformWorkingDir, "bin");

            PlatformContext context = new PlatformContext(
                    PlatformLookup.getCordovaArtifactId(p),
                    platformWorkingDir,
                    platformLibDir,
                    platformNativeDir,
                    platformBinDir);

            // Create the sample project from the Cordova library
            PlatformPreparer preparer = PlatformLookup.getPreparerForPlatform(p);
            preparer.prepare(applicationContext, context);

            // Merge the project's source code with the sample project
            PlatformMerger merger = PlatformLookup.getMergerForPlatform(p);
            merger.merge(applicationContext, context);

            // Build the sample project and place in the bin folder
            PlatformBuilder builder = PlatformLookup.getBuilderForPlatform(p);
            builder.build(applicationContext, context);
        }
    }
}
