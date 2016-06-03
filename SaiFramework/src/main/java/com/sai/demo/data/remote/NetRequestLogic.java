package com.sai.demo.data.remote;

import com.sai.net.SaiNet;
import com.sai.net.request.http.HttpRequest;


public abstract class NetRequestLogic {
//    private List<HttpRequest> mHttpRequestList = new ArrayList<>();

    public void startRequest(HttpRequest request) {
//        synchronized (mHttpRequestList) {
//            mHttpRequestList.add(request);
//        }
        SaiNet.addRequest(request);
    }

    public void stopRequest(HttpRequest request) {
        SaiNet.cancelRequest(request);
//        synchronized (mHttpRequestList) {
//            mHttpRequestList.remove(request);
//        }
    }

    public void stopRequest(){
        SaiNet.cancelAllRequest(getTag());
    }

    protected Object getTag(){
        return getClass().getName();
    }

//    public synchronized void stopRequest() {
//
//        for (HttpRequest request : mHttpRequestList) {
//            SaiNet.cancelRequest(request);
//        }
//        mHttpRequestList.clear();
//
//    }
}
