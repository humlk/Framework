package com.sai.base.util;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *  
 * @author: huajie-work 
 * @version: 1.0
 * @date:   2015年5月4日 
 *      
 */
public class ObjectUtil {

    /**
     * list拷贝
     * @param src
     * @return
     */
    public static List deepCopy(List src) {
        //将对象写到流里
        List dest = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(src);//从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            dest = (List) oi.readObject();
        } catch (Exception e) {
        }
        return dest;
    }
    
    /**
     * 
     * @param object
     * @param trim true:值为空则不返回
     * @return
     */
    public static Map<String, Object> toMap(Object object, boolean trim) {
        try {
            Class<?> mClass = object.getClass();
            Map<String, Object> map = new HashMap<String, Object>();
            Field[] fields = mClass.getDeclaredFields();
            Object objValue;
            for (int i = 0; i < fields.length; i++) {
                objValue = getFieldValue(object,fields[i]);
                if (objValue != null || !trim) {
                    map.put(fields[i].getName(), objValue);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject toJson(Object object, boolean trim) {
        if(object == null){
            return null;
        }
        try {
            Class<?> mClass = object.getClass();
            JSONObject json = new JSONObject();
            Field[] fields = mClass.getDeclaredFields();
            Object objValue;
            for (int i = 0; i < fields.length; i++) {
                objValue = getFieldValue(object,fields[i]);
                if (objValue != null || !trim) {
                    json.put(fields[i].getName(), objValue);
                }
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static Object getFieldValue(Object object, Field field){
        Class<?> mClass = object.getClass();
        String fieldName = field.getName();
        Class<?> fieldType = field.getType();
        String pre = "get";
        if (fieldType.equals(boolean.class)) {
            pre = "is";
        }
        String mathodName = pre + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method;
        try {
            method = mClass.getDeclaredMethod(mathodName);
            return method.invoke(object);
        } catch (Exception e) {
        }
        return null;
    }
}
