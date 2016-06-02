package com.sai.net.util;


import com.sai.net.common.RequestError;
import com.sai.net.model.ErrorMessage;
import com.sai.net.exception.SaiException;


public class SaiUtil {

    public static ErrorMessage netRequestError(SaiException error) {
        ErrorMessage httpError = new ErrorMessage(RequestError.CODE_DEFAULT, RequestError.MSG_DEFAULT);
        if(error != null){
            if(error.networkResponse != null){
                httpError.setCode(error.networkResponse.statusCode+"");
                httpError.setMsg(null);
            }
            httpError.setMsg(error.getMessage());
        }
        return httpError;
    }


}
