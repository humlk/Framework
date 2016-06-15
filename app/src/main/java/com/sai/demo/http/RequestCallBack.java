//package com.sai.demo.http;
//
//import com.sai.framework.data.callback.BaseLoadCallBack;
//import com.sai.demo.utils.LogUtils;
//
//
//public class RequestCallBack<T> extends BaseLoadCallBack<T>{
//
//    @Override
//    public void onSuccess(T response) {
//        super.onSuccess(response);
//        LogUtils.d("response onSuccess ->" + response);
//    }
//
//    @Override
//    public void onError(String code, String msg) {
//        super.onError(code, msg);
//        LogUtils.d("request onError ->" + msg);
//    }
//
//    @Override
//    public void onPerStart() {
//        super.onPerStart();
//        LogUtils.d("request onPerStart");
//    }
//
//    @Override
//    public void onCancel() {
//        super.onCancel();
//        LogUtils.d("request onCancel");
//    }
//
//    @Override
//    public void onFinish() {
//        super.onFinish();
//        LogUtils.d("request onFinish");
//    }
//}
