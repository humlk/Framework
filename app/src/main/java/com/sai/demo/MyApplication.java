package com.sai.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sai.demo.utils.LogUtils;
import com.sai.monitor.Monitor;
import com.sai.monitor.monitor.LifeCycleMonitor;
import com.sai.net.SaiNet;

public class MyApplication extends Application{

    private static MyApplication instance;
    private String TAG = "monitor";

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SaiNet.init(this);
        SaiNet.getHelper().setDebug(true);

        Monitor.MonitorPolicy monitor = new Monitor.MonitorPolicy.Builder().activityLife(new LifeCycleMonitor.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                LogUtils.d(TAG, activity.getLocalClassName() + " onActivityDestroyed");
            }
        }).fragmentLife(new LifeCycleMonitor.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentAttached(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentAttached");
            }

            @Override
            public void onFragmentCreated(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentCreated");
            }

            @Override
            public void onFragmentCreateViewed(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentCreateViewed");
            }

            @Override
            public void onFragmentActivityCreated(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentActivityCreated");
            }

            @Override
            public void onFragmentStarted(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentStarted");
            }

            @Override
            public void onFragmentResumed(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentResumed");
            }

            @Override
            public void setUserVisibleHint(Fragment fragment, boolean isVisibleToUser) {
                LogUtils.d(TAG, fragment.getClass() + " setUserVisibleHint");
            }

            @Override
            public void onFragmentPaused(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentPaused");
            }

            @Override
            public void onFragmentStoped(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentStoped");
            }

            @Override
            public void onFragmentDestroyViewed(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentDestroyViewed");
            }

            @Override
            public void onFragmentDestroyed(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentDestroyed");
            }

            @Override
            public void onFragmentDetached(Fragment fragment) {
                LogUtils.d(TAG, fragment.getClass() + " onFragmentDetached");
            }

        }).build();

        Monitor.install(this);
        Monitor.execute(monitor);
    }

    public static MyApplication get(){
        return instance;
    }
}
