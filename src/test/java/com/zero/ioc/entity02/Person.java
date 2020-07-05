package com.zero.ioc.entity02;

public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String hello(String str) {
        return name + " hello " + str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
