package com.github.emmerich.prepare;

import com.github.emmerich.util.FileUtils;
import com.github.emmerich.util.PluginPaths;

import java.io.IOException;

public class AndroidPreparer extends CommonPlatformPreparer {

    @Override
    public void buildNativeProject() throws IOException {
        /*<exec executable="cmd" dir="${cordova-build.android.lib}" failonerror="true">
        <arg line="/c ${cordova-build.android.lib}\bin\create.bat ${cordova-build.android.native} ${project.groupId} ${cordova-build.name}" />
        </exec>*/
        String command = "cmd.exe /c " +
                FileUtils.getFile(libDirectory.getAbsolutePath(), "bin", "create.bat").getAbsolutePath() +
                " " +
                FileUtils.getFile(workingDirectory.getAbsolutePath(), PluginPaths.NATIVE_APP_DIR).getAbsolutePath() +
                " " +
                project.getGroupId() +
                " " +
                project.getProperties().getProperty("cordova-build.name");

        System.out.println("Running command: " + command);
        Process process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public String getCordovaArtifactId() {
        return "cordova-android";
    }
}
