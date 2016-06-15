package com.sai.framework.base;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sai.framework.base.loader.PresenterHolder;
import com.sai.framework.base.loader.PresenterLoader;

/**
 * presenter
 */
public class Presenter1Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<PresenterHolder>{

    private PresenterLoader mPresenterLoader = null;

    private String tag = "PresenterActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "activity onCreate");
        getLoaderManager().initLoader(getLoadId(),null,this);
    }

    protected int getLoadId(){

        return hashCode();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.d(tag,"loaderManager onCreateLoader");
        //初始化loader
        mPresenterLoader = new PresenterLoader(this);
        initPresenter(mPresenterLoader.getHolder());
        return mPresenterLoader;
    }

    protected void initPresenter(PresenterHolder holder){

    }

    @Override
    public void onLoadFinished(Loader loader, PresenterHolder holder) {
        //loader业务处理完成
        Log.d(tag, "loaderManager onLoadFinished");

//        initPresenter(holder);
    }


    /**
     * onDestroy后调用
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader loader) {
        //loader被销毁了
        Log.d(tag, "loaderManager onLoaderReset");
        mPresenterLoader = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "activity onDestroy");
    }

}
