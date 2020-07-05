package com.zero.ioc.core;

public interface BeanFactory {

    Object getBean(String beanName) throws Exception;
}
