package com.github.emmerich.cordovabuild.util;

import java.io.File;

public interface FileEditorFactory {
    public FileEditor build(File file);
}
