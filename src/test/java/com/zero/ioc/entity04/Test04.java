package com.zero.ioc.entity04;

import com.sun.tools.javac.util.Assert;
import com.zero.ioc.core.JsonApplicationContext;
import org.junit.Test;


public class Test04 {

    @Test
    public void test() throws Exception {

        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application04.json");
        Student student = (Student) jsonApplicationContext.getBean("xiaoming");
        String name = student.getUserName();
        String phoneName = student.getPhone().getPhoneName();
        Assert.check("小明".equals(name));
        Assert.check("huawei".equals(phoneName));
        String res = student.hello("zero");
        System.out.println("res = " + res);
        Assert.check(res.equals("小明 huawei hello zero"));
    }
}
