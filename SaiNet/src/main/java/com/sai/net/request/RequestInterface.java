package com.sai.net.request;

/**
 * 请求任务
 */
interface RequestInterface {

    interface Method{
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }


    /** 请求优先级 **/
    enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    interface Protocol{
        String HTTP = "http";
        String HTTPS = "https";
        String TCP = "tcp";
    }

//    public interface ContentType{
//        final String JSON = "json";
//        final String FORM = "form";
//        final String RESTFUL = "restful";
//    }


    interface RequestState{
        int NULL = -1;
        int SUCCESS = 0;
        int FAILED = 1;
        int RUNNING = 2;
        int COMPLATE = 4;
        int CANCEL = 5;
    }

    interface Model{
        /** 只请求网络 **/
        int ONLY_REQUEST = 0;
        /** 请求失败，读缓存 **/
        int REQUEST_FAILED_READ_CACHE = 1;
        /** 先读缓存没有缓存就去网络请求 **/
        int NO_CACHE_REQUEST = 2;
        /** 先读缓存，缓存过期就去网络请求 **/
        int EXPIRED_CACHE_REQUEST = 3;
    }
}
