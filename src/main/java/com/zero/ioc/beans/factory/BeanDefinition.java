package com.zero.ioc.beans.factory;

import java.util.List;
import java.util.Objects;

/**
 * @author zero
 */
public class BeanDefinition {

    /**
     * 单例bean
     */
    public static final String SCOPE_SINGLETON = "singleton";
    /**
     * 多例的bean
     */
    public static final String SCOPE_PROTOTYPE = "prototype";
    /**
     * 默认的scope
     */
    public static final String SCOPE_DEFAULT = "";

    /**
     * bean的名称
     */
    private String beanName;
    /**
     * bean对应的class路径
     */
    private String className;
    /**
     *
     */
    private String scope;
    /**
     * 构造函数参数列表
     */
    private List<ConstructorArg> constructorArgList;
    /**
     * 类内变量参数列表
     */
    private List<PropertyArg> propertyArgList;
    /**
     * 懒加载标志
     */
    private boolean lazyInit;

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isDefault() {
        return SCOPE_DEFAULT.equals(scope) || Objects.isNull(scope);
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgList() {
        return constructorArgList;
    }

    public void setConstructorArgList(List<ConstructorArg> constructorArgList) {
        this.constructorArgList = constructorArgList;
    }

    public List<PropertyArg> getPropertyArgList() {
        return propertyArgList;
    }

    public void setPropertyArgList(List<PropertyArg> propertyArgList) {
        this.propertyArgList = propertyArgList;
    }
}
