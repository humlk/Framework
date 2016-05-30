package com.sai.net.http;


import com.sai.net.exception.BaseException;

public interface ResponseListener<T> {
    public void onResponse(T response);
    public void onErrorResponse(BaseException error);
}
