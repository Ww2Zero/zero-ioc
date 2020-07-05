package com.zero.ioc.core;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory {

    Object getBean(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
