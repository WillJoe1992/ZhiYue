package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.tool.GetScreenHeight;

/**
 * Created by SAZ 16/3/29.
 */
public class SpecialPictureAdapter extends RecyclerView.Adapter<SpecialPictureAdapter.SpecialPictureHolder> {
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    JSONSpecial jsonSpecial;
    int MainPosition;
    public SpecialPictureAdapter(Context context, JSONSpecial jsonSpecial,int MainPosition) {
        this.context = BaseApplication.getContext();
        this.jsonSpecial = jsonSpecial;
        this.MainPosition=MainPosition;
    }

    @Override
    public SpecialPictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载有内容的布局
        mHeaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_content_picture_item, parent, false);
        SpecialPictureHolder specialPictureHolder=null;
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            specialPictureHolder = new SpecialPictureHolder(mHeaderView);
            ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureItemRv.getLayoutParams();
            layoutParams.height= GetScreenHeight.getScreenHeight(context);
            specialPictureHolder.specialContentPictureItemRv.setLayoutParams(layoutParams);
        } else {
            //加载空的布局
            View nullView = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_content_picture_null_item, parent, false);
            specialPictureHolder=new SpecialPictureHolder(nullView);
            ViewGroup.LayoutParams layoutParams = specialPictureHolder.specialContentPictureNullItemView.getLayoutParams();
            layoutParams.height= GetScreenHeight.getScreenHeight(context);
            specialPictureHolder.specialContentPictureNullItemView.setLayoutParams(layoutParams);
        }
        return specialPictureHolder;
    }

    @Override
    public void onBindViewHolder(SpecialPictureHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEADER){
            holder.specialContentItemShareTvContent.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSubTitle());
            holder.specialContentItemShareTvTitle.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSmallTitle());
            holder.specialContentItemShareTvBigTitle.setText(jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().get(position).getSmallTitle());
        }
    }

    @Override
    public int getItemCount() {
        return jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array()!=null?
                jsonSpecial.getData().getList().get(MainPosition).getStory_data().getText_array().size():0;
    }

    class SpecialPictureHolder extends RecyclerView.ViewHolder {
        RelativeLayout specialContentPictureItemRv;
        LinearLayout specialContentPictureNullItemView;

        TextView specialContentItemShareTvTitle,specialContentItemShareTvBigTitle,specialContentItemShareTvContent;
        public SpecialPictureHolder(View itemView) {
            super(itemView);
            specialContentPictureItemRv = (RelativeLayout) itemView.findViewById(R.id.special_content_picture_item_rv);
            specialContentPictureNullItemView = (LinearLayout) itemView.findViewById(R.id.special_content_picture_null_item_view);
            specialContentItemShareTvTitle= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_title);
            specialContentItemShareTvBigTitle= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_big_title);
            specialContentItemShareTvContent= (TextView) itemView.findViewById(R.id.special_content_item_share_tv_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }
}