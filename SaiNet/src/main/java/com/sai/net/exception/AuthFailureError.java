package com.sai.net.exception;


import com.sai.net.http.NetworkResponse;

public class AuthFailureError extends BaseException {
    public AuthFailureError() {
        super();
    }

    public AuthFailureError(Throwable cause) {
        super(cause);
    }

    public AuthFailureError(NetworkResponse networkResponse) {
        super(networkResponse);
    }
}
