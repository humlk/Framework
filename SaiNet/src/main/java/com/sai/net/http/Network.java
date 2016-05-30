
package com.sai.net.http;

import com.sai.net.exception.BaseException;

/**
 * 网络请求接口
 */
public interface Network {

    public NetworkResponse performRequest(Request<?> request) throws BaseException;
}
