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

import com.github.emmerich.config.Access;
import com.github.emmerich.config.CordovaConfiguration;
import com.github.emmerich.merger.PlatformMerger;
import com.github.emmerich.util.MergerLookup;
import com.github.emmerich.util.MobilePlatform;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds a Cordova project for each chosen platform, merges in the source from the WAR project and builds the
 * native application from the merged native project.
 *
 * @goal build
 * @phase package
 * @requiresDependencyResolution compile
 */
public class BuildMojo extends AbstractMojo {
    private static final String NATIVE_APP_DIR = "native";

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
    private File pluginWorkingDir;

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
            JAXBContext jc = JAXBContext.newInstance(CordovaConfiguration.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            config = (CordovaConfiguration) unmarshaller.unmarshal(configFile);
            config.setSource(configFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for(MobilePlatform p : platforms) {
            PlatformMerger merger = MergerLookup.getMergerForPlatform(p);
            File workingDir = new File(pluginWorkingDir.getAbsolutePath() + File.separator + p + File.separator + NATIVE_APP_DIR);

            getLog().info("Performing on " + workingDir.getAbsolutePath());
            merger.perform(explodedWarDir, workingDir, config);
        }
    }
}
