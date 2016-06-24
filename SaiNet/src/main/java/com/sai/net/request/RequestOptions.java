package com.sai.net.request;

/**
 * 网络请求的配置信息
 */
public class RequestOptions implements RequestInterface{

    /** 请求方法 Request.Method **/
    public int method = Method.GET;
    /** 请求内容类型 **/
//    public String contentType;

    /** 请求协议 Request.Protocol **/
    public String protocol;

    /** 请求超时 **/
    public int timeOut = 30 * 1000;

    /** 超时重连次数 **/
    public int maxRetries = 0;

    /** 数据模型 **/
    public int cacheModel = Model.ONLY_REQUEST;
}
