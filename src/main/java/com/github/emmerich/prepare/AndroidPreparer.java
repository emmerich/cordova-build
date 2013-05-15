package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public class AndroidPreparer extends CommonPreparer {

    @Override
    protected void buildNativeProject(ApplicationContext applicationContext, PlatformContext context) throws IOException, MojoExecutionException {

        StringBuilder executableCommand = new StringBuilder();

        if(SystemUtils.IS_OS_WINDOWS) {
            executableCommand.append("cmd.exe /c ");
            executableCommand.append(FileUtils.getFile(context.getPlatformLibDirectory(), "bin", "create.bat").getAbsolutePath());
        } else if(SystemUtils.IS_OS_UNIX) {
            executableCommand.append(FileUtils.getFile(context.getPlatformLibDirectory(), "bin", "create").getAbsolutePath());
        } else {
            throw new MojoExecutionException("Your OS " + SystemUtils.OS_NAME + " is not recognized");
        }

        executableCommand.append(" ");
        executableCommand.append(context.getPlatformNativeDirectory().getAbsolutePath());
        executableCommand.append(" ");
        executableCommand.append(applicationContext.getPackageName());
        executableCommand.append(" ");
        executableCommand.append(applicationContext.getApplicationName());

        Process process = Runtime.getRuntime().exec(executableCommand.toString());

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
