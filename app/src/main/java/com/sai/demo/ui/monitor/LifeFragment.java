package com.sai.demo.ui.monitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LifeFragment extends Fragment{

    private String TAG = "monitor";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, " LifeFragment onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " LifeFragment onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, " LifeFragment onCreateView");
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, " LifeFragment onActivityCreated");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " LifeFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, " LifeFragment onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " LifeFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, " LifeFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, " LifeFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " LifeFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, " LifeFragment onDetach");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, " LifeFragment onSaveInstanceState");
    }

    /**
     * 用户可见时调用，但是这个函数在fragment执行oncreate时也会isVisibleToUser=true，
     * 大概的显示流程:startFragment->setUserVisibleHint(false)->onCreate->setUserVisibleHint(true)(view is null)->onCreateView->onActivityCreated->onResume->end
     * 等下次进来就会调用setUserVisibleHint(true)(view not null).
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, " LifeFragment setUserVisibleHint");
        //用户是否可见
        if(getView() != null && isVisibleToUser){
            //用户可见
        }

    }
}
