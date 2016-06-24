//package com.sai.framework.base;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
//public class LifeFragment extends Fragment{
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        LifeCycleManager.get().dispatchFragmentAttached(this,context);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        LifeCycleManager.get().dispatchFragmentCreated(this, savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LifeCycleManager.get().dispatchFragmentCreateViewed(this, savedInstanceState);
//        return super.onCreateView(inflater,container,savedInstanceState);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        LifeCycleManager.get().dispatchFragmentActivityCreated(this, savedInstanceState);
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        LifeCycleManager.get().dispatchFragmentStarted(this);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        LifeCycleManager.get().dispatchFragmentResumed(this);
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        LifeCycleManager.get().dispatchFragmentPaused(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        LifeCycleManager.get().dispatchFragmentStoped(this);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        LifeCycleManager.get().dispatchFragmentDestroyViewed(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        LifeCycleManager.get().dispatchFragmentDestroyed(this);
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        LifeCycleManager.get().dispatchFragmentDetached(this);
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        LifeCycleManager.get().dispatchFragmentSaveInstanceStated(this, outState);
//    }
//
//    /**
//     * 用户可见时调用，但是这个函数在fragment执行oncreate时也会isVisibleToUser=true，
//     * 大概的显示流程:startFragment->setUserVisibleHint(false)->onCreate->setUserVisibleHint(true)(view is null)->onCreateView->onActivityCreated->onResume->end
//     * 等下次进来就会调用setUserVisibleHint(true)(view not null).
//     *
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        //用户是否可见
//        if(getView() != null && isVisibleToUser){
//            //用户可见
//        }
//
//    }
//}
