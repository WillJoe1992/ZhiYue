package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/3/31.
 */
public class SelectTitleRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private SelectTitleRecyclerBean selectTitleRecyclerBean;
    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans ;

    public SelectTitleRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHome = LayoutInflater.from(context).inflate(R.layout.item_select_title_recyclerview, null);
        return new HomePageViewHolder(viewHome);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return selectTitleRecyclerBeans.size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lineAll;
        TextView tvAll;
        ImageView underLineAll;

        public HomePageViewHolder(View itemView) {
            super(itemView);
           // lineAll =itemView.findViewById()

        }
    }
}
