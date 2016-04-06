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
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.net.NetImageLoader;

import java.util.Random;

/**
 * Created by dllo on 16/4/5.
 */
public class AllRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private JSONGlasses jsonGlasses;
    private JSONSpecial jsonSpecial;
    private SpecialOnClick specialOnClick;
    private Random random=new Random();
    private NetImageLoader netImageLoader;
    public AllRecyclerViewAdapter(Context context, JSONGlasses jsonGlasses, JSONSpecial jsonSpecial) {
        if (jsonSpecial != null) {
            this.context = context;
            this.jsonGlasses = jsonGlasses;
            this.jsonSpecial = jsonSpecial;
            netImageLoader=new NetImageLoader();
        }


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

        int sumGlass=0,sumSpecial=0;
        int i;
        if (sumGlass ==jsonGlasses.getData().getList().size()){
            i=2;
        }else if (sumSpecial ==jsonSpecial.getData().getList().size()){
            i=1;
        }else{
            i =random.nextInt(2)+1;
        }

        if (i==1){
            sumGlass++;

        }else if(i==2){
            sumSpecial++;
        }
        Log.d("AllRecyclerViewAdapter", "i:" + i);
        return i;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (jsonSpecial != null) {
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
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Log.d("ShowNewstabsonAdapter", "viewType:" + viewType);
        int jsonGlassesNum = 0;
        int jsonSpecialNum = 0;
        if (jsonSpecial != null) {
            switch (viewType) {

                case 1:
                    HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;

//                    NetHelper homePageNetHelper = new NetHelper();
//                    ImageLoader homePageImageLoader = homePageNetHelper.getImageLoader();
//                    homePageImageLoader.get(jsonGlasses.getData().getList().get(jsonGlassesNum).getGoods_img()
//                            , ImageLoader.getImageListener(homePageViewHolder.ic, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

                    netImageLoader.getImgOfLoader(homePageViewHolder.ic,jsonGlasses.getData().getList().get(jsonGlassesNum).getGoods_img());
                    homePageViewHolder.homepageBrand.setText(jsonGlasses.getData().getList().get(jsonGlassesNum).getBrand());
                    homePageViewHolder.homepageModel.setText(jsonGlasses.getData().getList().get(jsonGlassesNum).getModel());
                    homePageViewHolder.homepageProduct.setText(jsonGlasses.getData().getList().get(jsonGlassesNum).getProduct_area());
                    homePageViewHolder.homepagePrice.setText(jsonGlasses.getData().getList().get(jsonGlassesNum).getGoods_price());
                    jsonGlassesNum = jsonGlassesNum++;


                    break;
                case 2:
                    SpecialViewHolder specialViewHolder = (SpecialViewHolder) holder;
                    specialViewHolder.specialTitle.setText(jsonSpecial.getData().getList().get(position).getStory_title());
//                    NetHelper specialNetHelper = new NetHelper();
//                    ImageLoader specialImageLoader = specialNetHelper.getImageLoader();
//                    specialImageLoader.get(jsonSpecial.getData().getList().get(position).getStory_img()
//                            , ImageLoader.getImageListener(specialViewHolder.specialIv, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
                    netImageLoader.getImgOfLoader(specialViewHolder.specialIv,jsonSpecial.getData().getList().get(position).getStory_img());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            specialOnClick.specialOnClick();
                            Log.d("Sysout", "relativeLayout");
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (jsonSpecial != null) {

            return (jsonGlasses.getData().getList().size() + jsonSpecial.getData().getList().size()) > 0 ? jsonGlasses.getData().getList().size() + jsonSpecial.getData().getList().size() : 0;
        }
        return 0;
    }

    interface SpecialOnClick {
        void specialOnClick();
    }


}
