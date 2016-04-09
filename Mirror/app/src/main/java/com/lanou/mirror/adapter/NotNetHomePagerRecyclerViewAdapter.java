package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.greendaodemo.entity.greendao.HomePagerDao;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/7.
 */
public class NotNetHomePagerRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
 //   private NetImageLoader netImageLoader;
    private ImageLoaderHelper imageLoaderHelper;
    private HomePagerDao homePagerDaos;
    public NotNetHomePagerRecyclerViewAdapter(Context context,HomePagerDao homePagerDaos) {
        this.context = context;
        this.homePagerDaos=homePagerDaos;
 //       netImageLoader=new NetImageLoader();
        imageLoaderHelper=new ImageLoaderHelper();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHome = LayoutInflater.from(context).inflate(R.layout.item_homepagefragment_recyclerview, parent, false);
        return new HomePageViewHolder(viewHome);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;
    //    netImageLoader.getImgOfLoader(homePageViewHolder.imageView, homePagerDaos.loadAll().get(position).getGoods_img());
        imageLoaderHelper.loadImage(homePagerDaos.loadAll().get(position).getGoods_img(),homePageViewHolder.imageView);
        homePageViewHolder.homepageBrand.setText(homePagerDaos.loadAll().get(position).getBrand());
        homePageViewHolder.homepageModle.setText(homePagerDaos.loadAll().get(position).getModel());
        homePageViewHolder.homepageProduct.setText(homePagerDaos.loadAll().get(position).getProduct_area());
        homePageViewHolder.homepagePrice.setText(homePagerDaos.loadAll().get(position).getGoods_price());
        Log.d("IIIIIII", "" + homePagerDaos.loadAll().get(position).getGoods_img());
    }

    @Override
    public int getItemCount() {
        return homePagerDaos.loadAll().size()>0?homePagerDaos.loadAll().size():0;
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView
                ;
        TextView homepageBrand,homepagePrice,homepageProduct,homepageModle;
        public HomePageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
            homepageBrand= (TextView) itemView.findViewById(R.id.homepage_brand);
            homepagePrice= (TextView) itemView.findViewById(R.id.homepage_price);
            homepageProduct= (TextView) itemView.findViewById(R.id.homepage_comefrom);
            homepageModle= (TextView) itemView.findViewById(R.id.homepage_modle);
        }
    }
}
