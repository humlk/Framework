package com.sai.net.http;

import java.io.InputStream;
import java.util.Map;

/**
 * 服务端响应信息
 * 模拟apache
 */
public class HttpResponse {

    /** 响应code **/
    private int statusCode;
    /** 响应message **/
    private String message;
    /** 头部信息 **/
    private Map<String, String> headers;
    /** 响应数据类型 **/
    private String contentType;
    /** 响应数据编码 **/
    private String contentEncoding;
    /** 响应数据 **/
    private InputStream contentStream;
    /** 响应数据长度 **/
    private int contentLength;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public InputStream getContentStream() {
        return contentStream;
    }

    public void setContentStream(InputStream contentStream) {
        this.contentStream = contentStream;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }
}
