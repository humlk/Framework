package com.sai.net.request.http;

import com.sai.base.util.ClassUtil;
import com.sai.net.http.HttpHeaderParser;
import com.sai.net.http.NetworkResponse;
import com.sai.net.http.Request;
import com.sai.net.http.Response;
import com.sai.net.request.OnResponseListener;
import com.sai.net.request.RequestOptions;
import com.sai.net.request.RequestParams;
import com.sai.net.response.ResponseFactory;
import com.sai.net.response.ResponseParse;

import java.util.Map;


public class HttpRequest<T> extends Request<byte[]> {

    private RequestParams mRequestParams;

    public HttpRequest(String url, RequestParams requestParams, RequestOptions options, OnResponseListener<T> listener){
        super(url,options, listener);
        mRequestParams = requestParams;
    }

    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        if(mResponseListener == null){
            return;
        }

        //是不是对外提供接口，让上层自己解析

        //默认解析
        Object obj = ClassUtil.getClassArgs(mResponseListener, 0);
        if(obj == null){
            mResponseListener.onSuccess(response);
            return;
        }
        ResponseParse rp = ResponseFactory.getResPonse(obj);
        mResponseListener.onSuccess(rp.parse(response));
    }

    @Override
    public Map<String, String> getHeaders() {
        if(mRequestParams != null){
            return mRequestParams.getHeaders();
        }
        return super.getHeaders();
    }

    @Override
    public byte[] getBody(){
        //默认模拟form表单
        if(mRequestParams != null){
            return mRequestParams.getBody();
        }
        return null;
    }

    @Override
    public String getCacheKey() {
        return super.getCacheKey();
    }

    @Override
    public String getBodyContentType() {
        if (mRequestParams.getContentType() != null) {
            return mRequestParams.getContentType();
        } else {
            return super.getBodyContentType();
        }
    }
}
