package com.zero.ioc.entity02;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test02 {

    @Test
    public void test() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application02.json");
        Person person = (Person) jsonApplicationContext.getBean("person");
        String name = person.getName();
        assertEquals("小明", name);
        String res = person.hello("zero");
        System.out.println("res = " + res);
        assertEquals("小明 hello zero", res);
    }
}
