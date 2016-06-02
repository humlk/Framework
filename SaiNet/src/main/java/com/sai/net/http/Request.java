package com.sai.net.http;

import com.sai.net.cache.Cache;
import com.sai.net.exception.SaiException;
import com.sai.net.model.ErrorMessage;
import com.sai.net.request.OnResponseListener;
import com.sai.net.request.RequestOptions;
import com.sai.net.util.SaiUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

/**
 * 请求任务
 * @param <T>
 */
public abstract class Request<T> implements RequestInterface, Comparable<Request<T>>{

    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    private final String mUrl;

    /** 优先级 **/
    private Integer mSequence;

    protected RequestQueue mRequestQueue;

    private boolean mCanceled = false;

    private boolean mResponseDelivered = false;

    private boolean mShouldRetryServerErrors = false;

    private RetryPolicy mRetryPolicy;

    private Cache.Entry mCacheEntry = null;

    private Object mTag;

    private RequestOptions mRequestOptions;
    protected OnResponseListener mResponseListener;

    public Request(String url, RequestOptions options, OnResponseListener listener){
        mUrl = url;
        mRequestOptions = options;
        mResponseListener = listener;
        mRetryPolicy = new DefaultRetryPolicy(options.timeOut,options.maxRetries,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }


    public int getMethod() {
        return mRequestOptions.method;
    }

    public Request<?> setTag(Object tag) {
        mTag = tag;
        return this;
    }

    public Object getTag() {
        return mTag;
    }

    public RequestOptions getRequestOptions(){
        return mRequestOptions;
    }

    public int getCacheModel(){
        return mRequestOptions.cacheModel;
    }
    /**
     * 重发策略
     *
     * @return Request
     */
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        mRetryPolicy = retryPolicy;
        return this;
    }


    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
        return this;
    }

    public final Request<?> setSequence(int sequence) {
        mSequence = sequence;
        return this;
    }

    public final int getSequence() {
        if (mSequence == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        }
        return mSequence;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(Cache.Entry entry) {
        mCacheEntry = entry;
        return this;
    }

    public Cache.Entry getCacheEntry() {
        return mCacheEntry;
    }

    public void cancel() {
        mCanceled = true;
    }

    public boolean isCanceled() {
        return mCanceled;
    }

    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    protected Map<String, String> getParams(){
        return null;
    }


    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }


    public byte[] getBody(){
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    public final boolean shouldCache() {
        return mRequestOptions.shouldCache;
    }

    public final Request<?> setShouldRetryServerErrors(boolean shouldRetryServerErrors) {
        mShouldRetryServerErrors = shouldRetryServerErrors;
        return this;
    }

    public final boolean shouldRetryServerErrors() {
        return mShouldRetryServerErrors;
    }


    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return mRequestOptions.timeOut;
    }

    public RetryPolicy getRetryPolicy() {
        return mRetryPolicy;
    }

    public void markDelivered() {
        mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return mResponseDelivered;
    }

    /**
     * 解析网络响应数据
     * @param response
     * @return
     */
    abstract public Response<T> parseNetworkResponse(NetworkResponse response);

    protected SaiException parseNetworkError(SaiException error) {
        return error;
    }

    abstract protected void deliverResponse(T response);


    public void finish() {
        if (mRequestQueue != null) {
            mRequestQueue.finish(this);
        }
    }

    public void deliverFinish() {
        if (mResponseListener != null) {
            mResponseListener.onFinish();
        }
    }

    public void deliverError(SaiException error) {
        if (mResponseListener != null) {
            ErrorMessage errorMessage = SaiUtil.netRequestError(error);
            mResponseListener.onError(errorMessage.getCode(), errorMessage.getMsg());
        }
    }

    public void deliverError(String code, String message) {
        if (mResponseListener != null) {
            mResponseListener.onError(code, message);
        }
    }

    public void deliverPerStart(){
        if(mResponseListener != null){
            mResponseListener.onPerStart();
        }
    }

    public void deliverProgress(long downloadSize, long totalFileSize){
        if(mResponseListener != null){
            mResponseListener.onProgressChanged(downloadSize, totalFileSize);
        }
    }

    public void deliverCancel(){
        if(mResponseListener != null){
            mResponseListener.onCancel();
        }
    }


    @Override
    public int compareTo(Request<T> other) {
        Priority left = this.getPriority();
        Priority right = other.getPriority();

        return left == right ?
                this.mSequence - other.mSequence :
                right.ordinal() - left.ordinal();
    }

    public boolean handleResponse(HttpResponse httpResponse){

        return false;
    }
}
