package com.sai.framework.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        presenter = ClassUtil.getClassArgs(this,0);
        if(presenter != null && this instanceof BaseView){
            presenter.onViewAttached(this);
        }
    }

    protected P getPresenter(){
        return presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //关闭缓存
        //当activity被回收的时候，fragment不会被回收。
        // 等重新创建Activity的时候就会出现两个fragment导致错误。
        // 所以这里关闭缓存，不让其缓存fragment。
//        super.onSaveInstanceState(outState);
    }
}
