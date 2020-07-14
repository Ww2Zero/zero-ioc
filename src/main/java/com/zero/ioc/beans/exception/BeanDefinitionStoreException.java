package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
