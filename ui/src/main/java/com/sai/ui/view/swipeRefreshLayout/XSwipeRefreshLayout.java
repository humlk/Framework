package com.sai.ui.view.swipeRefreshLayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

public class XSwipeRefreshLayout extends SwipeRefreshLayout{

    public XSwipeRefreshLayout(Context context){
        super(context);
        init();
    }
    public XSwipeRefreshLayout(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    private void init(){
//        setColorSchemeResources(R.color.refresh_bar_from, R.color.refresh_bar_to);
    }
}
