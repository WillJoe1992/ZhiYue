package com.lanou.mirror.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zouwei on 16/3/19.
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> list;

    protected abstract int getItemResource();

    protected abstract View getItemView(int position, ViewHolder holder, View convertView);

    public BaseListViewAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getItemResource(), null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return getItemView(position, holder, convertView);
    }

    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (v == null) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }
}
