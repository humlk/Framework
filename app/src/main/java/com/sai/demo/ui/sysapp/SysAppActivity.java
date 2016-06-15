package com.sai.demo.ui.sysapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sai.demo.R;
import com.sai.demo.model.sysapp.AppInfo;
import com.sai.demo.ui.BaseActivity;
import com.sai.demo.utils.ToastUtil;
import com.sai.ui.view.recyclelistview.XRecyclerListView;
import com.sai.ui.view.recyclelistview.XRecyclerViewAdapter;
import com.sai.ui.view.recyclelistview.XRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SysAppActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.recyclerListView)
    XRecyclerListView mRecyclerListView;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;

    private List<AppInfo> mAppInfoList = new ArrayList<>();

    private XRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sysapp);

        initData();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(mMainContent, "i am snackbar");
            }
        });
    }

    private void initData() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> ril = getPackageManager().queryIntentActivities(mainIntent, 0);

        try {
            for (ResolveInfo ri : ril) {
                mAppInfoList.add(new AppInfo(this, ri));
            }
        } catch (PackageManager.NameNotFoundException e) {
        }

//        Collections.sort(mAppInfoList);

        mAdapter = new XRecyclerViewAdapter<AppInfo>(this,R.layout.item_sysapp_list,mAppInfoList) {
            @Override
            public void convert(XRecyclerViewHolder holder, AppInfo item, int position) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getName());

                ImageView ivIcon = holder.getView(R.id.iv_icon);
                ivIcon.setImageDrawable(item.getIcon());
            }
        };
        mRecyclerListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new XRecyclerViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(XRecyclerViewAdapter adapter, View view, int position) {
                AppInfo appInfo = (AppInfo)adapter.getObject(position);
                Intent intent = new Intent(getActivity(), NestActivity.class);
                intent.putExtra("componentName",appInfo.getComponentName());
                startActivity(intent);
            }
        });
    }

}
