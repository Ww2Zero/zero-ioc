package com.zero.ioc.utils;

public final class ClassUtils {

    private ClassUtils() {
    }

    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className) throws ClassNotFoundException {
        return getDefaultClassLoader().loadClass(className);

    }

}
