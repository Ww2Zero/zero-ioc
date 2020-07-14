package com.zero.ioc.beans.factory;


import java.util.Objects;

/**
 * 构造函数的参数
 * 构造函数的入参暂时只支持名称匹配
 *
 * @author zero
 */
public class ConstructorArg {

    /**
     * 构造函数参数名称
     */
    private String name;
    /**
     * 构造函数参数为引用 指向另外一个bean
     */
    private String ref;
    /**
     * 构造函数参数 值
     */
    private Object value;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConstructorArg that = (ConstructorArg) o;
        return
                Objects.equals(ref, that.ref) &&
                        Objects.equals(name, that.name) &&
                        Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, name, value);
    }

    @Override
    public String toString() {
        return "ConstructorArg{" +
                ", ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
