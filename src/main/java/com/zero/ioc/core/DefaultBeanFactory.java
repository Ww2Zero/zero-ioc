package com.zero.ioc.core;

import com.zero.ioc.base.BeanDefinition;
import com.zero.ioc.utils.BeanUtils;
import com.zero.ioc.utils.ClassUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zero
 */
public class DefaultBeanFactory implements BeanFactory {

    private final static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) throws ClassNotFoundException {

        Object bean = beanMap.get(beanName);
        if (!Objects.isNull(bean)) {
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        bean = createBean(beanDefinition);
        beanMap.put(beanName, bean);
        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException {
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(className);
        return BeanUtils.instanceByCglib(clazz, null, null);
    }

    protected void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
