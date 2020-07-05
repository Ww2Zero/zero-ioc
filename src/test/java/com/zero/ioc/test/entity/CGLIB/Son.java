package com.zero.ioc.test.entity.CGLIB;

public class Son extends Person {

    @Override
    public String hello(String name) {
        return "Son" + "hello" + name;
    }
}
