package com.sai.demo.presenter;


import com.sai.framework.presenter.BasePresenter;
import com.sai.framework.presenter.BaseView;

public interface DemoContract<V> {

    interface CView extends BaseView {
        void showLoadProgress();
        void loadData(String data);
    }

    abstract class Presenter extends BasePresenter<CView> {
        public abstract void load(String id);
    }
}
