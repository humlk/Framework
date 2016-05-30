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

    public interface UrlType{
        final int restful = 1;
        final int form = 0;
    }


    public interface RequestState{
        final int NULL = -1;
        final int SUCCESS = 0;
        final int FAILED = 1;
        final int RUNNING = 2;
        final int COMPLATE = 4;
        final int CANCEL = 5;
    }
}
