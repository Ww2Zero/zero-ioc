package com.zero.ioc.entity01;


import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test01 {


    @Test
    public void test() throws Exception {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application01.json");
        Hello hello = (Hello) jsonApplicationContext.getBean("hello");
        String res = hello.hello("zero");
        System.out.println("res = " + res);
        assertEquals("hello zero", res);
    }
}
