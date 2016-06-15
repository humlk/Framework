package com.sai.framework.presenter;


public abstract class BasePresenter<V> {

    public abstract void onViewAttached(V view);

    public abstract void onViewDetached();
}
