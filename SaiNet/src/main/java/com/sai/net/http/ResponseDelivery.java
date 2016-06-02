package com.sai.net.http;

import com.sai.net.exception.SaiException;

/**
 * 响应分发
 */
public interface ResponseDelivery {

    public void postResponse(Request<?> request, Response<?> response);

    public void postError(Request<?> request, SaiException error);

    public void postCancel(Request<?> request);

    public void postProgress(Request<?> request, long downloadSize, long totalFileSize);
}
