package com.sai.demo.ui.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sai.demo.R;
import com.sai.demo.ui.BaseActivity;
import com.sai.monitor.service.LogCatDetectService;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 功能说明： </br>
 *
 * @author: huajie
 * @version: 1.0
 * @date: 2016/6/22
 * @Copyright (c) 2016. huajie Inc. All rights reserved.
 */
public class MonitorActivity extends BaseActivity {


    @Bind(R.id.btn_start)
    Button mBtnStart;
    @Bind(R.id.btn_stop)
    Button mBtnStop;

    @Bind(R.id.edt_key)
    EditText mEdtKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_monitor);
    }


    @OnClick(R.id.btn_start)
    public void onBtnStart() {
        String tag = mEdtKey.getText().toString();
        String[] tags = tag.split(" ");
        Intent intent = new Intent(getActivity(), LogCatDetectService.class);
        intent.putExtra(LogCatDetectService.KEY_TAGS, tags);
        startService(intent);
    }

    @OnClick(R.id.btn_stop)
    public void onBtnStop() {
        Intent intent = new Intent(getActivity(), LogCatDetectService.class);
        stopService(intent);
    }
}
