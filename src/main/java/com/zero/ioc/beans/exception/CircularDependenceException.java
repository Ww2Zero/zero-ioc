package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class CircularDependenceException extends RuntimeException {

    private String beanName;

    public CircularDependenceException(String beanName) {
        super(String.format("found circular dependence when creating bean with name  '%s'", beanName));
        this.beanName = beanName;
    }
}
