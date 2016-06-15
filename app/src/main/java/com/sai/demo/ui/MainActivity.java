package com.sai.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sai.demo.R;
import com.sai.demo.fragment.HomeFragment;
import com.sai.ui.manager.FragmentHelper;
import com.sai.ui.view.listview.XListViewAdapter;
import com.sai.ui.view.listview.XListViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    @Bind(R.id.nav_right_view)
    ListView mRightView;

    XListViewAdapter mNavAdapter;
    List<NavEvent> mNavEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setupDrawerContent();

        HomeFragment fragment = HomeFragment.newInstance();
        new FragmentHelper().setFragmentManager(getSupportFragmentManager()).addFragment(fragment)
                .setLayoutId(R.id.content_fragment).show();
    }


    private void setupDrawerContent() {
        //left
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //right
        mNavEventList = new ArrayList<>();
        for(int i=1;i<5;i++){
            NavEvent navEvent = new NavEvent();
            navEvent.mName = "name"+i;
            mNavEventList.add(navEvent);
        }

        mNavAdapter = new XListViewAdapter<NavEvent>(this,R.layout.item_main_list,mNavEventList) {
            @Override
            protected void convert(XListViewHolder holder, NavEvent o, int position) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(o.mName);
            }
        };

        mRightView.setAdapter(mNavAdapter);
        mRightView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawer(Gravity.RIGHT);
    }


    class NavEvent{
        public String mName;
        public int mIcon;
        public Intent mIntent;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }
}
