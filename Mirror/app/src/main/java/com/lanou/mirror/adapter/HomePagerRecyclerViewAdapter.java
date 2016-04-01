package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou.mirror.R;

/**
 * Created by wyc on 16/3/30.
 */
public class HomePagerRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;

    public HomePagerRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHome = LayoutInflater.from(context).inflate(R.layout.item_homepagefragment_recyclerview, null);
        return new HomePageViewHolder(viewHome);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView ic;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            ic = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
        }
    }
}
