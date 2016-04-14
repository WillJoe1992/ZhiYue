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
import com.lanou.mirror.greendao.AllHolderDao;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.tool.MyLog;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/7.
 */
public class NotNetAllAdapter extends RecyclerView.Adapter{
    private Context context;
    private AllHolderDao allHolderDao;
 //   private NetImageLoader netImageLoader;
    private ImageLoaderHelper imageLoaderHelper;
    public NotNetAllAdapter(Context context, AllHolderDao allHolderDao) {
        this.context = context;
        this.allHolderDao = allHolderDao;
  //      netImageLoader=new NetImageLoader();
        imageLoaderHelper=new ImageLoaderHelper();
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
         //       MyLog.showLog("NotNetAllAdapter",allHolderDao.loadAll().get(position).getStory_img());
                imageLoaderHelper.loadImage(allHolderDao.loadAll().get(position).getGoods_img(),homePageViewHolder.allImageView);
                homePageViewHolder.homepageBrand.setText(allHolderDao.loadAll().get(position).getBrand());
                homePageViewHolder.homepageModel.setText(allHolderDao.loadAll().get(position).getModel());
                homePageViewHolder.homepageProduct.setText(allHolderDao.loadAll().get(position).getProduct_area());
                homePageViewHolder.homepagePrice.setText(allHolderDao.loadAll().get(position).getGoods_price());
                break;
            case 2:

                break;
        }
    }
    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView allImageView;
        TextView homepageBrand, homepagePrice, homepageProduct, homepageModel;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            allImageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
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
    public int getItemCount() {
        return (allHolderDao.loadAll().size() ) > 0 ? allHolderDao.loadAll().size(): 0;
    }

    @Override
    public int getItemViewType(int position) {
       // return Integer.valueOf(allHolderDao.loadAll().get(position).);
        return allHolderDao.loadAll().get(position).getType()!=null? Integer.parseInt(allHolderDao.loadAll().get(position).getType()):1;
    }
}
