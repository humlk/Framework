package com.sai.net.exception;

import com.sai.net.http.NetworkResponse;

public class ParseError extends SaiException {
    public ParseError() { }

    public ParseError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ParseError(Throwable cause) {
        super(cause);
    }
}
