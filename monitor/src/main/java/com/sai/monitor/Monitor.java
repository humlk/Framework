package com.sai.monitor;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


public class Monitor {

    private Application mApplication;

    private RefWatcher mRefWatcher;

    private static class Instance{
        public static Monitor instance = new Monitor();
    }

    public static Monitor get(){
        return Instance.instance;
    }

    public void init(Application application){
        mApplication = application;

    }

    public Application getApplication(){
        return mApplication;
    }

    /**
     * 监控内存泄露
     * @return
     */
    public RefWatcher getRefWatcher() {
        if(mRefWatcher == null){
            mRefWatcher = LeakCanary.install(mApplication);
        }
        return mRefWatcher;
    }


    /**
     * 监控内存泄露
     * @return
     */
//    public RefWatcher openMemoryLeaks(){
//       return getRefWatcher();
//    }
}
