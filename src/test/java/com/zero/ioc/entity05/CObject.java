package com.zero.ioc.entity05;


public class CObject {

    private AObject aObject;
    private String name;

    public String hello(String str) {
        return "C+" + name + str;
    }

    public AObject getAObject() {
        return aObject;
    }

    public void setAObject(AObject aObject) {
        this.aObject = aObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
