package com.sai.net.model;

import com.sai.base.util.JsonObjectUtil;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestResult {
    private String response;
    private ErrorMessage errorMessage;

    public RequestResult(){

    }
    public RequestResult(ErrorMessage errorMessage){
        this.errorMessage = errorMessage;
    }

    public RequestResult(String response){
        this.response = response;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public <T> T getBean(Class<T> cls) {
        if (response != null) {
            return JsonObjectUtil.getBean(response, cls);
        }
        return null;
    }

    public JSONObject getJson() {
        if (response == null) {
            return null;
        }
        try {
            return new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
