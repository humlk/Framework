package com.sai.security.store.base;

/**
 * 对存储数据加密
 */
public abstract class SecurityStore extends JsonStore {

    protected byte[] en(byte[] data) {

        return null;
    }

    protected byte[] dn(byte[] data) {
       return null;
    }
}
