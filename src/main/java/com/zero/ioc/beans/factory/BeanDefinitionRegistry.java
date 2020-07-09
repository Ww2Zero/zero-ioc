package com.zero.ioc.beans.factory;

/**
 * @author zero
 */
public interface BeanDefinitionRegistry {

    /**
     * 根据beanName获取bean的定义信息
     *
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 注册bean的信息
     *
     * @param beanName
     * @param bd
     */
    void registerBeanDefinition(String beanName, BeanDefinition bd);
}
