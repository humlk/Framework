package com.sai.net.exception;


import com.sai.net.http.NetworkResponse;

public class BaseException extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public BaseException() {
        networkResponse = null;
    }

    public BaseException(NetworkResponse response) {
        networkResponse = response;
    }

    public BaseException(String exceptionMessage) {
       super(exceptionMessage);
       networkResponse = null;
    }

    public BaseException(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
    }

    public BaseException(Throwable cause) {
        super(cause);
        networkResponse = null;
    }

    public void setNetworkTimeMs(long networkTimeMs) {
       this.networkTimeMs = networkTimeMs;
    }

    public long getNetworkTimeMs() {
       return networkTimeMs;
    }
}
