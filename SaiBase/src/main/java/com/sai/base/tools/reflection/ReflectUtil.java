package com.sai.base.tools.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectUtil {


    /**
     * 获取object自身的所有属性
     * @param object
     * @param name
     * @return
     */
    public static Object getDeclaredField(Object object, String name) {
        try {
            //只访问自身的属性
            Field field = object.getClass().getDeclaredField(name);
            if (field != null) {
                field.setAccessible(true);
                return field.get(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取object，父类和实现的接口类的public属性
     * @param object
     * @param name
     * @return
     */
    public static Object getField(Object object, String name) {
        try {
            Field field = object.getClass().getField(name);
            if (field != null) {
                return field.get(object);
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取object的所有方法
     * @param object
     * @param name
     * @param args
     * @return
     */
    public static Object getDeclaredMethod(Object object, String name, Object[] args) {
        //只访问自身的方法
        Class[] argClass = null;
        if (args != null) {
            argClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argClass[i] = args[i].getClass();
            }
        }
        try {
            Method method = object.getClass().getDeclaredMethod(name, argClass);
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取object，父类，接口类的public方法
     * @param object
     * @param name
     * @param args
     * @return
     */
    public static Object getMethod(Object object, String name, Object[] args) {

        Class[] argClass = null;
        if (args != null) {
            argClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argClass[i] = args[i].getClass();
            }
        }
        try {
            Method method = object.getClass().getMethod(name, argClass);
            return method.invoke(object.getClass(), args);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 创建实例
     * @param cls
     * @param args
     * @return
     */
    public static Object newInstance(Class cls, Object[] args){

        try {
            if(args != null){
                //使用构造方法创建对象
                Class[] classes = new Class[args.length];
                for(int i=0; i<classes.length; i++){
                    classes[i] = args[i].getClass();
                }
                //构造函数
                Constructor constructor = cls.getConstructor(classes);
                return constructor.newInstance(args);
            }else{
                return cls.newInstance();
            }
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * object是不是class的实例
     * @param object
     * @param cls
     * @return
     */
    public static boolean isInstance(Object object, Class cls){
        return cls.isInstance(object);
    }

    /*-------------修改属性-----------------*/

    /**
     * 修改属性
     * @param object
     * @param fieldName
     * @param value
     * @return
     */
    public static boolean setField(Object object, String fieldName, Object value){
        try {
            Field field = object.getClass().getField(fieldName);
            if(field != null){
                field.set(object, value);
                return true;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}