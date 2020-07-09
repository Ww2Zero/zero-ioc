package com.zero.ioc.beans.exception;

/**
 * @author zero
 */
public class TypeMismatchException extends BeansException {

    private transient Object value;

    private Class<?> targetType;

    public TypeMismatchException(Object value, Class<?> targetType) {
        super(String.format("Failed to convert value :%sto type %s", value, targetType));
        this.value = value;
        this.targetType = targetType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return targetType;
    }
}
