package com.sai.net.http;

import com.sai.net.exception.SaiException;

/**
 * 重发机制
 */
public interface RetryPolicy {


    public int getCurrentTimeout();

    public int getCurrentRetryCount();

    public void retry(SaiException error) throws SaiException;
}
