package com.sai.demo.ui;

import android.support.v7.app.AppCompatActivity;

import com.sai.demo.MyApplication;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Created by huajie on 2016/5/16.
 */
public class BaseActivity extends AppCompatActivity{


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);//内存泄露检测
    }
}
