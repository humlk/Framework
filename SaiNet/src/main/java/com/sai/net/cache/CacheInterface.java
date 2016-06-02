package com.sai.net.cache;

interface CacheInterface {

    public interface Model{
        /** 只请求网络 **/
        final int ONLY_REQUEST = 0;
        /** 只读缓存 **/
        final int ONLY_READ_CACHE = 1;
        /** 请求失败，读缓存 **/
        final int REQUEST_FAILED_READ_CACHE = 2;
        /** 没有缓存就去网络请求 **/
        final int NO_CACHE_REQUEST = 3;
    }
}
