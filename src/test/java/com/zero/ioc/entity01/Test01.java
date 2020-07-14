package com.zero.ioc.entity01;


import com.zero.ioc.beans.exception.BeanDefinitionStoreException;
import com.zero.ioc.beans.exception.NoSuchBeanDefinitionException;
import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test01 {


    @Test
    public void testGetBean() {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application01.json");
        Hello hello = (Hello) jsonApplicationContext.getBean("hello");
        String res = hello.hello("zero");
        assertEquals("hello zero", res);
    }

    @Test
    public void testErrorJson() {
        try {
            JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("z-application01.json");
            Hello hello = (Hello) jsonApplicationContext.getBean("hello");
            String res = hello.hello("zero");
            assertEquals("hello zero", res);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testErrorBeanName() {
        try {
            JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application01.json");
            Hello hello = (Hello) jsonApplicationContext.getBean("hello0x02");
            String res = hello.hello("zero");
            assertEquals("hello zero", res);
        } catch (NoSuchBeanDefinitionException e) {
            return;
        }
        Assert.fail();
    }
}
