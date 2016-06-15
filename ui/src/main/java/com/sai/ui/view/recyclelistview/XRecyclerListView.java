package com.sai.ui.view.recyclelistview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class XRecyclerListView extends RecyclerView{
    ScrollToLastCallBack mScrollToLastCallBack;

    public XRecyclerListView(Context context) {
        super(context);
        init();
    }

    public XRecyclerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init(){
        setOrientation(LinearLayoutManager.VERTICAL);
        setItemAnimator(new DefaultItemAnimator());
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mScrollToLastCallBack == null) {
                    return;
                }
                //最后可见的条目
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                //总条目
                int totalItemCount = getAdapter().getItemCount();
                //向上滑动到最底部
//                if (lastVisibleItem > totalItemCount - 3 && lastVisibleItem < totalItemCount - 1 && lastVisibleItem
//                        <= totalItemCount) {
                if(lastVisibleItem <= totalItemCount){
                    mScrollToLastCallBack.onScrollToLast();
                }
            }
        });
    }

    public void setOrientation(int orientation){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(orientation);
        setLayoutManager(linearLayoutManager);
    }

//    public void showDefaultDivider(){
//        addItemDecoration(new DividerItemDecoration.Builder(getContext()).size(2).color
//                (getResources().getColor(R.color.list_divider_color)).build());
//    }

    public void setScrollToLastCallBack(ScrollToLastCallBack callBack) {
        mScrollToLastCallBack = callBack;
    }

    public interface ScrollToLastCallBack {
        public void onScrollToLast();
    }
}
