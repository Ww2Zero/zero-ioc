package com.zero.ioc.entity02;

import com.zero.ioc.beans.exception.BeanCreationException;
import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test02 {

    @Test
    public void testConstructorArgList() {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application02.json");
        Person person = (Person) jsonApplicationContext.getBean("person");
        String name = person.getName();
        assertEquals("小明", name);
        String res = person.hello("zero");
        assertEquals("小明 hello zero", res);
    }


    /**
     * 构造函数的入参和目标的类构造函数入参名称不一样
     * 但是 构造函数的入参类型和目标的类的构造函数入参的类型一样  ==>> 可以构造成功
     */
    @Test
    public void testWithConstructorArgType() {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application02.json");
        Person person = (Person) jsonApplicationContext.getBean("person0x01");
        String name = person.getName();
        assertEquals("小明0x01", name);
        String res = person.hello("zero");
        assertEquals("小明0x01 hello zero", res);
    }

    @Test
    public void testErrorConstructorArgList() {
        try {
            JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application02.json");
            Person person = (Person) jsonApplicationContext.getBean("person0x02");
            String name = person.getName();
            assertEquals("小明0x02", name);
            String res = person.hello("zero");
            assertEquals("小明0x02 hello zero", res);
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail();
    }
}
