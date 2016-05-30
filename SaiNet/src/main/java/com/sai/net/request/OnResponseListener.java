package com.sai.net.request;


public interface OnResponseListener<T> {

    public void onPerStart();

    public void onSuccess(T response);

    public void onError(String code, String msg);

    public void onProgressChanged(long downloadSize, long totalFileSize);

    public void onCancel();

    public void onFinish();
}
