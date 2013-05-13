package com.github.emmerich.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Drawable {
    private String src;
    private String platform;
    private String density;
    private Integer width;
    private Integer height;

    public String getSrc() {
        return src;
    }

    @XmlAttribute(name = "src", required = true)
    public void setSrc(String src) {
        this.src = src;
    }

    public String getPlatform() {
        return platform;
    }

    @XmlAttribute(name = "platform", namespace=CordovaConfiguration.PHONEGAP_NAMESPACE, required = true)
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDensity() {
        return density;
    }

    @XmlAttribute(name = "density", namespace=CordovaConfiguration.PHONEGAP_NAMESPACE, required = false)
    public void setDensity(String density) {
        this.density = density;
    }

    public Integer getWidth() {
        return width;
    }

    @XmlAttribute(name = "width", required = false)
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    @XmlAttribute(name = "height", required = false)
    public void setHeight(Integer height) {
        this.height = height;
    }
}
