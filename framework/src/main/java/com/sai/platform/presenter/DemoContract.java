package com.sai.platform.presenter;

import com.sai.platform.presenter.base.BasePresenter;
import com.sai.platform.presenter.base.BaseView;


public interface DemoContract {

    public interface BView extends BaseView{
        public void showLoadProgress();
        public void loadData();
    }

    public abstract class Presenter extends BasePresenter{
        public abstract void load(int pageIndex);
    }
}
