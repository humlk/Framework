package com.sai.demo;

import android.app.Application;

import com.sai.monitor.Monitor;
import com.sai.net.SaiNet;

public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SaiNet.init(this);
        SaiNet.getHelper().setDebug(true);

        Monitor.get().init(this);
    }

    public static MyApplication get(){
        return instance;
    }
}
