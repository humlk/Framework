package com.sai.net.http;


import com.sai.net.exception.SaiException;

public interface ResponseListener<T> {
    public void onResponse(T response);
    public void onErrorResponse(SaiException error);
}
