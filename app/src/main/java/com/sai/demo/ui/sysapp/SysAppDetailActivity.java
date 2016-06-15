package com.sai.demo.ui.sysapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.sai.demo.R;
import com.sai.demo.model.sysapp.AppInfo;
import com.sai.demo.ui.BaseActivity;

import butterknife.Bind;

public class SysAppDetailActivity extends BaseActivity {

    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.cv_detail)
    CardView mCvDetail;
    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;
    @Bind(R.id.tv_detail)
    TextView mTvDetail;

    private AppInfo mAppInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sysapp_detail);

        ComponentName componentName = getIntent().getParcelableExtra("componentName");
        if (componentName != null) {
            Intent intent = new Intent();
            intent.setComponent(componentName);
            ResolveInfo app = getPackageManager().resolveActivity(intent, 0);
            try {
                mAppInfo = new AppInfo(this, app);

            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        mTvDetail.setText(mAppInfo.getComponentInfo());
    }


}
