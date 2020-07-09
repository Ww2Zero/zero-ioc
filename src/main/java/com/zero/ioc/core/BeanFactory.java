package com.zero.ioc.core;

/**
 * @author zero
 */
public interface BeanFactory {

    /**
     * 根据beanName获取bean的实例
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;
}
