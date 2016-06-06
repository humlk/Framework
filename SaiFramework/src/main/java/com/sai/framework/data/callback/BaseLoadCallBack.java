package com.sai.framework.data.callback;

import com.sai.net.request.OnResponseListener;


public class BaseLoadCallBack<T> implements OnResponseListener<T> {

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
    public void onProgressChanged(long downloadSize, long totalFileSize) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFinish() {

    }
}
