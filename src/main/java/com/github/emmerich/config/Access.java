package com.github.emmerich.config;

import javax.xml.bind.annotation.XmlAttribute;

public class Access {
    private String origin;
    private boolean subdomains;
    private boolean browserOnly;

    public String getOrigin() {
        return origin;
    }

    @XmlAttribute(name = "origin", required = true)
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isSubdomains() {
        return subdomains;
    }

    @XmlAttribute(name = "subdomains", required = false)
    public void setSubdomains(boolean subdomains) {
        this.subdomains = subdomains;
    }

    public boolean isBrowserOnly() {
        return browserOnly;
    }

    @XmlAttribute(name = "browserOnly", required = false)
    public void setBrowserOnly(boolean browserOnly) {
        this.browserOnly = browserOnly;
    }
}
