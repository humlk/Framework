package com.sai.net.cache;


import android.os.Handler;
import android.os.Looper;

import com.sai.net.http.NetworkResponse;
import com.sai.net.http.Request;
import com.sai.net.http.Response;

public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    public ClearCacheRequest(Cache cache, Runnable callback) {
        super(null,null,null);
        mCache = cache;
        mCallback = callback;
    }

    @Override
    public boolean isCanceled() {
        // This is a little bit of a hack, but hey, why not.
        mCache.clear();
        if (mCallback != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postAtFrontOfQueue(mCallback);
        }
        return true;
    }

    @Override
    public Request.Priority getPriority() {
        return Request.Priority.IMMEDIATE;
    }

    @Override
    public Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(Object response) {
    }
}
