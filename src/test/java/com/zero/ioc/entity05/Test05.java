package com.zero.ioc.entity05;

import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;

public class Test05 {

    @Test(expected = IllegalArgumentException.class)
    public void test() throws Exception {

        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application05.json");
        jsonApplicationContext.getBean("aObject");
    }
}
