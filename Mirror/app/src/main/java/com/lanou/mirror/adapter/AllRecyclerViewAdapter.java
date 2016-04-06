package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONAll;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.bean.JSONSpecial;
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
    private NetImageLoader netImageLoader;
    public AllRecyclerViewAdapter(Context context, JSONAll jsonAll) {

            this.context = context;
            this.jsonAll = jsonAll;
            netImageLoader=new NetImageLoader();



    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView ic;
        TextView homepageBrand, homepagePrice, homepageProduct, homepageModel;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            ic = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
            homepageBrand = (TextView) itemView.findViewById(R.id.homepage_brand);
            homepagePrice = (TextView) itemView.findViewById(R.id.homepage_price);
            homepageProduct = (TextView) itemView.findViewById(R.id.homepage_comefrom);
            homepageModel = (TextView) itemView.findViewById(R.id.homepage_modle);
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
        Log.d("ShowNewstabsonAdapter", "viewType:" + viewType);


            switch (viewType) {

                case 1:
                    HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;

//                    NetHelper homePageNetHelper = new NetHelper();
//                    ImageLoader homePageImageLoader = homePageNetHelper.getImageLoader();
//                    homePageImageLoader.get(jsonGlasses.getData().getList().get(jsonGlassesNum).getGoods_img()
//                            , ImageLoader.getImageListener(homePageViewHolder.ic, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

                    netImageLoader.getImgOfLoader(homePageViewHolder.ic,jsonAll.getData().getList().get(position).getData_info().getGoods_img());
                    homePageViewHolder.homepageBrand.setText(jsonAll.getData().getList().get(position).getData_info().getBrand());
                    homePageViewHolder.homepageModel.setText(jsonAll.getData().getList().get(position).getData_info().getModel());
                    homePageViewHolder.homepageProduct.setText(jsonAll.getData().getList().get(position).getData_info().getProduct_area());
                    homePageViewHolder.homepagePrice.setText(jsonAll.getData().getList().get(position).getData_info().getGoods_price());



                    break;
                case 2:
//                    SpecialViewHolder specialViewHolder = (SpecialViewHolder) holder;
//                    specialViewHolder.specialTitle.setText(jsonAll.getData().getList().get(position).getData_info().);
////                    NetHelper specialNetHelper = new NetHelper();
////                    ImageLoader specialImageLoader = specialNetHelper.getImageLoader();
////                    specialImageLoader.get(jsonSpecial.getData().getList().get(position).getStory_img()
////                            , ImageLoader.getImageListener(specialViewHolder.specialIv, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
//                    netImageLoader.getImgOfLoader(specialViewHolder.specialIv,jsonAll.getData().getList().get(position).getStory_img());
//                    holder.itemView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            specialOnClick.specialOnClick();
//                            Log.d("Sysout", "relativeLayout");
//                        }
//                    });
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
