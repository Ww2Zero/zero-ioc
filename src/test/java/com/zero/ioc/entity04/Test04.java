package com.zero.ioc.entity04;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Test04 {

    @Test
    public void test() throws Exception {

        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application04.json");
        Student student = (Student) jsonApplicationContext.getBean("xiaoming");
        String name = student.getUserName();
        String phoneName = student.getPhone().getPhoneName();
        assertEquals("小明", name);
        assertEquals("huawei", phoneName);
        String res = student.hello("zero");
        System.out.println("res = " + res);
        assertEquals("小明 huawei hello zero", res);
    }
}
