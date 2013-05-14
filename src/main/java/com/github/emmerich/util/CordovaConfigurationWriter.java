package com.github.emmerich.util;

import com.github.emmerich.config.cordova.CordovaConfiguration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class CordovaConfigurationWriter {

    private JAXBContext jaxbContext;

    public void write(CordovaConfiguration configuration, File destination) throws JAXBException {
        Marshaller marshaller = getJaxbContext().createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(configuration, destination);
    }

    private JAXBContext getJaxbContext() throws JAXBException {
        if(jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(CordovaConfiguration.class);
        }

        return jaxbContext;
    }
}
