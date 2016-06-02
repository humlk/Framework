package com.sai.net.exception;


import com.sai.net.http.NetworkResponse;

public class SaiException extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public SaiException() {
        networkResponse = null;
    }

    public SaiException(NetworkResponse response) {
        networkResponse = response;
    }

    public SaiException(String exceptionMessage) {
       super(exceptionMessage);
       networkResponse = null;
    }

    public SaiException(String exceptionMessage, Throwable reason) {
        super(exceptionMessage, reason);
        networkResponse = null;
    }

    public SaiException(Throwable cause) {
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
