package com.sai.ui.view.recyclelistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public abstract class XRecyclerViewAdapter<T> extends RecyclerView.Adapter<XRecyclerViewHolder> {
    private static final int TYPE_HEADER_VIEW = 0x9527;
    private static final int TYPE_FOOTER_VIEW = 0x9528;
    private static final int TYPE_NORMAL_VIEW = 0;
    private View headerView = null;
    private View footerView = null;

    private List<T> mList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mItemLayoutId;
    private OnRecyclerItemClickListener mItemClickListener;


    public XRecyclerViewAdapter(Context context, int layoutId, List<T> list) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mList = list;
        this.mItemLayoutId = layoutId;
    }

    @Override
    public XRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_HEADER_VIEW) {
            return new XRecyclerViewHolder(headerView);
        }
        if (viewType == TYPE_FOOTER_VIEW) {
            return new XRecyclerViewHolder(footerView);
        }

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(mItemLayoutId, viewGroup, false);
        final XRecyclerViewHolder holder = new XRecyclerViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(XRecyclerViewAdapter.this, holder.itemView, getAdapterPosition(holder));
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(XRecyclerViewHolder recyclerViewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEADER_VIEW) {
            return ;
        }
        if (getItemViewType(position) == TYPE_FOOTER_VIEW) {
            return ;
        }
        int adapterPosition = getAdapterPosition(recyclerViewHolder);
        convert(recyclerViewHolder, mList.get(adapterPosition), adapterPosition);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mList != null) {
            count = mList.size();
        }
        count +=hasHeaderView()?1:0;
        count +=hasFooterView()?1:0;
        return count;
    }

    public T getObject(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    public int getAdapterPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        int realPosition = position;
        realPosition -= hasHeaderView()?1:0;
        return realPosition;
    }

    public int getItemViewType(int position) {
        if(hasHeaderView() && position == 0){
            return TYPE_HEADER_VIEW;
        }
        if(hasFooterView() && position == getItemCount() -1){
            return TYPE_FOOTER_VIEW;
        }
        return TYPE_NORMAL_VIEW;
    }

    public abstract void convert(XRecyclerViewHolder holder, T item, int position);


    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void add(T t, int position) {
        mList.add(position, t);
        notifyItemInserted(position);
    }

    public void update(int position) {
        notifyItemChanged(position);
    }

    public void addAll(List<T> list) {
        int position = getItemCount();
        for (T t : list) {
            add(t, position);
            position++;
        }
    }

    public void removeAll() {
        int position = getItemCount();
        for (T t : mList) {
            remove(position);
            position--;
        }
    }


    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        if (headerView == null) {
            return;
        }
        notifyDataSetChanged();
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
        if (footerView == null) {
            return;
        }
        notifyDataSetChanged();
    }


    public boolean hasHeaderView() {
        return null != headerView;
    }

    public boolean hasFooterView() {
        return null != footerView;
    }

    public interface OnRecyclerItemClickListener {
        public void onItemClick(XRecyclerViewAdapter adapter, View view, int position);
    }
}
