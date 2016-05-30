package com.sai.net.http;


import com.sai.net.cache.Cache;
import com.sai.net.exception.BaseException;

public class Response<T> {

    public static <T> Response<T> success(T result, Cache.Entry cacheEntry) {
        return new Response<T>(result, cacheEntry);
    }


    public static <T> Response<T> error(BaseException error) {
        return new Response<T>(error);
    }

    public final T result;

    public final Cache.Entry cacheEntry;

    public final BaseException error;

    public boolean isSuccess() {
        return error == null;
    }


    private Response(T result, Cache.Entry cacheEntry) {
        this.result = result;
        this.cacheEntry = cacheEntry;
        this.error = null;
    }

    private Response(BaseException error) {
        this.result = null;
        this.cacheEntry = null;
        this.error = error;
    }
}
