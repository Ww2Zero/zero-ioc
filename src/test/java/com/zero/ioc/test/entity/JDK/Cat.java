package com.zero.ioc.test.entity.JDK;

public class Cat implements Hello {

    private String catName;

    public Cat() {
    }

    public Cat(String catName) {
        this.catName = catName;
    }

    @Override
    public String hello(String name) {
        return catName + ": 喵喵喵～" + name;
    }
}
