package com.sai.demo.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sai.demo.R;
import com.sai.demo.utils.ToastUtil;
import com.sai.ui.view.recyclelistview.XRecyclerListView;
import com.sai.ui.view.recyclelistview.XRecyclerViewAdapter;
import com.sai.ui.view.recyclelistview.XRecyclerViewHolder;
import com.sai.ui.view.swipeRefreshLayout.XSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements XRecyclerViewAdapter.OnRecyclerItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recyclerListView)
    XRecyclerListView mRecyclerListView;
    @Bind(R.id.swipeRefreshLayout)
    XSwipeRefreshLayout mSwipeRefreshLayout;


    List<DoorInfo> mDoorInfoList = new ArrayList<>();
    XRecyclerViewAdapter mViewAdapter;

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDoorInfoList();
        mViewAdapter = new XRecyclerViewAdapter<DoorInfo>(getActivity(),R.layout.item_home_list,mDoorInfoList) {
            @Override
            public void convert(XRecyclerViewHolder holder, DoorInfo item, int position) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.label);
            }
        };
        mRecyclerListView.setAdapter(mViewAdapter);
        mViewAdapter.setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ToastUtil.show("refresh data");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDoorInfoList() {
        Intent myIntent = new Intent();
        myIntent.setAction("android.intent.action.myDoor");
        PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> infoList = pm.queryIntentActivities(myIntent, 0);

        for (ResolveInfo info : infoList) {
            addItem(info.activityInfo.loadLabel(pm) + "", activityIntent(info.activityInfo.packageName, info.activityInfo.name));
        }
    }

    private void addItem(String label, Intent intent) {
        DoorInfo doorInfo = new DoorInfo();
        doorInfo.label = label;
        doorInfo.intent = intent;
        mDoorInfoList.add(doorInfo);
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    @Override
    public void onItemClick(XRecyclerViewAdapter adapter, View view, int position) {
        DoorInfo doorInfo = (DoorInfo)adapter.getObject(position);
        startActivity(doorInfo.intent);
    }

    class DoorInfo {
        public String label;
        public Intent intent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
