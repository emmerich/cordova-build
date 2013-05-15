package com.github.emmerich.util;

import org.jdom.Document;
import org.jdom.JDOMException;

import java.io.File;
import java.io.IOException;

public interface XMLHelper {
    public Document getDocument(File file) throws IOException, JDOMException;
    public void write(Document document, File file) throws IOException;
}
