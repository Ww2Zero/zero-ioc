package com.zero.ioc.base;


import java.util.Objects;

public class ConstructorArg {

    private int index;
    private String ref;
    private String name;
    private Object value;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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
        return index == that.index &&
                Objects.equals(ref, that.ref) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, ref, name, value);
    }

    @Override
    public String toString() {
        return "ConstructorArg{" +
                "index=" + index +
                ", ref='" + ref + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
