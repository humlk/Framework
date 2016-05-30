package com.sai.net.response;


import org.json.JSONObject;

public class ResponseFactory {

    public static <T> ResponseParse getResPonse(Object o){
        if(o instanceof String){
            return new StringResponse();
        } else if(o instanceof JSONObject){
            return new JsonResponse();
        }else{
            return new BeanResponse<T>((Class<T>)o.getClass());
        }
    }
}
