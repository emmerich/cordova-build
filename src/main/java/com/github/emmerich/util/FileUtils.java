package com.github.emmerich.util;

import java.io.File;

public class FileUtils extends org.codehaus.plexus.util.FileUtils {

    public static File getFile(String root, String... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(root);

        for(String arg : args) {
            builder.append(File.separator);
            builder.append(arg);
        }

        return new File(builder.toString());
    }
}
