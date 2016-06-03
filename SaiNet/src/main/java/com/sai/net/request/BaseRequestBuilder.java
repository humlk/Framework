package com.sai.net.request;

import com.sai.base.util.StringUtil;
import com.sai.net.http.Request;
import com.sai.net.request.http.HttpRequest;
import com.sai.net.util.LogUtil;

import java.util.Map;

public abstract class BaseRequestBuilder<T> {
    protected String mUrl = null;
    protected RequestParams mRequestParams = null;
    protected Object mReqTag = null;
    protected OnResponseListener<T> mCallBackListener = null;
//    protected WeakReference<OnResponseListener> mCallBackListener;
    protected RequestOptions mRequestOptions;

    public BaseRequestBuilder() {

    }

    public BaseRequestBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public BaseRequestBuilder options(RequestOptions options) {
        mRequestOptions = options;
        return this;
    }

    public BaseRequestBuilder params(RequestParams httpParams) {
        this.mRequestParams = httpParams;
        return this;
    }

    public BaseRequestBuilder tag(Object reqTag) {
        this.mReqTag = reqTag;
        return this;
    }

    public BaseRequestBuilder callBack(OnResponseListener<T> callBack) {
        this.mCallBackListener = callBack;
//        this.mCallBackListener = new WeakReference<OnResponseListener>(callBack);
        return this;
    }

    public HttpRequest<T> build() {
        if (mRequestOptions == null) {
            mRequestOptions = new RequestOptions();
        }

        //先处理url
        String url = getUrl();
        if (mRequestOptions.method == Request.Method.GET && mRequestParams != null) {
            url = StringUtil.toString(url, "?", mRequestParams.getUrlParams());
        }

        LogUtil.d("reuqest url= "+url);
        HttpRequest mRequest = createRequest(url);

        if (mReqTag == null) {
            mReqTag = System.currentTimeMillis() + "";
        }
        mRequest.setTag(mReqTag);

        return mRequest;
    }


    protected abstract HttpRequest createRequest(String url);


    protected String getUrl() {
        return Config.fillUrl(mUrl);
    }

    /**
     * A/B/C
     */
    public static class RestfulUrlBuilder {

        private String mUrl;
        private StringBuilder urlParams = new StringBuilder();

        public RestfulUrlBuilder url(String url) {
            mUrl = url;
            return this;
        }

        public RestfulUrlBuilder add(Map<String, Object> valMap) {
            if (valMap == null) {
                return this;
            }
            for (Map.Entry<String, Object> entry : valMap.entrySet()) {
                add(entry.getValue());
            }
            return this;
        }

        public RestfulUrlBuilder add(Object val) {
            urlParams.append("/").append(val);
            return this;
        }

        public String build() {
            if(StringUtil.isEmpty(mUrl)){
                return urlParams.toString();
            }
            if(mUrl.endsWith("/")){
                urlParams.delete(0,1);
            }
            return urlParams.insert(0,mUrl).toString();
        }
    }
}
