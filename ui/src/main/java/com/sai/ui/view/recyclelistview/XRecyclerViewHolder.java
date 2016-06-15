package com.sai.ui.view.recyclelistview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


public class XRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray viewmap;
    private View convertView;

    public XRecyclerViewHolder(View itemView) {
        super(itemView);
        this.convertView = itemView;
        viewmap = new SparseArray();
    }

    public <T extends View> T getView(int id) {
        if (viewmap.get(id) != null) {
            return (T) viewmap.get(id);
        } else {
            T tv = (T) convertView.findViewById(id);
            viewmap.put(id, tv);
            return tv;
        }
    }
}
