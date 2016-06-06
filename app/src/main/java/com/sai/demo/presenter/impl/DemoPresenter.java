package com.sai.demo.presenter.impl;

import com.sai.demo.data.remote.DemoLogic;
import com.sai.demo.http.RequestCallBack;
import com.sai.demo.presenter.DemoContract;

public class DemoPresenter extends DemoContract.Presenter {

    private DemoContract.CView mView;

    private DemoLogic mDemoLogic;

    public DemoPresenter(){
        mDemoLogic = new DemoLogic();
    }

    @Override
    public void load() {

        mDemoLogic.requestPage(new RequestCallBack<String>(){
            @Override
            public void onPerStart() {
                mView.showLoadProgress();
            }

            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                mView.loadData(response);
            }

            @Override
            public void onError(String code, String msg) {
                super.onError(code, msg);
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    @Override
    public void onViewAttached(DemoContract.CView view) {
        mView = view;
    }

    @Override
    public void onViewDetached() {
        mView = null;
        mDemoLogic.stopRequest();
        mDemoLogic = null;
    }
}
