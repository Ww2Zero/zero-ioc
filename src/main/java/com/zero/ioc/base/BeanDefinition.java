package com.zero.ioc.base;

import java.util.List;

/**
 * @author zero
 */


public class BeanDefinition {

    private String name;
    private String className;
    private String interfaceName;

    private List<ConstructorArg> constructorArgList;
    private List<PropertyArg> propertyArgList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
