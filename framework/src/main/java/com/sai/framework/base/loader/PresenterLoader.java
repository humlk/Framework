package com.sai.framework.base.loader;

import android.content.Context;
import android.content.Loader;
import android.util.Log;

/**
 * loader 是同步的
 */
public class PresenterLoader extends Loader<PresenterHolder> {

    private String TAG = "PresenterLoader";

    private PresenterHolder mHolder = null;

    public PresenterLoader(Context context) {
        super(context);
        mHolder = new PresenterHolder();
        Log.d(TAG, "Loader init");
    }


    /**
     * Activity/Fragment执行onStart时调用
     */
    @Override
    protected void onStartLoading() {
        Log.d(TAG, "Loader onStartLoading");
        super.onStartLoading();
        //初始化presenter

//
//        mHolder.create();

        deliverResult(mHolder);
    }

    @Override
    protected void onForceLoad() {
        Log.d(TAG, "Loader onForceLoad");
        super.onForceLoad();

    }

    /**
     * Activity/Fragment执行onStop时调用
     */
    @Override
    protected void onStopLoading() {
        Log.d(TAG, "Loader onStopLoading");
        super.onStopLoading();

    }

    public PresenterHolder getHolder(){
        return mHolder;
    }

    /**
     * 分发数据，该方法回回调loadManager.CallBack中的onLoadFinished
     * @param data
     */
    @Override
    public void deliverResult(PresenterHolder data) {
        Log.d(TAG, "Loader deliverResult");
        super.deliverResult(data);
        //分发处理结果 -> LoaderCallbacks.onLoadFinished(loader, T t);

    }

    /**
     * loader注销，activity/fragment onStop时调用
     * loader onStopLoading 之后调用
     */
    @Override
    protected void onReset() {
        Log.d(TAG, "Loader onReset");
        super.onReset();

        mHolder.detach();
        mHolder.clear();
        mHolder = null;
    }

//    public <P extends BasePresenter> P createPresenter(P p){
//        mHolder.add(p);
//        return p;
//    }
}
