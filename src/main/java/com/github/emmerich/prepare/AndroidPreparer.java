package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.SystemUtils;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public class AndroidPreparer extends CommonPreparer {

    @Override
    protected void buildNativeProject(ApplicationContext applicationContext, PlatformContext context) throws IOException, MojoExecutionException {
        String command = SystemUtils.getExecutable(
                // create is turned into create.bat or whatever necessary
                fileHelper.getFile(context.getPlatformLibDirectory(), "bin", "create").getAbsolutePath(),
                context.getPlatformNativeDirectory().getAbsolutePath(),
                applicationContext.getPackageName(),
                applicationContext.getApplicationName());

        Process process = Runtime.getRuntime().exec(command);

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void checkDependencies(ApplicationContext applicationContext, PlatformContext context) throws MojoExecutionException {
        /*try {
            String androidExec = SystemUtils.getExecutable("android", "--help");
            Process process = Runtime.getRuntime().exec(androidExec);
            if(process.waitFor() != 0) {
                throw new MojoExecutionException("No Android executable was found on your PATH.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            String antExec = SystemUtils.getExecutable("ant", "-help");
            Process process = Runtime.getRuntime().exec(antExec);
            if(process.waitFor() != 0) {
                throw new MojoExecutionException("No Ant executable was found on your PATH.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
