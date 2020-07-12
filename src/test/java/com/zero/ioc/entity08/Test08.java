package com.zero.ioc.entity08;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test08 {

    @Test
    public void TestLazyInitTrue() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application08.json");
        Student student01 = (Student) jsonApplicationContext.getBean("xiaoming");
        String name = student01.getUserName();
        int age = student01.getAge();
        assertEquals("小明", name);
        assertEquals(12, age);
    }

    @Test
    public void TestLazyInitFalse() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application08.json");
        Student student01 = (Student) jsonApplicationContext.getBean("xiaohua");
        String name = student01.getUserName();
        int age = student01.getAge();
        assertEquals("小华", name);
        assertEquals(22, age);
    }

    /**
     * lazy init 默认值为false
     *
     * @throws Exception
     */
    @Test
    public void TestLazyInitDefault() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application08.json");
        Student student01 = (Student) jsonApplicationContext.getBean("xiaohan");
        String name = student01.getUserName();
        int age = student01.getAge();
        assertEquals("小韩", name);
        assertEquals(23, age);
    }
}
