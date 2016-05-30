//package com.sai.net.task;
//
//
//import com.sai.net.SaiNet;
//import com.sai.net.SaiVolley;
//import com.sai.net.http.Request;
//import com.sai.net.http.RequestQueue;
//
//public class HttpTask extends BaseTask {
//
//    private Request request;
//    private Object mTag;
//
//    private RequestQueue requestQueue = null;
//
//    public void bindRequest(Request request) {
//        bindRequest(request, "");
//    }
//
//    public void bindRequest(Request request, Object tag) {
//        this.request = request;
//        this.mTag = tag;
//        mTag = (tag != null)?tag:(System.currentTimeMillis() + "");
//        this.request.setTag(mTag);
//    }
//
//    public void bindRequest(Request request, RequestQueue queue) {
//        bindRequest(request);
//        this.requestQueue = queue;
//    }
//
//    public Object getTag() {
//        return mTag;
//    }
//
//    public Request getRequest() {
//        return request;
//    }
//
//    public boolean isCanceled() {
//        return request.isCanceled();
//    }
//
//    public boolean isRunning() {
//        if (getState() == SaiNet.RequestState.RUNNING) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        if (requestQueue != null) {
//            requestQueue.add(request);
//        } else {
//            SaiVolley.get().getRequestQueue().add(request);
//        }
//    }
//
//    public void cancel() {
//        cancel(mTag);
//    }
//
//    public void cancel(Object tag) {
//        if (isRunning()) {
//            if (requestQueue != null) {
//                requestQueue.cancelAll(tag);
//            } else {
//                SaiVolley.get().getRequestQueue().cancelAll(tag);
//            }
//
//            super.cancel();
//        }
//    }
//}
