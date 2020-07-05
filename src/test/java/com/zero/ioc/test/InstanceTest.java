package com.zero.ioc.test;


import com.zero.ioc.test.entity.CGLIB.Son;
import com.zero.ioc.test.entity.CGLIB.Worker;
import com.zero.ioc.test.entity.JDK.Cat;
import com.zero.ioc.test.entity.JDK.Dog;
import com.zero.ioc.test.entity.JDK.Pig;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;


public class InstanceTest {

    @Test
    public void TestJdkNoConstructor1() throws IllegalAccessException, InstantiationException {
        Dog dog = Dog.class.newInstance();
        String zero = dog.hello("zero");
        System.out.println("zero = " + zero);
        assertEquals("汪汪汪～zero", zero);
    }

    @Test
    public void TestJdkNoConstructor2() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<Dog> constructor = Dog.class.getConstructor(null);
        Dog dog = constructor.newInstance(null);
        String zero = dog.hello("zero");
        System.out.println("zero = " + zero);
        assertEquals("汪汪汪～zero", zero);
    }

    @Test
    public void TestJdkWithConstructor() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor<Cat> constructor = Cat.class.getConstructor(String.class);
        Cat cat = constructor.newInstance("小花猫");
        String zero = cat.hello("zero");
        System.out.println("zero = " + zero);
        assertEquals("小花猫: 喵喵喵～zero", zero);
    }

    @Test
    public void TestJdkWithConstructor2() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<Pig> constructor = Pig.class.getConstructor(String.class);
        Pig pig = constructor.newInstance("佩奇");
        String zero = pig.hello("zero");
        System.out.println("zero = " + zero);
        assertEquals("佩奇哈哈哈～zero", zero);
    }

    @Test
    public void TestCglibNoConstructor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Son.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Son son = (Son) enhancer.create();
        String q = son.hello("Q");
        System.out.println("q = " + q);
        assertEquals("SonhelloQ", q);
    }


    @Test
    public void TestCglibWithConstructor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Worker.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Worker worker = (Worker) enhancer.create(new Class[]{String.class}, new Object[]{"faker"});
        String r = worker.hello("R");
        System.out.println("r = " + r);
        assertEquals("faker is worker ,R", r);
    }
}
