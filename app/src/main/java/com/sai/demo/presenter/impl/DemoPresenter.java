package com.sai.demo.presenter.impl;

import com.sai.demo.data.callback.BaseLoadCallBack;
import com.sai.demo.data.remote.DemoLogic;
import com.sai.demo.presenter.DemoContract;
import com.sai.demo.utils.LogUtils;

public class DemoPresenter extends DemoContract.Presenter {

    private DemoContract.BView mView;

    private DemoLogic mDemoLogic;

    public DemoPresenter(DemoContract.BView view){
        mView = view;
        mDemoLogic = new DemoLogic();
    }

    @Override
    public void destroy() {
        super.destroy();
        mView = null;
        mDemoLogic.stopRequest();
        mDemoLogic = null;
    }

    @Override
    public void load() {

        mView.showLoadProgress();
        mDemoLogic.requestPage(new BaseLoadCallBack<String>(){
            @Override
            public void onSuccess(String response) {
                LogUtils.d("response :" +response);
                super.onSuccess(response);
                mView.loadData();
            }

            @Override
            public void onError(String code, String msg) {
                super.onError(code, msg);
                LogUtils.d("response error");
            }
        });
    }
}
