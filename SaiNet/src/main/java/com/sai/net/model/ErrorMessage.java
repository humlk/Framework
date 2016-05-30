package com.sai.net.model;


public class ErrorMessage {

    private String code;
    private String msg;

    public ErrorMessage(){

    }

    public ErrorMessage(String c, String m){
        code = c;
        msg = m;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
