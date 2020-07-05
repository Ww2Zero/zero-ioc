package com.zero.ioc.entity02;

import com.sun.tools.javac.util.Assert;
import com.zero.ioc.core.JsonApplicationContext;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application02.json");
        Person person = (Person) jsonApplicationContext.getBean("person");
        String name = person.getName();
        Assert.check("小明".equals(name));
        String res = person.hello("zero");
        System.out.println("res = " + res);
        Assert.check(res.equals("小明 hello zero"));
    }
}
