package com.github.emmerich.util;

import org.apache.maven.plugin.MojoExecutionException;

public class SystemUtils extends org.apache.commons.lang3.SystemUtils {

    public static String getExecutable(String executable, String... args) throws MojoExecutionException {

        StringBuilder executableCommand = new StringBuilder();

        if(SystemUtils.IS_OS_WINDOWS) {
            executableCommand.append("cmd.exe /c ");
            executableCommand.append(executable);
            executableCommand.append(".bat");
        } else if(SystemUtils.IS_OS_UNIX) {
            // The signature of this method accepts Unix-friendly executables, so we don't need to do anything
            executableCommand.append(executable);
        } else {
            throw new MojoExecutionException("Your OS " + SystemUtils.OS_NAME + " is not recognized");
        }

        for(String arg : args) {
            executableCommand.append(" ");
            executableCommand.append(arg);
        }

        return executableCommand.toString();

    }
}
