package com.github.emmerich.cordovabuild.util;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLHelperImpl implements XMLHelper {

    public Document getDocument(File file) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(file);
    }

    public void write(Document document, File file) throws IOException {
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, new FileWriter(file));
    }

}
