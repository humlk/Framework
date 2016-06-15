package com.sai.framework.lifecycle;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class LifeCycleManager {


    private Application mApplication = null;

    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    private Application.ActivityLifecycleCallbacks mApplicationCallbacks;

    private FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;


    private static LifeCycleManager instance = null;

    public static LifeCycleManager get(){
        if(instance == null){
            instance = new LifeCycleManager();
        }
        return instance;
    }

    public void registerActivityLifecycle(Application application, ActivityLifecycleCallbacks lifecycleCallbacks){
        mActivityLifecycleCallbacks = lifecycleCallbacks;
        mApplication = application;
        //4.0以上直接注册,4.0下要在activity中手动调用
        mApplicationCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                dispatchActivityCreated(activity, savedInstanceState);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                dispatchActivityStarted(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                dispatchActivityResumed(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                onActivityPaused(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                onActivityStopped(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                onActivitySaveInstanceState(activity, outState);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                onActivityDestroyed(activity);
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


    /*------------------- dispatchActivity ---------------------------*/

    public void dispatchActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityCreated(activity, savedInstanceState);
        }
    }
    public void dispatchActivityStarted(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityStarted(activity);
        }
    }
    public void dispatchActivityResumed(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityResumed(activity);
        }
    }
    public void dispatchActivityPaused(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityPaused(activity);
        }
    }

    public void dispatchActivityStopped(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityStopped(activity);
        }
    }

    public void dispatchActivityDestroyed(Activity activity) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivityDestroyed(activity);
        }
    }

    public void dispatchActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(mActivityLifecycleCallbacks != null){
            mActivityLifecycleCallbacks.onActivitySaveInstanceState(activity, outState);
        }
    }

    /*------------------- dispatchFragment ---------------------------*/

    public void dispatchFragmentAttached(Fragment fragment, Context context){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentAttached(fragment, context);
        }
        fragment = null;
    }
    public void dispatchFragmentCreated(Fragment fragment, Bundle savedInstanceState){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentCreated(fragment, savedInstanceState);
        }
        fragment = null;
    }
    public void dispatchFragmentActivityCreated(Fragment fragment, Bundle savedInstanceState){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentActivityCreated(fragment, savedInstanceState);
        }
    }
    public void dispatchFragmentStarted(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentStarted(fragment);
        }
    }
    public void dispatchFragmentResumed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentResumed(fragment);
        }
    }
    public void dispatchFragmentPaused(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentPaused(fragment);
        }
    }
    public void dispatchFragmentStoped(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentStoped(fragment);
        }
    }
    public void dispatchFragmentDestroyViewed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDestroyViewed(fragment);
        }
    }
    public void dispatchFragmentDestroyed(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDestroyed(fragment);
        }
    }
    public void dispatchFragmentDetached(Fragment fragment){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentDetached(fragment);
        }
    }
    public void dispatchFragmentSaveInstanceStated(Fragment fragment, Bundle bundle){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentSaveInstanceStated(fragment, bundle);
        }
    }

    public void dispatchFragmentCreateViewed(Fragment fragment, Bundle savedInstanceState){
        if(mFragmentLifecycleCallbacks != null){
            mFragmentLifecycleCallbacks.onFragmentCreateViewed(fragment,savedInstanceState);
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

        public void onFragmentAttached(Fragment fragment, Context context);
        public void onFragmentCreated(Fragment fragment, Bundle savedInstanceState);
        public View onFragmentCreateViewed(Fragment fragment, Bundle savedInstanceState);
        public void onFragmentActivityCreated(Fragment fragment, Bundle savedInstanceState);
        public void onFragmentStarted(Fragment fragment);
        public void onFragmentResumed(Fragment fragment);
        public void onFragmentPaused(Fragment fragment);
        public void onFragmentStoped(Fragment fragment);
        public void onFragmentDestroyViewed(Fragment fragment);
        public void onFragmentDestroyed(Fragment fragment);
        public void onFragmentDetached(Fragment fragment);
        public void onFragmentSaveInstanceStated(Fragment fragment, Bundle outState);
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
        public void onFragmentAttached(Fragment fragment, Context context) {

        }

        @Override
        public void onFragmentCreated(Fragment fragment, Bundle savedInstanceState) {

        }

        @Override
        public View onFragmentCreateViewed(Fragment fragment, Bundle savedInstanceState) {
            return null;
        }

        @Override
        public void onFragmentActivityCreated(Fragment fragment, Bundle savedInstanceState) {

        }

        @Override
        public void onFragmentStarted(Fragment fragment) {

        }

        @Override
        public void onFragmentResumed(Fragment fragment) {

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

        @Override
        public void onFragmentSaveInstanceStated(Fragment fragment, Bundle outState) {

        }
    }

}
