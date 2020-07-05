package com.zero.ioc.entity03;

import com.sun.tools.javac.util.Assert;
import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Test03 {

    @Test
    public void test() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {

        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application03.json");
        Person person = (Person) jsonApplicationContext.getBean("xiaoming");
        String name = person.getName();
        String phoneName = person.getPhone().getPhoneName();
        Assert.check("小明".equals(name));
        Assert.check("huawei".equals(phoneName));
        String res = person.hello("zero");
        System.out.println("res = " + res);
        Assert.check(res.equals("小明使用huawei给zero打电话"));
    }

}
