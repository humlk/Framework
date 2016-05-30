package com.sai.net.response;

public class StringResponse extends ResponseParse {

    @Override
    public String parse(byte[] data) {
        return new String(data);
    }
}
