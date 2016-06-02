package com.sai.net.http;

import android.os.SystemClock;

import com.sai.base.util.StreamUtil;
import com.sai.net.cache.Cache;
import com.sai.net.exception.AuthFailureError;
import com.sai.net.exception.SaiException;
import com.sai.net.exception.ClientError;
import com.sai.net.exception.NetworkError;
import com.sai.net.exception.NoConnectionError;
import com.sai.net.exception.ServerError;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BasicNetwork implements Network {

    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;

    private static int DEFAULT_POOL_SIZE = 4096;

    protected final HttpStack mHttpStack;

    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        mHttpStack = httpStack;
        mPool = pool;
    }


    /**
     * 网络请求
     *
     * @param request
     * @return
     * @throws SaiException
     */
    @Override
    public NetworkResponse performRequest(Request<?> request) throws SaiException {
        long requestStart = SystemClock.elapsedRealtime();
        //处理重发机制
        while (true) {
            HttpResponse httpResponse = null;
            byte[] responseContents = null;
            Map<String, String> responseHeaders = Collections.emptyMap();
            try {
                // 处理请求header
                Map<String, String> headers = new HashMap<String, String>();
                // 缓存相关的头部信息
                addCacheHeaders(headers, request.getCacheEntry());

                httpResponse = mHttpStack.performRequest(request, headers);
                //获取状态码
                int statusCode = httpResponse.getStatusCode();
                //响应的头部信息
                responseHeaders = httpResponse.getHeaders();

                /*   ----------------- 检查响应状态码 ------------------ */

                // 如果服务端返回304
                if (statusCode == HttpStatus.SC_NOT_MODIFIED) {
                    //检查本地的缓存
                    return new NetworkResponse(HttpStatus.SC_NOT_MODIFIED, request.getCacheEntry() == null ? null : request.getCacheEntry().data,
                            responseHeaders, true, SystemClock.elapsedRealtime() - requestStart);
                }

                if (statusCode < 200 || statusCode > 299) {
                    //转去后面的异常
                    throw new IOException();
                }

                //request自己想解析响应数据
                if(request.handleResponse(httpResponse)){
                    return new NetworkResponse(statusCode, responseContents, responseHeaders, false,
                            SystemClock.elapsedRealtime() - requestStart);
                }

                //读取响应body内容
                if (httpResponse.getContentStream() != null) {
                    responseContents = entityToBytes(httpResponse);
                } else {
                    responseContents = new byte[0];
                }

                return new NetworkResponse(statusCode, responseContents, responseHeaders, false,
                        SystemClock.elapsedRealtime() - requestStart);
//            } catch (SocketTimeoutException e) {
//                attemptRetryOnException("socket", request, new SaiException(
//                        new SocketTimeoutException("socket timeout")));
//            } catch (MalformedURLException e) {
//                attemptRetryOnException("connection", request,
//                        new SaiException("Bad URL " + request.getUrl(), e));
            } catch (IOException e) {
                int statusCode;
                if (httpResponse != null) {
                    statusCode = httpResponse.getStatusCode();
                } else {
                    throw new NoConnectionError(e);
                }
                NetworkResponse networkResponse;
                if (responseContents != null) {
                    networkResponse = new NetworkResponse(statusCode, responseContents,
                            responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
                    if (statusCode == HttpStatus.SC_UNAUTHORIZED ||
                            statusCode == HttpStatus.SC_FORBIDDEN) {
                        attemptRetryOnException("auth",
                                request, new AuthFailureError(networkResponse));
                    } else if (statusCode >= 400 && statusCode <= 499) {
                        // Don't retry other client errors.
                        throw new ClientError(networkResponse);
                    } else if (statusCode >= 500 && statusCode <= 599) {
                        if (request.shouldRetryServerErrors()) {
                            attemptRetryOnException("server",
                                    request, new ServerError(networkResponse));
                        } else {
                            throw new ServerError(networkResponse);
                        }
                    } else {
                        // 3xx? No reason to retry.
                        throw new ServerError(networkResponse);
                    }
                } else {
                    attemptRetryOnException("network", request, new NetworkError());
                }
            }
        }
    }

    private static void attemptRetryOnException(String logPrefix, Request<?> request,
                                                SaiException exception) throws SaiException {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        try {
            retryPolicy.retry(exception);
        } catch (SaiException e) {
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> headers, Cache.Entry entry) {
        if (entry == null) {
            return;
        }

        if (entry.etag != null) {
            headers.put("If-None-Match", entry.etag);
        }

        if (entry.lastModified > 0) {
            Date refTime = new Date(entry.lastModified);
            DateFormat format = SimpleDateFormat.getDateTimeInstance();
            headers.put("If-Modified-Since", format.format(refTime));
        }
    }

    private byte[] entityToBytes(HttpResponse httpResponse) throws IOException, ServerError {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(mPool, httpResponse.getContentLength());
        byte[] buffer = null;
        InputStream in = null;
        try {
            in = httpResponse.getContentStream();
            if (in == null) {
                throw new ServerError();
            }
            buffer = mPool.getBuf(1024);
            int count;
            while ((count = in.read(buffer)) != -1) {
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } finally {
            StreamUtil.close(in);
            mPool.returnBuf(buffer);
            bytes.close();
        }
    }
}
