package com.sai.monitor.monitor;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * 功能说明： </br>
 *
 * @author: huajie
 * @version: 1.0
 * @date: 2016/6/22
 * @Copyright (c) 2016. huajie Inc. All rights reserved.
 */
public class LifeCycleMonitor {

    private static final int INITIALIZING = 0;     // Not yet created.
    private static final int CREATED = 1;          // Created.
    private static final int ACTIVITY_CREATED = 2; // The activity has finished its creation.
    private static final int STOPPED = 3;          // Fully created, not started.
    private static final int STARTED = 4;          // Created and started, not resumed.
    private static final int RESUMED = 5;          // Created started and resumed.

    private Application mApplication = null;

    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    private Application.ActivityLifecycleCallbacks mApplicationCallbacks;

    private FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;

    private static LifeCycleMonitor instance = new LifeCycleMonitor();

    private LifeCycleMonitor(){};

    public static LifeCycleMonitor get(){
        return instance;
    }

    public void init(Application application){
        mApplication = application;
    }

    public void registerActivityLifecycle(ActivityLifecycleCallbacks lifecycleCallbacks){
        mActivityLifecycleCallbacks = lifecycleCallbacks;
    }

    public void start(){
        if(mApplicationCallbacks != null){
            mApplication.registerActivityLifecycleCallbacks(mApplicationCallbacks);
            return;
        }
        //4.0以上直接注册,4.0下要在activity中手动调用
        mApplicationCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                dispatchActivityCreated(activity, savedInstanceState);
                dispatchFragment(activity, CREATED);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                dispatchActivityStarted(activity);
                dispatchFragment(activity, STARTED);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                dispatchActivityResumed(activity);
                dispatchFragment(activity, RESUMED);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                onActivityPaused(activity);
                dispatchFragment(activity, STARTED);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                onActivityStopped(activity);
                dispatchFragment(activity, STOPPED);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                onActivitySaveInstanceState(activity, outState);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                onActivityDestroyed(activity);
                dispatchFragment(activity, INITIALIZING);
            }
        };
        mApplication.registerActivityLifecycleCallbacks(mApplicationCallbacks);
    }

    public void unRegisterActivityLifecycle(){
        mApplication.unregisterActivityLifecycleCallbacks(mApplicationCallbacks);
    }

    public void registerFragmentLifecycle(FragmentLifecycleCallbacks callbacks){
        mFragmentLifecycleCallbacks = callbacks;
    }

    public void unRegisterFragmentLifecycle(){
        mFragmentLifecycleCallbacks = null;
    }


    private void dispatchFragment(Activity activity, int newState){
        if(mFragmentLifecycleCallbacks == null){
            return;
        }
        //support
        if(activity instanceof FragmentActivity){
            ragmentV4(activity, newState);
        }else{

        }
    }

    private void ragmentV4(Activity activity, int newState){
        FragmentActivity fa = (FragmentActivity)activity;
        FragmentManager fm = fa.getSupportFragmentManager();

        List<Fragment> fragmentList = fm.getFragments();
        if(fragmentList == null || fragmentList.size() == 0){
            return;
        }

//        int mCurState = (Integer)ReflectUtil.getDeclaredField(fm, "mCurState");
//        if(mCurState == newState){
//            return;
//        }

    }

    /*------------------- dispatchActivity ---------------------------*/

    private void dispatchActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
        }
    }
    private void dispatchActivityStarted(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityStarted(activity);
        }
    }
    private void dispatchActivityResumed(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityResumed(activity);
        }
    }
    private void dispatchActivityPaused(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityPaused(activity);
        }
    }

    private void dispatchActivityStopped(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityStopped(activity);
        }
    }

    private void dispatchActivityDestroyed(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityDestroyed(activity);
        }
    }

    private void dispatchActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);
        }
    }

    /*------------------- dispatchFragment ---------------------------*/

    private void dispatchFragmentAttached(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentAttached(fragment);
        }
        fragment = null;
    }
    private void dispatchFragmentCreated(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentCreated(fragment);
        }
        fragment = null;
    }
    private void dispatchFragmentActivityCreated(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentActivityCreated(fragment);
        }
    }
    private void dispatchFragmentStarted(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentStarted(fragment);
        }
    }
    private void dispatchFragmentResumed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentResumed(fragment);
        }
    }
    private void dispatchFragmentPaused(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentPaused(fragment);
        }
    }
    private void dispatchFragmentStoped(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentStoped(fragment);
        }
    }
    private void dispatchFragmentDestroyViewed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDestroyViewed(fragment);
        }
    }
    private void dispatchFragmentDestroyed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDestroyed(fragment);
        }
    }
    private void dispatchFragmentDetached(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDetached(fragment);
        }
    }

    private void dispatchFragmentCreateViewed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentCreateViewed(fragment);
        }
    }

    private void dispatchFragmentUserVisibleHint(Fragment fragment, boolean isVisibleToUser){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.setUserVisibleHint(fragment, isVisibleToUser);
        }
    }


    public static interface ActivityLifecycleCallbacks{
        void onActivityCreated(Activity activity, Bundle savedInstanceState);
        void onActivityStarted(Activity activity);
        void onActivityResumed(Activity activity);
        void onActivityPaused(Activity activity);
        void onActivityStopped(Activity activity);
        void onActivitySaveInstanceState(Activity activity, Bundle outState);
        void onActivityDestroyed(Activity activity);
    }

    public static interface FragmentLifecycleCallbacks{

        public void onFragmentAttached(Fragment fragment);
        public void onFragmentCreated(Fragment fragment);
        public void onFragmentCreateViewed(Fragment fragment);
        public void onFragmentActivityCreated(Fragment fragment);
        public void onFragmentStarted(Fragment fragment);
        public void onFragmentResumed(Fragment fragment);
        public void setUserVisibleHint(Fragment fragment, boolean isVisibleToUser);
        public void onFragmentPaused(Fragment fragment);
        public void onFragmentStoped(Fragment fragment);
        public void onFragmentDestroyViewed(Fragment fragment);
        public void onFragmentDestroyed(Fragment fragment);
        public void onFragmentDetached(Fragment fragment);
    }

    public static class ActivityLifecycle implements ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }


    public static class FragmentLifecycle implements FragmentLifecycleCallbacks{

        @Override
        public void onFragmentAttached(Fragment fragment) {

        }

        @Override
        public void onFragmentCreated(Fragment fragment) {

        }

        @Override
        public void onFragmentCreateViewed(Fragment fragment) {
        }

        @Override
        public void onFragmentActivityCreated(Fragment fragment) {

        }

        @Override
        public void onFragmentStarted(Fragment fragment) {

        }

        @Override
        public void onFragmentResumed(Fragment fragment) {

        }

        @Override
        public void setUserVisibleHint(Fragment fragment, boolean isVisibleToUser) {

        }

        @Override
        public void onFragmentPaused(Fragment fragment) {

        }

        @Override
        public void onFragmentStoped(Fragment fragment) {

        }

        @Override
        public void onFragmentDestroyViewed(Fragment fragment) {

        }

        @Override
        public void onFragmentDestroyed(Fragment fragment) {

        }

        @Override
        public void onFragmentDetached(Fragment fragment) {

        }
    }
}
