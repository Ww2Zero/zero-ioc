package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class NoSuchBeanDefinitionException extends BeansException {

    private String beanName;

    public NoSuchBeanDefinitionException(String beanName) {
        super(String.format("No bean named '%s' is defined", beanName));
        this.beanName = beanName;
    }
}
