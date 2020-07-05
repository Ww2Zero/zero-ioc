package com.zero.ioc.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class BeanUtils {

    private BeanUtils() {
    }


    public static <T> T createInstance(Class<T> clazz, Constructor constructor, Object[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        if (constructor == null) {
            return clazz.newInstance();
        }
        return (T) constructor.newInstance(args);
    }
}
