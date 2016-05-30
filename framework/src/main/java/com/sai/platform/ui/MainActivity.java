package com.sai.platform.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sai.platform.R;
import com.sai.platform.base.BaseActivity;
import com.sai.platform.presenter.DemoContract;
import com.sai.platform.presenter.impl.DemoPresenter;

public class MainActivity extends BaseActivity implements DemoContract.BView{

    DemoPresenter mDemoPresenter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                doBusiness();
            }
        });

        mDemoPresenter = new DemoPresenter(this);
    }


    private void doBusiness(){
        mDemoPresenter.load(1);
    }

    @Override
    public void showLoadProgress() {

    }

    @Override
    public void loadData() {

    }
}
