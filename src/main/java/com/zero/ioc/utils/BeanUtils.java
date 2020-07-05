package com.zero.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class BeanUtils {

    private BeanUtils() {
    }

    public static <T> T instanceByCglib(Class<T> clz, Constructor ctr, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if (ctr == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(ctr.getParameterTypes(), args);
        }
    }

    public static <T> T createInstance(Class<T> clz, Constructor ctr, Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (ctr == null) {
            return clz.newInstance();
        }
        return (T) ctr.newInstance(args);
    }

}
