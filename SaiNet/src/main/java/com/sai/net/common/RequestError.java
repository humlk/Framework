package com.sai.net.common;

/**
 * 错误信息
 * S
 * Q/S/O  request/response/其他
 * xxxx  错误code
 */
public class RequestError {

    public static String CODE_DEFAULT = "SDEF";
    public static String MSG_DEFAULT = "unknow";

    public static String CODE_NET_NULL = "SQ0001";
    public static String MSG_NET_NULL = "无网络";

    public static String CODE_DATA_PARSE_ERROR = "SO0001";
    public static String MSG_DATA_PARSE_ERROR = "数据解析失败";
}
