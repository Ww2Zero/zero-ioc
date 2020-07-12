package com.zero.ioc.entity07;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Test07 {

    @Test
    public void TestSingleton() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application07.json");
        Student student01 = (Student) jsonApplicationContext.getBean("xiaoming");
        Student student02 = (Student) jsonApplicationContext.getBean("xiaoming");
        String name = student01.getUserName();
        int age = student01.getAge();
        assertEquals("小明", name);
        assertEquals(12, age);
        assertEquals(student01, student02);
    }

    @Test
    public void TestPrototype() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application07.json");
        Student student01 = (Student) jsonApplicationContext.getBean("xiaohua");
        Student student02 = (Student) jsonApplicationContext.getBean("xiaohua");
        assertEquals("小华", student01.getUserName());
        assertEquals(22, student01.getAge());
        assertEquals("小华", student02.getUserName());
        assertEquals(22, student02.getAge());
        assertNotEquals(student01, student02);
    }

}
