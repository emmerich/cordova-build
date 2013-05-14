package com.github.emmerich.util;

import java.io.File;
import java.io.IOException;

public class FileWriter {

    private File file;
    private boolean open;

    public FileWriter(File file) {
        this.file = file;
        this.open = false;
    }

    public void open() {
        this.open = true;
    }

    public void insertInto(String line, String expression) throws IOException {
        if(this.open) {

        } else {
            throw new IOException("Attempted to insert into a FileWriter that hasn't been opened");
        }
    }
}
