package com.github.emmerich.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: shall
 * Date: 13/05/13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class NullIntegerAdapter extends XmlAdapter<String, Integer> {

    @Override
    public Integer unmarshal(String v) throws Exception {
        System.out.println("Unmarshalling integer from string " + v);
        if(v.equalsIgnoreCase("null")) {
            return null;
        }

        return Integer.valueOf(v);
    }

    @Override
    public String marshal(Integer v) throws Exception {
        System.out.println("Turning integer " + v + " into String");
        if(v == null) {
            return null;
        }

        return v.toString();
    }
}
