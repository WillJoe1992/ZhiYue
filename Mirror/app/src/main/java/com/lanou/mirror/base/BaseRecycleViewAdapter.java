package com.lanou.mirror.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zouwei on 16/3/19.
 */
public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int headerItemCount;
    public int bottomItemCount;

    protected LayoutInflater inflater;
    protected Context context;

    protected List<T> list;

    public enum Type {
        ITEM_TYPE_HEADER,
        ITEM_TYPE_CONTENT,
        ITEM_TYPE_BOTTOM
    }


    public BaseRecycleViewAdapter(Context context) {
        this.inflater = inflater.from(context);

    }

    public void addData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    protected  boolean islength(){
        return list !=null && list.size()>0;
    }


    public void setHeaderItemCount(int headerItemCount) {
        this.headerItemCount = headerItemCount;
    }

    public void setBottomItemCount(int bottomItemCount) {
        this.bottomItemCount = bottomItemCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Type.ITEM_TYPE_HEADER.ordinal()) {
            return onCreateHeaderView(parent);
        } else if (viewType == Type.ITEM_TYPE_BOTTOM.ordinal()) {
            return onCreateBottomView(parent);
        } else {
            return onCreateContentView(parent);
        }
    }

    public abstract RecyclerView.ViewHolder onCreateHeaderView(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent);

    public abstract RecyclerView.ViewHolder onCreateContentView(ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == Type.ITEM_TYPE_HEADER.ordinal()) {
            onblidHeaderViewHolder(holder, position);
        } else if (getItemViewType(position) == Type.ITEM_TYPE_BOTTOM.ordinal()) {
            onblidBottomViewHolder( holder, position);
        }else{
            onblidContentViewHolder( holder, position);
        }
    }

    protected abstract void onblidHeaderViewHolder(RecyclerView.ViewHolder holder,int position);
    protected abstract void onblidBottomViewHolder(RecyclerView.ViewHolder holder,int position);
    protected abstract void onblidContentViewHolder(RecyclerView.ViewHolder holder,int position);


    @Override
    public int getItemCount() {
        return headerItemCount + bottomItemCount + getContentItemCount();
    }

    protected abstract int getContentItemCount();


    public boolean isHeaderView(int position) {
        return headerItemCount != 0 && position < headerItemCount;
    }

    public boolean isbottomView(int position) {
        return bottomItemCount != 0 && position < bottomItemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (headerItemCount != 0 && position < headerItemCount) {
            return Type.ITEM_TYPE_HEADER.ordinal();
        } else if (bottomItemCount != 0 && position < bottomItemCount) {
            return Type.ITEM_TYPE_BOTTOM.ordinal();
        } else {
            return Type.ITEM_TYPE_CONTENT.ordinal();
        }


    }
}
