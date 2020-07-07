package com.zero.ioc.entity05;

public class AObject {

    private BObject bObject;
    private String name;

    public String hello(String str) {
        return "A+" + name + str;
    }

    public BObject getBObject() {
        return bObject;
    }

    public void setBObject(BObject bObject) {
        this.bObject = bObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
