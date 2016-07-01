package com.sai.monitor;

import android.app.Application;

import com.sai.monitor.agent.life.LifeCycleAgent;

/**
 *
 */
public class Monitor {

    private static Application mApplication;

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
            LifeCycleAgent.get().init(mApplication);
            LifeCycleAgent.get().registerFragmentLifecycle(monitorPolicy.mFragmentLifecycleCallbacks);
            LifeCycleAgent.get().registerActivityLifecycle(monitorPolicy.mActivityLifecycleCallbacks);
            LifeCycleAgent.get().start();
        }
    }

    private static void memory(MonitorPolicy monitorPolicy){
        //内存使用
    }

    private static void leak(MonitorPolicy monitorPolicy){
        //内存泄露
        if(monitorPolicy.mLeak){

        }
    }

    public static class MonitorPolicy {
        private boolean mLeak = false;
        LifeCycleAgent.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
        LifeCycleAgent.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

        public MonitorPolicy(Builder builder) {
            mLeak = builder.mLeak;
            mActivityLifecycleCallbacks = builder.mActivityLifecycleCallbacks;
            mFragmentLifecycleCallbacks = builder.mFragmentLifecycleCallbacks;
        }

        public static class Builder {
            boolean mLeak = false;
            boolean mMemory = false;
            LifeCycleAgent.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;
            LifeCycleAgent.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

            public Builder activityLife(LifeCycleAgent.ActivityLifecycleCallbacks callbacks) {
                mActivityLifecycleCallbacks = callbacks;
                return this;
            }

            public Builder fragmentLife(LifeCycleAgent.FragmentLifecycleCallbacks callbacks) {
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
