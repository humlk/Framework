
package com.sai.net.http;

import com.sai.net.exception.SaiException;

/**
 * 网络请求接口
 */
public interface Network {

    public NetworkResponse performRequest(Request<?> request) throws SaiException;
}
