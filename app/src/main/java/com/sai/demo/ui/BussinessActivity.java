package com.sai.demo.ui;

import android.os.Bundle;

import com.sai.demo.presenter.DemoContract;
import com.sai.demo.presenter.impl.DemoPresenter;
import com.sai.framework.base.PresenterActivity;


public class BussinessActivity extends PresenterActivity<DemoPresenter> implements DemoContract.CView{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().load("");
    }

    @Override
    public void showLoadProgress() {

    }

    @Override
    public void loadData(String data) {

    }

}
