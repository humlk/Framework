package com.sai.base.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;


/** 
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *  
 * @author: huajie 
 * @version: 1.0
 * @date:   2015年1月26日 
 *      
 */
public class JsonObjectUtil {

    public static <T> T getBean(String jsonString, Class<T> cls) {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(jsonString, cls);
        return t;
    }


    /**
     * 
     * @param jsonString
     * @param token new Type<List<class>>
     * @return
     */
    public static <T> T getBeans(String jsonString, TypeToken<T> token) {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(jsonString, token.getType());
        return t;
    }

    /**
     * 将对象转换为json
     * @param object
     * @return
     */
    public static String getJsonObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }


    /**
     * 将json转为request请求数据 a=b
     * @param json
     * @return
     */
    public static String jsonToRequestParameter(JSONObject json) {
        StringBuffer buffer = new StringBuffer();
        String key;
        String value;
        for (Iterator<String> iterator = json.keys(); iterator.hasNext();) {
            key = iterator.next();
            value = json.optString(key);
            buffer.append("&").append(key).append("=").append(value);
        }
        String str = buffer.toString();
        if (!StringUtil.isEmpty(str) && str.startsWith("&")) {
            str = str.replaceFirst("&", "");
        }
        return str;
    }

    public static String mapToRequestParameter(Map<String, Object> maps) {
        if (maps == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        String key;
        Object value;
        for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
            key = iterator.next();
            value = maps.get(key);
            buffer.append("&").append(key).append("=").append(value);
        }
        String str = buffer.toString();
        if (!StringUtil.isEmpty(str) && str.startsWith("&")) {
            str = str.replaceFirst("&", "");
        }
        return str;
    }


    public static String mapToRestfulParameter(Map<String, Object> maps) {
        if (maps == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        String key;
        Object value;
        for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
            key = iterator.next();
            value = maps.get(key);
            buffer.append("/").append(value);
        }
        String str = buffer.toString();
        if (!StringUtil.isEmpty(str) && str.startsWith("/")) {
            str = str.replaceFirst("/", "");
        }
        return str;
    }
    /**
     * map -> json
     * @param maps
     * @return
     */
    public static JSONObject mapToJson(Map<String, Object> maps) {
        if (maps == null) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        String key;
        Object value;
        try {
            for (Iterator<String> iterator = maps.keySet().iterator(); iterator.hasNext();) {
                key = iterator.next();
                value = maps.get(key);
                jsonObject.put(key, value);
            }
            return jsonObject;
        } catch (JSONException e) {
//            BaseLogger.d("json 组装失败->" + e);

        }
        return null;
    }

    public static JSONArray getJsonArray(JSONObject response, String key) {
        try {
            if (response != null && response.has(key)) {
                JSONArray array = response.getJSONArray(key);
                if (array != null && array.length() > 0) {
                    return array;
                }
            }
        } catch (JSONException e) {
//            BaseLogger.d("json 解析失败->" + e);
        }
        return null;
    }


    public static void copyTo(JSONArray src, JSONArray dst){
        if(src == null || dst == null){
            return;
        }
       for(int i=0;i<src.length();i++){
           try {
               dst.put(src.get(i));
           } catch (JSONException e) {
               e.printStackTrace();
           }
       }
    }
}
