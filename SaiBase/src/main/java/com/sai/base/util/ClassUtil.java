package com.sai.base.util;

import java.lang.reflect.ParameterizedType;

public class ClassUtil {

    public static <T> T getClassArgs(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getClass(Object o, int i){
        return (Class)((ParameterizedType) (o.getClass()
                .getGenericSuperclass())).getActualTypeArguments()[i];
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
