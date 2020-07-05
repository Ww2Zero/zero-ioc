package com.zero.ioc.entity01;

import com.sun.tools.javac.util.Assert;
import com.zero.ioc.core.JsonApplicationContext;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application01.json");
        Hello hello = (Hello) jsonApplicationContext.getBean("hello");
        String res = hello.hello("zero");
        System.out.println("res = " + res);
        Assert.check(res.equals("hello zero"));
    }
}