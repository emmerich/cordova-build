package com.github.emmerich.cordovabuild.util;

import org.apache.commons.lang3.SystemUtils;
import org.apache.maven.plugin.MojoExecutionException;

public class SystemHelperImpl implements SystemHelper {

    public String getExecutable(String executable, String... args) throws MojoExecutionException {
        StringBuilder executableCommand = new StringBuilder();

        if(isOsWindows()) {
            executableCommand.append("cmd.exe /c ");
            executableCommand.append(executable);
            executableCommand.append(".bat");
        } else if(isOsLinux()) {
            // The signature of this method accepts Unix-friendly executables, so we don't need to do anything
            executableCommand.append(executable);
        } else {
            throw new MojoExecutionException("Your OS " + getOsName() + " is not recognized");
        }

        for(String arg : args) {
            executableCommand.append(" ");
            executableCommand.append(arg);
        }

        return executableCommand.toString();
    }

    @Override
    public boolean isOsWindows() {
        return SystemUtils.IS_OS_WINDOWS;
    }

    @Override
    public boolean isOsLinux() {
        return SystemUtils.IS_OS_LINUX;
    }

    @Override
    public String getOsName() {
        return SystemUtils.OS_NAME;
    }
}
