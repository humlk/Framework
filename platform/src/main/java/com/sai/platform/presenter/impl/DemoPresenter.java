package com.sai.platform.presenter.impl;

import com.sai.platform.data.callback.BaseLoadCallBack;
import com.sai.platform.data.remote.DemoLogic;
import com.sai.platform.presenter.DemoContract;

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
    public void load(int pageIndex) {

        mView.showLoadProgress();
        mDemoLogic.requestPage(pageIndex, new BaseLoadCallBack<String>(){
            @Override
            public void onSuccess(String response) {
                super.onSuccess(response);
                mView.loadData();
            }
        });
    }
}
