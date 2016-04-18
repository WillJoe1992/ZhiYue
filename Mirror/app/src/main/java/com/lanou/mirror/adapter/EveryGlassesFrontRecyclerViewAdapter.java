package com.lanou.mirror.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONAllson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/4/1.
 */
public class EveryGlassesFrontRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<JSONAllson.DataEntity.GoodsDataEntity> data;

    /**
     * 自定义 添加数据方法
     */
    public void addData(List<JSONAllson.DataEntity.GoodsDataEntity> data) {
        this.data = data;
        notifyDataSetChanged();  // 通知适配器  数据是实时更新的
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_everyglasses_frontrecyclerview_itemfirst, parent, false);
            return new FirstViewHolder(v);
        } else {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_everyglasses_frontrecyclerview_itemothers, parent, false);
            return new OtherViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstViewHolder) {




            ((FirstViewHolder) holder).tvCity.setText(data.get(position).getCountry());
            ((FirstViewHolder) holder).tvFrom.setText(data.get(position).getLocation());
            ((FirstViewHolder) holder).tvEnglishFrom.setText(data.get(position).getEnglish());
            ((FirstViewHolder) holder).tvContent.setText(data.get(position).getIntroContent());
        } else if (holder instanceof OtherViewHolder) {


            ((OtherViewHolder) holder).tvTitle.setText(data.get(position).getName());
            ((OtherViewHolder) holder).tvContent.setText(data.get(position).getIntroContent());
        }
    }

    /**
     * 返回数据个数
     */
    @Override
    public int getItemCount() {
        return data.size()!=0?data.size():0;
    }


    class OtherViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvContent;

        public OtherViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemother_title);
            tvContent = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemother_content);
        }

    }

    class FirstViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCity, tvFrom, tvEnglishFrom, tvContent;
        private ImageView iv;

        //  缓存类 构造方法
        public FirstViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemfirst_city);
            tvFrom = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemfirst_from);
            tvEnglishFrom = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemfirst_englisrfrom);
            tvContent = (TextView) itemView.findViewById(R.id.everyglasses_frontrecyclerview_itemfirst_content);
        }

    }

}
