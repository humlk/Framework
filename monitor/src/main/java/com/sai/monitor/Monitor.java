package com.sai.monitor;

import android.app.Application;

import com.sai.monitor.monitor.LifeCycleMonitor;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 *
 */
public class Monitor {

    private static Application mApplication;
    private static RefWatcher mRefWatcher;

    private static Monitor instance;

    private Monitor() {

    }

    public static void install(Application application){
        mApplication = application;
    }

    public static void execute(MonitorPolicy monitorPolicy){
        if (mApplication == null || monitorPolicy == null) {
            return;
        }
        //life
        startLife(monitorPolicy);
        leak(monitorPolicy);
    }

    private static void startLife(MonitorPolicy monitorPolicy){
        if(monitorPolicy.mActivityLifecycleCallbacks != null || monitorPolicy.mFragmentLifecycleCallbacks != null){
            LifeCycleMonitor.get().init(mApplication);
            LifeCycleMonitor.get().registerFragmentLifecycle(monitorPolicy.mFragmentLifecycleCallbacks);
            LifeCycleMonitor.get().registerActivityLifecycle(monitorPolicy.mActivityLifecycleCallbacks);
            LifeCycleMonitor.get().start();
        }
    }

    private static void memory(MonitorPolicy monitorPolicy){

    }

    private static void leak(MonitorPolicy monitorPolicy){
        if(monitorPolicy.mLeak){
            if(mRefWatcher == null){
                mRefWatcher = LeakCanary.install(mApplication);
            }
        }
    }

    /**
     * 监控内存泄露
     *
     * @return
     */
    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }


    public static class MonitorPolicy {
        private boolean mLeak = false;
        LifeCycleMonitor.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
        LifeCycleMonitor.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

        public MonitorPolicy(Builder builder) {
            mLeak = builder.mLeak;
            mActivityLifecycleCallbacks = builder.mActivityLifecycleCallbacks;
            mFragmentLifecycleCallbacks = builder.mFragmentLifecycleCallbacks;
        }

        public static class Builder {
            boolean mLeak = false;
            boolean mMemory = false;
            LifeCycleMonitor.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
            LifeCycleMonitor.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

            public Builder activityLife(LifeCycleMonitor.ActivityLifecycleCallbacks callbacks) {
                mActivityLifecycleCallbacks = callbacks;
                return this;
            }

            public Builder fragmentLife(LifeCycleMonitor.FragmentLifecycleCallbacks callbacks) {
                mFragmentLifecycleCallbacks = callbacks;
                return this;
            }

            public Builder leak() {
                mLeak = true;
                return this;
            }

            public Builder memory() {
                mMemory = true;
                return this;
            }


            public MonitorPolicy build() {
                return new MonitorPolicy(this);
            }
        }
    }
}
