package com.github.emmerich.prepare;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileUtils;

import java.io.IOException;

public class AndroidPreparer extends CommonPlatformPreparer {

    @Override
    public void buildNativeProject(ApplicationContext applicationContext, PlatformContext context) throws IOException {
        String command = "cmd.exe /c " +
                FileUtils.getFile(context.getPlatformLibDirectory(), "bin", "create.bat").getAbsolutePath() +
                " " +
                context.getPlatformNativeDirectory().getAbsolutePath() +
                " " +
                applicationContext.getPackageName() +
                " " +
                applicationContext.getApplicationName();

        Process process = Runtime.getRuntime().exec(command);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
