package com.sai.demo.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.sai.monitor.Monitor;
import com.squareup.leakcanary.RefWatcher;

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

    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = Monitor.get().getRefWatcher();
        refWatcher.watch(this);//内存泄露检测
    }
}
