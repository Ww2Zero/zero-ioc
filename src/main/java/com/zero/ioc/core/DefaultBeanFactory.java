package com.zero.ioc.core;

import com.zero.ioc.base.BeanDefinition;
import com.zero.ioc.base.ConstructorArg;
import com.zero.ioc.base.PropertyArg;
import com.zero.ioc.utils.BeanUtils;
import com.zero.ioc.utils.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author zero
 */
public class DefaultBeanFactory implements BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String beanName) throws Exception {

        Object bean = beanMap.get(beanName);
        if (!Objects.isNull(bean)) {
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        bean = createBean(beanDefinition);
        populateBean(bean, beanDefinition);
        beanMap.put(beanName, bean);
        return bean;
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) throws Exception {
        List<PropertyArg> propertyArgList = beanDefinition.getPropertyArgList();
        if (propertyArgList != null && !propertyArgList.isEmpty()) {
            for (PropertyArg propertyArg : propertyArgList) {
                Object value = propertyArg.getValue();
                String name = propertyArg.getName();
                String ref = propertyArg.getRef();
                Object injectValue = null;
                if (value != null) {
                    injectValue = value;
                } else if (StringUtils.isNotEmpty(ref)) {
                    injectValue = getBean(ref);
                }
                Method method = setPropertyValue(beanDefinition, name, injectValue);
                method.invoke(bean, injectValue);
            }
        }
    }

    private Method setPropertyValue(BeanDefinition bd, String propertyName, Object injectValue) throws Exception {
        Class beanClass = Class.forName(bd.getClassName());
        Class injectClazz = injectValue.getClass();
        Class supClass = injectValue.getClass().getSuperclass();
        if (supClass != null && supClass != Object.class) {
            injectClazz = supClass;
        }
        propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        Method setter = beanClass.getMethod("set" + propertyName, injectClazz);
        return setter;
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
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
