package com.github.emmerich.util;

import java.io.File;

public interface FileEditorFactory {
    public FileEditor build(File file);
}
