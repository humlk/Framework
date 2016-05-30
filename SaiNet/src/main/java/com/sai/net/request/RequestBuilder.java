package com.sai.net.request;

import com.sai.net.request.http.HttpRequest;

public class RequestBuilder<T> extends BaseRequestBuilder<T>{

    @Override
    protected HttpRequest createRequest(String url) {
        return new HttpRequest<T>(url, mRequestParams, mRequestOptions, mCallBackListener);
    }

}
