package com.zero.ioc.entity06;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test06 {

    @Test
    public void Test06() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application06.json");
        Student student = (Student) jsonApplicationContext.getBean("xiaoming");
        String name = student.getUserName();
        int age = student.getAge();
        assertEquals("小明", name);
        assertEquals(12, age);
    }


}
