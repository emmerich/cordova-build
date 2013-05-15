package com.github.emmerich.cordovabuild.util;

import java.io.*;

public interface FileEditor {
    public void open() throws IOException;
    public void flush() throws IOException;
    public void insertBefore(String expression, String insertion) throws IOException;
    public void replace(String before, String after) throws IOException;
}
