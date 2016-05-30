package com.sai.net.exception;

import com.sai.net.http.NetworkResponse;

public class NetworkError extends BaseException {
    public NetworkError() {
        super();
    }

    public NetworkError(Throwable cause) {
        super(cause);
    }

    public NetworkError(NetworkResponse networkResponse) {
        super(networkResponse);
    }
}
