package com.zero.ioc.beans.type;

/**
 * @author zero
 */
public interface TypeConverter {

    /**
     * 将value转换为目标类型
     *
     * @param value
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convertIfNecessary(Object value, Class<T> targetType);
}
