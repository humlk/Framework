package com.sai.net.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpStack implements HttpStack{

    private final OkHttpClient mHttpClient;
    private final SSLSocketFactory mSSLSocketFactory;

    public OkHttpStack(OkHttpClient okHttpClient){
        this(okHttpClient, null);
    }

    public OkHttpStack(OkHttpClient okHttpClient, SSLSocketFactory sslSocketFactory){
        mHttpClient = okHttpClient;
        mSSLSocketFactory = sslSocketFactory;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException {

        //设置client的配置信息
        OkHttpClient.Builder httpClientBuild = mHttpClient.newBuilder();
        //超时设置
        int timeout = request.getTimeoutMs();
        httpClientBuild.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        httpClientBuild.readTimeout(timeout, TimeUnit.MILLISECONDS);
        httpClientBuild.writeTimeout(timeout, TimeUnit.MILLISECONDS);

        if(mSSLSocketFactory != null){
            httpClientBuild.sslSocketFactory(mSSLSocketFactory);
        }

        //请求数据
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        //url
        builder.url(request.getUrl());
        //header
        Map<String, String> reqHeaders = request.getHeaders();
        String key = null;
        if(reqHeaders != null){
            for(Map.Entry<String,String> entry:reqHeaders.entrySet()){
                key = entry.getKey();
                builder.addHeader(key, entry.getValue());
            }
        }
        if(additionalHeaders != null){
            for(Map.Entry<String,String> entry:additionalHeaders.entrySet()){
                key = entry.getKey();
                builder.addHeader(key, entry.getValue());
            }
        }
        //body
        setConnectionParametersForRequest(builder, request);


        //处理各种请求方式: 缓存，网络



        //网络请求
        OkHttpClient client = httpClientBuild.build();
        Response response = client.newCall(builder.build()).execute();
        if(response == null){
            return null;
        }
        return parseOkResponse(response);
    }

    private HttpResponse parseOkResponse(Response response){

        HttpResponse httpResponse = new HttpResponse();
        //statusCode
        httpResponse.setStatusCode(response.code());
        httpResponse.setMessage(response.message());

        //body
        ResponseBody body = response.body();
        if(body != null){
            httpResponse.setContentStream(body.byteStream());
            httpResponse.setContentLength((int) body.contentLength());
            if(body.contentType() != null){
                httpResponse.setContentType(body.contentType().type());
            }
        }
        httpResponse.setContentEncoding(response.header("Content-Encoding"));

        //header
        Headers header = response.headers();
        int size = header.size();
        Map<String,String> headerMap = new HashMap<>();
        for(int i=0;i<size;i++){
            headerMap.put(header.name(i),header.value(i));
        }
        httpResponse.setHeaders(headerMap);
        return httpResponse;
    }

    static void setConnectionParametersForRequest(okhttp3.Request.Builder builder,
                                                  Request<?> request) throws IOException {
        switch (request.getMethod()) {
            case Request.Method.GET:
                builder.get();
                break;
            case Request.Method.DELETE:
                builder.delete(getBody(request));
                break;
            case Request.Method.POST:
                builder.post(getBody(request));
                break;
            case Request.Method.PUT:
                builder.post(getBody(request));
                break;
            case Request.Method.HEAD:
                builder.head();
                break;
            case Request.Method.OPTIONS:
                builder.method("OPTIONS",null);
                break;
            case Request.Method.TRACE:
                builder.method("TRACE", null);
                break;
            case Request.Method.PATCH:
                builder.patch(getBody(request));
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static RequestBody getBody(Request<?> request){
        byte[] body = request.getBody();
        if (body != null) {
            return RequestBody.create(MediaType.parse(request.getBodyContentType()),body);
        }
        return null;
    }
}
