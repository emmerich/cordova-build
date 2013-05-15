package com.github.emmerich.util;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: shall
 * Date: 14/05/13
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtils {

    public static Document getDocument(File file) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        return builder.build(file);
    }

    public static void write(Document document, File file) throws IOException {
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(document, new FileWriter(file));
    }


}
