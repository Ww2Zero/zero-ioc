package com.zero.ioc.beans.factory;

import java.util.List;
import java.util.Objects;

/**
 * @author zero
 */
public class BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "";

    private String beanName;
    private String className;
    private String interfaceName;
    private String scope;
    private List<ConstructorArg> constructorArgList;
    private List<PropertyArg> propertyArgList;
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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
