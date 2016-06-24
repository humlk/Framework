package com.sai.demo.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity{

    protected Activity getActivity(){
        return this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

}
