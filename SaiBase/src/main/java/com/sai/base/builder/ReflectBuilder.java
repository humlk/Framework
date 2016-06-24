//package com.sai.base.builder;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//
///**
// * 反射
// */
//public class ReflectBuilder {
//    private Object mObject;
//    private Object[] mObjectArgs;
//    private Class<?> mClass;
//    private String mField;
//    private String mMethodName;
//    private Object[] mMethodArgs;
//
//    public ReflectBuilder setObject(String clsName, Object[] constructorArgs) throws Exception {
//        setObject(Class.forName(clsName), constructorArgs);
//        return this;
//    }
//
//    public ReflectBuilder setObject(Class cls, Object[] constructorArgs) throws Exception {
//        mClass = cls;
//        mObjectArgs = constructorArgs;
//        return this;
//    }
//
//    public ReflectBuilder setObject(Object object){
//        mObject = object;
//        return this;
//    }
//
//    public Object getField(String fieldName){
//        return null;
//    }
//
//    public Object createObject(){
//
//        return null;
//    }
//
//    public ReflectBuilder setMethod(String methodName, Object[] methodArgs){
//        mMethodName = methodName;
//        mMethodArgs = methodArgs;
//        return this;
//    }
//
//
//    private static Object newInstance(Class cls, Object[] args) throws Exception {
//
//        if(args != null){
//            //使用构造方法创建对象
//            Class[] classes = new Class[args.length];
//            for(int i=0;i<classes.length;i++){
//                classes[i] = args[i].getClass();
//            }
//            //构造函数
//            Constructor constructor = cls.getConstructor(classes);
//            return constructor.newInstance(args);
//        }else{
//            return cls.newInstance();
//        }
//    }
//
//    /**
//     * 获取public属性
//     * @param object
//     * @param fieldName
//     * @return
//     * @throws Exception
//     */
//    private static Object accessField(Object object, String fieldName) throws Exception {
//        Field field = object.getClass().getField(fieldName);
//        return null;
//    }
//
//    private Object accessMethod(){
//        return null;
//    }
//
//}
