package com.sai.net.http;

import com.sai.net.exception.BaseException;

/**
 * 响应分发
 */
public interface ResponseDelivery {

    public void postResponse(Request<?> request, Response<?> response);

    public void postError(Request<?> request, BaseException error);

    public void postCancel(Request<?> request);

    public void postProgress(Request<?> request, long downloadSize, long totalFileSize);
}
