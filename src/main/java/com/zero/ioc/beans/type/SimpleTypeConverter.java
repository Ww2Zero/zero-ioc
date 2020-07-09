package com.zero.ioc.beans.type;

import com.zero.ioc.beans.exception.TypeMismatchException;
import com.zero.ioc.utils.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zero
 */
public class SimpleTypeConverter implements TypeConverter {

    private Map<Class<?>, PropertyEditor> defaultEditors;

    public SimpleTypeConverter() {

    }

    /**
     * 将value转换为目标类型
     *
     * @param value
     * @param targetType
     * @return
     */
    @Override
    public <T> T convertIfNecessary(Object value, Class<T> targetType) {

        if (ClassUtils.isAssignableValue(targetType, value)) {
            return targetType.cast(value);
        } else {
            if (value instanceof String) {
                PropertyEditor editor = findDefaultEditor(targetType);
                if (editor != null) {

                }
                try {
                    editor.setAsText((String) value);
                } catch (IllegalArgumentException e) {
                    throw new TypeMismatchException(value, targetType);
                }
                return (T) editor.getValue();
            } else {
                throw new RuntimeException("Todo : can't convert value for " + value + " class:" + targetType);
            }
        }
    }

    private <T> PropertyEditor findDefaultEditor(Class<T> targetType) {
        if (this.defaultEditors == null) {
            createDefaultEditors();
        }
        return this.defaultEditors.get(targetType);
    }

    private void createDefaultEditors() {
        this.defaultEditors = new HashMap<Class<?>, PropertyEditor>(64);
        this.defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        this.defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));
        this.defaultEditors.put(int.class, new CustomNumberEditor(Integer.class, false));
        this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class, true));
    }
}
