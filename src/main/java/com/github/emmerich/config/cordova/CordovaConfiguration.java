package com.github.emmerich.config.cordova;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.util.ArrayList;

@XmlRootElement(name = "widget", namespace = "http://www.w3.org/ns/widgets")
public class CordovaConfiguration {
    public static final String PHONEGAP_NAMESPACE = "http://phonegap.com/ns/1.0";

    private File source;

    private String name;
    private ArrayList<Drawable> icons;
    private ArrayList<Drawable> splashes;
    private ArrayList<Feature> features;
    private ArrayList<Preference> preferences;
    private ArrayList<Access> accesses;

    public Preference getPreferenceByName(String name) {
        for(Preference p : preferences) {
            if(p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Drawable> getIcons() {
        return icons;
    }

    @XmlElement(name = "icon")
    public void setIcons(ArrayList<Drawable> drawables) {
        this.icons = drawables;
    }

    public ArrayList<Drawable> getSplashes() {
        return splashes;
    }

    @XmlElement(name = "splash", namespace=PHONEGAP_NAMESPACE)
    public void setSplashes(ArrayList<Drawable> splashes) {
        this.splashes = splashes;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    @XmlElement(name = "feature")
    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public ArrayList<Preference> getPreferences() {
        return preferences;
    }

    @XmlElement(name = "preference")
    public void setPreferences(ArrayList<Preference> preferences) {
        this.preferences = preferences;
    }

    public ArrayList<Access> getAccesses() {
        return accesses;
    }

    @XmlElement(name = "access")
    public void setAccesses(ArrayList<Access> accesses) {
        this.accesses = accesses;
    }

    public void setSource(File source) {
        this.source = source;
    }

    @XmlTransient
    public File getSource() {
        return source;
    }

}
