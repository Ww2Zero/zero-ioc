package com.zero.ioc.test.entity.JDK;


public class Pig implements Hello {

    private String pigName;

    public Pig(String pigName) {
        this.pigName = pigName;
    }

    @Override
    public String hello(String name) {
        return pigName + "哈哈哈～" + name;
    }
}
