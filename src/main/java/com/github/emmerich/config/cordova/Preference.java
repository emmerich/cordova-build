package com.github.emmerich.config.cordova;

import javax.xml.bind.annotation.XmlAttribute;

public class Preference {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name", required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @XmlAttribute(name = "value", required = true)
    public void setValue(String value) {
        this.value = value;
    }
}
