package com.sai.platform.base;


import android.os.Bundle;

import com.sai.platform.presenter.base.BasePresenter;

public class PresenterActivity<P extends BasePresenter> extends BaseActivity{

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

}
