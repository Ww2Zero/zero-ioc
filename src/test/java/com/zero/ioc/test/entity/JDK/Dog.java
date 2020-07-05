package com.zero.ioc.test.entity.JDK;

public class Dog implements Hello {

    @Override
    public String hello(String name) {
        return "汪汪汪～" + name;
    }
}
