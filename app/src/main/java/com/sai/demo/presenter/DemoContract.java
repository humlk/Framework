package com.sai.demo.presenter;



public interface DemoContract {

    public interface BView extends BaseView{
        public void showLoadProgress();
        public void loadData(String data);
    }

    public abstract class Presenter extends BasePresenter{
        public abstract void load();
    }
}
