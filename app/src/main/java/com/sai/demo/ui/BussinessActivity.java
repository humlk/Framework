package com.sai.demo.ui;

import android.os.Bundle;

import com.sai.demo.presenter.DemoContract;
import com.sai.demo.presenter.impl.DemoPresenter;


public class BussinessActivity extends BaseActivity implements DemoContract.BView{

    private DemoPresenter mDemoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDemoPresenter = new DemoPresenter(this);
        mDemoPresenter.load();
    }

    @Override
    public void showLoadProgress() {

    }

    @Override
    public void loadData(String data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mDemoPresenter.destroy();
    }
}
