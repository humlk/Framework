package com.sai.demo;

import android.app.Activity;
import android.app.Application;
import android.os.StrictMode;

import com.sai.net.SaiNet;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application{

    private static MyApplication instance;
    private String TAG = "monitor";
    public static List<Activity> mActivityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SaiNet.init(this);
        SaiNet.getHelper().setDebug(true);
//        Monitor.install(this);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    public static MyApplication get(){
        return instance;
    }
}
