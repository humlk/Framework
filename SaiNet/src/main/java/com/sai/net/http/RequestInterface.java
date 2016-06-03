package com.sai.net.http;

/**
 * 请求任务
 */
interface RequestInterface{

    public interface Method{
        final int GET = 0;
        final int POST = 1;
        final int PUT = 2;
        final int DELETE = 3;
        final int HEAD = 4;
        final int OPTIONS = 5;
        final int TRACE = 6;
        final int PATCH = 7;
    }


    /** 请求优先级 **/
    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public interface Protocol{
        final String HTTP = "http";
        final String HTTPS = "https";
        final String TCP = "tcp";
    }

//    public interface ContentType{
//        final String JSON = "json";
//        final String FORM = "form";
//        final String RESTFUL = "restful";
//    }


    public interface RequestState{
        final int NULL = -1;
        final int SUCCESS = 0;
        final int FAILED = 1;
        final int RUNNING = 2;
        final int COMPLATE = 4;
        final int CANCEL = 5;
    }

    public interface Model{
        /** 只请求网络 **/
        final int ONLY_REQUEST = 0;
        /** 请求失败，读缓存 **/
        final int REQUEST_FAILED_READ_CACHE = 1;
        /** 先读缓存没有缓存就去网络请求 **/
        final int NO_CACHE_REQUEST = 2;
        /** 先读缓存，缓存过期就去网络请求 **/
        final int EXPIRED_CACHE_REQUEST = 3;
    }
}
