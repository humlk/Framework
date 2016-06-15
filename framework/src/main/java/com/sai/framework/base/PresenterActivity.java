package com.sai.framework.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sai.base.util.ClassUtil;
import com.sai.framework.presenter.BasePresenter;
import com.sai.framework.presenter.BaseView;

/**
 * presenter
 */
public class PresenterActivity<P extends BasePresenter> extends AppCompatActivity{

    private String tag = "PresenterActivity";

    private P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "activity onCreate");

        presenter = ClassUtil.getClassArgs(this,0);
        if(presenter != null && this instanceof BaseView){
            presenter.onViewAttached(this);
        }
    }


    protected P getPresenter(){
        return presenter;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "activity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "activity onDestroy");

        presenter.onViewDetached();
    }

}
