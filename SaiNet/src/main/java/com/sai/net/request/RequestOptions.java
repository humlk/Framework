package com.sai.net.request;

import com.sai.net.cache.Cache;
import com.sai.net.http.Request;

/**
 * 网络请求的配置信息
 */
public class RequestOptions {

    /** 请求方法 Request.Method **/
    public int method = Request.Method.GET;
    /** 请求内容类型 **/
    public String contentType;
    /** 请求协议 Request.Protocol **/
    public String protocol;

    /** 请求超时 **/
    public int timeOut = 30 * 1000;

    /** 超时重连次数 **/
    public int maxRetries = 0;

    /** 是否缓存 默认不缓存**/
    public boolean shouldCache = false;
    /** 缓存模式 **/
    public int cacheModel = Cache.CacheModel.ONLY_REQUEST;
}
