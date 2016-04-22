package com.lanou.mirror.adapter;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONAllson;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/4/1.
 */
public class EveryGlassesBackRecyclerViewAdapter extends  RecyclerView.Adapter<EveryGlassesBackRecyclerViewAdapter.EveryGlassesBackRecyclerViewHolder> {

    private AnimationDrawable animationDrawable;

    private List<JSONAllson.DataEntity.DesignDesEntity> data =new ArrayList<>();

    /**
     * 自定义 添加数据方法
     */
    public void addData(List<JSONAllson.DataEntity.DesignDesEntity> data) {
        this.data = data;
        notifyDataSetChanged();  // 通知适配器  数据是实时更新的
    }

    @Override
    public EveryGlassesBackRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_everyglasses_backrecyclerview_item, null);
        return new EveryGlassesBackRecyclerViewHolder(v);
    }



    @Override
    public void onBindViewHolder(EveryGlassesBackRecyclerViewHolder holder, int position) {

        ImageLoaderHelper imageLoaderHelper=new ImageLoaderHelper();
        imageLoaderHelper.loadImage(data.get(position).getImg(),holder.iv);
    }


    /**
     * 返回数据个数
     */
    @Override
    public int getItemCount() {


        return data.size() > 0 ? data.size() : 0;
    }



    class EveryGlassesBackRecyclerViewHolder extends RecyclerView.ViewHolder  {

        private ImageView iv;


        //  缓存类 构造方法
        public EveryGlassesBackRecyclerViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.everyglasses_backrecyclerview_item_iv);
            iv.setImageResource(R.drawable.loading);
            animationDrawable = (AnimationDrawable) iv.getDrawable();
            animationDrawable.start();

        }

    }

}
