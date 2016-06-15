package com.sai.framework.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sai.base.util.ClassUtil;
import com.sai.framework.presenter.BasePresenter;
import com.sai.framework.presenter.BaseView;

public class PresenterFragment<P extends BasePresenter> extends Fragment{

    private P presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = ClassUtil.getClassArgs(this, 0);
        if(presenter != null && this instanceof BaseView){
            presenter.onViewAttached(this);
        }
    }

    protected P getPresenter(){
        return presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }
}
