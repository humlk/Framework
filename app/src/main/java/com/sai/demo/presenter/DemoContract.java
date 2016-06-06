package com.sai.demo.presenter;


import com.sai.framework.presenter.BasePresenter;
import com.sai.framework.presenter.BaseView;

public interface DemoContract<V> {

    public interface CView extends BaseView {
        public void showLoadProgress();
        public void loadData(String data);
    }

    public abstract class Presenter extends BasePresenter<CView> {
        public abstract void load();
    }
}
