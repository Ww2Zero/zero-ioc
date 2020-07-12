package com.zero.ioc.core;

import com.zero.ioc.beans.exception.CircularDependenceException;
import com.zero.ioc.beans.factory.BeanDefinition;
import com.zero.ioc.beans.factory.ConstructorArg;
import com.zero.ioc.beans.factory.PropertyArg;
import com.zero.ioc.beans.type.SimpleTypeConverter;
import com.zero.ioc.utils.BeanUtils;
import com.zero.ioc.utils.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author zero
 */
public class DefaultBeanFactory implements BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    @Override
    public Object getBean(String beanName) throws Exception {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.isSingleton()) {
            Object bean = singletonObjects.get(beanName);
            if (!Objects.isNull(bean)) {
                return bean;
            }
        }
        return createBean(beanName, beanDefinition);

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
        Object earlyBean = earlySingletonObjects.get(beanName);
        if (earlyBean != null) {
            throw new CircularDependenceException("创建" + beanName + "检查到循环依赖，请检查配置");
        }
        Object bean = createBean(beanDefinition);
        if (bean != null) {
            //为了解决循环依赖，先添加到早期单例中
            earlySingletonObjects.put(beanName, bean);

            populateBean(bean, beanDefinition);
            singletonObjects.put(beanName, bean);
            //从早期单例Map中移除
            earlySingletonObjects.remove(beanName);
        }

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
                setPropertyValue(bean, beanDefinition, name, injectValue);

            }
        }
    }

    private void setPropertyValue(Object bean, BeanDefinition bd, String propertyName, Object injectValue) throws Exception {
        Class beanClass = Class.forName(bd.getClassName());
        propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        // 默认采用set方法注入，bean必须实现setter方法
        String propertySetMethodName = "set" + propertyName;
        for (Method method : beanClass.getMethods()) {
            if (method.getName().equals(propertySetMethodName)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
                    Object value = simpleTypeConverter.convertIfNecessary(injectValue, parameterTypes[0]);
                    method.invoke(bean, value);
                    break;
                }
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String className = beanDefinition.getClassName();
        Class clazz = ClassUtils.loadClass(className);

        List<ConstructorArg> constructorArgList = beanDefinition.getConstructorArgList();
        if (constructorArgList != null && !constructorArgList.isEmpty()) {
            List<Object> constructorArgValueList = new LinkedList<>();
            // todo refactor
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
