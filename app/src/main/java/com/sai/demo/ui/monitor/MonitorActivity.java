package com.sai.demo.ui.monitor;

import android.os.Bundle;

import com.sai.demo.R;
import com.sai.demo.ui.BaseActivity;
import com.sai.framework.manager.FragmentHelper;

/**
 * 功能说明： </br>
 *
 * @author: huajie
 * @version: 1.0
 * @date: 2016/6/22
 * @Copyright (c) 2016. huajie Inc. All rights reserved.
 */
public class MonitorActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitor);

        new FragmentHelper().setLayoutId(R.id.fragment).setFragmentManager(getSupportFragmentManager())
        .addFragment(new LifeFragment()).show();
    }
}
