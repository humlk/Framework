package com.sai.framework.data.remote;

import com.sai.net.SaiNet;
import com.sai.net.request.http.HttpRequest;


public abstract class NetRequestLogic {

    public void startRequest(HttpRequest request) {
        SaiNet.addRequest(request);
    }

    public void stopRequest(HttpRequest request) {
        SaiNet.cancelRequest(request);
    }

    public void stopRequest(){
        SaiNet.cancelAllRequest(getTag());
    }

    protected Object getTag(){
        return getClass().getName();
    }
}
