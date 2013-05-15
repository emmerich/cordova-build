package com.github.emmerich.builder;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.codehaus.plexus.component.annotations.Requirement;

import java.io.File;
import java.io.IOException;

public class AndroidBuilder extends CommonBuilder {
    private static final String DEBUG_APK_SUFFIX = "-debug.apk";

    @Override
    public void performBuild(ApplicationContext applicationContext, PlatformContext context) {
        File buildFile = fileHelper.getFile(context.getPlatformNativeDirectory(), "build.xml");
        Project project = new Project();
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.setProperty("build.compiler", "extJavac");
        project.init();

        ProjectHelper helper = ProjectHelper.getProjectHelper();
        project.addReference("ant.projectHelper", helper);
        helper.parse(project, buildFile);

        project.executeTarget("debug");
    }

    @Override
    public void moveBinaries(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        fileHelper.copyFileToDirectory(
            fileHelper.getFile(context.getPlatformNativeDirectory(), "bin", applicationContext.getApplicationName() + DEBUG_APK_SUFFIX),
            context.getPlatformBinDirectory());
    }
}
