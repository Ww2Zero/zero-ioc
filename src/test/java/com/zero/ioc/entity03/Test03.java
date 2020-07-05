package com.zero.ioc.entity03;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test03 {

    @Test
    public void test() throws Exception {

        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application03.json");
        Person person = (Person) jsonApplicationContext.getBean("xiaoming");
        String name = person.getName();
        String phoneName = person.getPhone().getPhoneName();
        assertEquals("小明", name);
        assertEquals("huawei", phoneName);
        String res = person.hello("zero");
        System.out.println("res = " + res);
        assertEquals("小明使用huawei给zero打电话", res);
    }

}
