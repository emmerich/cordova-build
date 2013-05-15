package com.github.emmerich.cordovabuild.util;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileEditorImpl implements FileEditor {

    private File file;
    private boolean open;

    private ArrayList<String> lines;

    public FileEditorImpl(File file) {
        this.file = file;
        this.open = false;
        this.lines = new ArrayList<String>();
    }

    public void open() throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } finally {
            if(reader != null) {
                reader.close();
                open = true;
            }
        }
    }

    public void flush() throws IOException {
        FileWriter writer = new FileWriter(file);

        for(String s : lines) {
            writer.write(String.format("%1s%n", s));
        }

        writer.close();
    }

    public void insertBefore(String expression, String insertion) throws IOException {
        if(this.open) {
            Pattern pattern = Pattern.compile(expression);

            for(int i = 0; i<lines.size(); i++) {
                if(pattern.matcher(lines.get(i)).find()) {
                    lines.add(i, insertion);
                    return;
                }
            }
        } else {
            throw new IOException("Attempted to insert into a FileWriter that hasn't been opened");
        }
    }

    public void replace(String before, String after) throws IOException {
        if(this.open) {
            Pattern pattern = Pattern.compile(before);

            for(int i = 0; i<lines.size(); i++) {
                if(pattern.matcher(lines.get(i)).find()) {
                    lines.remove(i);
                    lines.add(i, after);
                    return;
                }
            }
        } else {
            throw new IOException("Attempted to call replace on a FileWriter that hasn't been opened");
        }
    }
}
