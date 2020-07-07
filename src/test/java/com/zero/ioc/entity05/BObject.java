package com.zero.ioc.entity05;

public class BObject {

    private CObject cObject;
    private String name;

    public String hello(String str) {
        return "B+" + name + str;
    }

    public CObject getCObject() {
        return cObject;
    }

    public void setCObject(CObject cObject) {
        this.cObject = cObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
