package com.zero.ioc.core;

import com.zero.ioc.base.BeanDefinition;
import com.zero.ioc.base.ConstructorArg;
import com.zero.ioc.utils.BeanUtils;
import com.zero.ioc.utils.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author zero
 */
public class DefaultBeanFactory implements BeanFactory {

    private final static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object bean = beanMap.get(beanName);
        if (!Objects.isNull(bean)) {
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        bean = createBean(beanDefinition);
        beanMap.put(beanName, bean);
        return bean;
    }

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(className);

        List<ConstructorArg> constructorArgList = beanDefinition.getConstructorArgList();
        if (constructorArgList != null && !constructorArgList.isEmpty()) {
            List<Object> constructorArgValueList = new LinkedList<>();
            for (ConstructorArg constructorArg : constructorArgList) {
                if (constructorArg.getValue() != null) {
                    constructorArgValueList.add(constructorArg.getValue());
                } else {
                    constructorArgValueList.add(getBean(constructorArg.getRef()));
                }
            }
            Class[] constructorArgTypes = constructorArgValueList.stream().map(Object::getClass).collect(Collectors.toList()).toArray(new Class[]{});
            Constructor constructor = clazz.getConstructor(constructorArgTypes);
            return BeanUtils.createInstance(clazz, constructor, constructorArgValueList.toArray());
        }
        return BeanUtils.createInstance(clazz, null, null);
    }

    protected void registerBean(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
