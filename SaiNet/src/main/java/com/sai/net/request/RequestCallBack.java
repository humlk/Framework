package com.sai.net.request;


import com.sai.net.util.LogUtil;

public class RequestCallBack<T> implements OnResponseListener<T> {


    @Override
    public void onPerStart() {

    }

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onError(String code, String msg) {

        LogUtil.d("业务失败，"+msg);
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
