package com.sai.framework;

import android.app.Application;
import android.content.Context;

import com.sai.net.SaiNet;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by huajie on 2016/5/16.
 */
public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        refWatcher = LeakCanary.install(this);
        SaiNet.init(this);
        SaiNet.getConfig().setDebug(true);
    }

    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public static MyApplication get(){
        return instance;
    }
}
