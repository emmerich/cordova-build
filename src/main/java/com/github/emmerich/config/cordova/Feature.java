package com.github.emmerich.config.cordova;

import javax.xml.bind.annotation.XmlAttribute;

public class Feature {
    private String name;

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name", required = true)
    public void setName(String name) {
        this.name = name;
    }
}
