package com.sai.net.exception;

import com.sai.net.http.NetworkResponse;

@SuppressWarnings("serial")
public class ServerError extends SaiException {
    public ServerError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ServerError() {
        super();
    }
}

