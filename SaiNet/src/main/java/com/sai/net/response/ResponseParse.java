package com.sai.net.response;


public abstract class ResponseParse {

    public abstract <T> T parse(byte[] data);

}
