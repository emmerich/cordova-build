package com.github.emmerich.cordovabuild.util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: shall
 * Date: 15/05/13
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class FileEditorFactoryImpl implements FileEditorFactory {

    @Override
    public FileEditor build(File file) {
        return new FileEditorImpl(file);
    }
}
