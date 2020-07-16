package com.zero.ioc.core;

import com.zero.ioc.beans.exception.BeanCreationException;
import com.zero.ioc.beans.exception.CircularDependenceException;
import com.zero.ioc.beans.exception.NoSuchBeanDefinitionException;
import com.zero.ioc.beans.factory.BeanDefinition;
import com.zero.ioc.beans.factory.ConstructorArg;
import com.zero.ioc.beans.factory.PropertyArg;
import com.zero.ioc.beans.type.SimpleTypeConverter;
import com.zero.ioc.utils.BeanUtils;
import com.zero.ioc.utils.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
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
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (Objects.isNull(beanDefinition)) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
        if (beanDefinition.isSingleton() || beanDefinition.isDefault()) {
            Object bean = singletonObjects.get(beanName);
            if (!Objects.isNull(bean)) {
                return bean;
            }
        }
        return createBean(beanName, beanDefinition);

    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object earlyBean = earlySingletonObjects.get(beanName);
        if (earlyBean != null) {
            throw new CircularDependenceException(beanName);
        }
        Object bean = null;
        try {
            bean = createBean(beanDefinition);
        } catch (Exception e) {
            throw new BeanCreationException(beanName, e);
        }
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

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyArg> propertyArgList = beanDefinition.getPropertyArgList();
        if (propertyArgList != null && !propertyArgList.isEmpty()) {
            for (PropertyArg propertyArg : propertyArgList) {
                String propertyArgName = propertyArg.getName();
                String propertyArgRef = propertyArg.getRef();
                Object propertyArgValue = propertyArg.getValue();
                Object injectValue = null;
                if (propertyArgValue != null) {
                    injectValue = propertyArgValue;
                } else if (StringUtils.isNotEmpty(propertyArgRef)) {
                    injectValue = getBean(propertyArgRef);
                }
                setPropertyValue(bean, beanDefinition, propertyArgName, injectValue);

            }
        }
    }

    private void setPropertyValue(Object bean, BeanDefinition bd, String propertyName, Object injectValue) {
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : propertyDescriptors) {
                if (propertyName.equals(pd.getName())) {
                    Object value = simpleTypeConverter.convertIfNecessary(injectValue, pd.getPropertyType());
                    pd.getWriteMethod().invoke(bean, value);
                    break;
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException(String.format("failed to obtain BeanInfo for class[%s] on set [%s]", bd.getClassName(), propertyName), e);
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
        if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
            createBean(name, beanDefinition);
        }
        beanDefinitionMap.put(name, beanDefinition);
    }
}
