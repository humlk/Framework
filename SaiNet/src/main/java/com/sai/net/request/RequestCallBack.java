package com.sai.net.request;


public class RequestCallBack<T> implements OnResponseListener<T> {


    @Override
    public void onPerStart() {

    }

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onError(String code, String msg) {

    }

    @Override
    public void onProgressChanged(long downloadSize, long fileSize) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFinish() {

    }
}
