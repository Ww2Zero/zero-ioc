package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class BeanCreationException extends BeansException {

    private String beanName;


    public BeanCreationException(String beanName) {
        super(String.format("Error creating bean with name  '%s'", beanName));
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, Throwable cause) {
        super(String.format("Error creating bean with name  '%s'", beanName), cause);
        this.beanName = beanName;
    }
}
