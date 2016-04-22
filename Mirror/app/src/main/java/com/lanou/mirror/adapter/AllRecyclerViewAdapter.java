package com.lanou.mirror.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.EveryGlassesActivity;
import com.lanou.mirror.bean.JSONAll;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;

import java.util.Random;

/**
 * Created by dllo on 16/4/5.
 */
public class AllRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private JSONAll jsonAll;

    private SpecialOnClick specialOnClick;
    private Random random=new Random();


    private ImageLoaderHelper imageLoaderHelper;
    public AllRecyclerViewAdapter(Context context, JSONAll jsonAll) {

            this.context = context;
            this.jsonAll = jsonAll;
            imageLoaderHelper=new ImageLoaderHelper();


    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView allImageView;
        TextView homepageBrand, homepagePrice, homepageProduct, homepageModel;
        RelativeLayout line ;
        int position;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            allImageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
            homepageBrand = (TextView) itemView.findViewById(R.id.homepage_brand);
            homepagePrice = (TextView) itemView.findViewById(R.id.homepage_price);
            homepageProduct = (TextView) itemView.findViewById(R.id.homepage_comefrom);
            homepageModel = (TextView) itemView.findViewById(R.id.homepage_modle);
            line = (RelativeLayout) itemView.findViewById(R.id.line);

            line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, EveryGlassesActivity.class);

                    intent.putExtra("goodsId",jsonAll.getData().getList().get(position).getData_info().getGoods_id());
                    intent.putExtra("picUrl", jsonAll.getData().getList().get(position).getData_info().getGoods_img());
                    intent.putExtra("position",position);



                    context.startActivity(intent);
                }
            });
        }
    }

    public class SpecialViewHolder extends RecyclerView.ViewHolder {
        TextView specialTitle;
        ImageView specialIv;

        public SpecialViewHolder(View itemView) {
            super(itemView);
            specialTitle = (TextView) itemView.findViewById(R.id.special_title);
            specialIv = (ImageView) itemView.findViewById(R.id.special_iv);
        }
    }


    @Override
    public int getItemViewType(int position) {

        return Integer.valueOf(jsonAll.getData().getList().get(position).getType());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

            switch (viewType) {

                case 1:

                    View homePageView = LayoutInflater.from(context).inflate(R.layout.item_homepagefragment_recyclerview, parent, false);
                    viewHolder = new HomePageViewHolder(homePageView);

                    break;
                case 2:
                    View specialView = LayoutInflater.from(context).inflate(R.layout.item_special, parent, false);
                    viewHolder = new SpecialViewHolder(specialView);
                    break;
            }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
            switch (viewType) {

                case 1:

                    HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;
                    imageLoaderHelper.loadImage(jsonAll.getData().getList().get(position).getData_info().getGoods_img(),homePageViewHolder.allImageView);
                    homePageViewHolder.homepageBrand.setText(jsonAll.getData().getList().get(position).getData_info().getBrand());
                    homePageViewHolder.homepageModel.setText(jsonAll.getData().getList().get(position).getData_info().getModel());
                    homePageViewHolder.homepageProduct.setText(jsonAll.getData().getList().get(position).getData_info().getProduct_area());
                    homePageViewHolder.homepagePrice.setText(jsonAll.getData().getList().get(position).getData_info().getGoods_price());
                    homePageViewHolder.position=position;
                    break;
                case 2:

                    break;
            }

    }

    @Override
    public int getItemCount() {
            return (jsonAll.getData().getList().size() ) > 0 ? jsonAll.getData().getList().size()  : 0;

    }

    interface SpecialOnClick {
        void specialOnClick();
    }


}
