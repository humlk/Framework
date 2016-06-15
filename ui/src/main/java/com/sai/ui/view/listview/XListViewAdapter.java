package com.sai.ui.view.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.List;

public abstract class XListViewAdapter<T> extends BaseAdapter {

    private List<T> mList;
    private Context mContext;
    private boolean mCache;
    private int mLayoutId;

    public XListViewAdapter(Context context, int layoutId, List<T> list) {
        mContext = context;
        mLayoutId = layoutId;
        mList = list;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        XListViewHolder holder = null;
        if (convertView == null || !mCache) {
            convertView = LinearLayout.inflate(mContext, mLayoutId, null);
            holder = new XListViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (XListViewHolder) convertView.getTag();
        }
        convert(holder, (T) getItem(position), position);

        return convertView;
    }

    protected abstract void convert(XListViewHolder holder, T t, int position);
}
