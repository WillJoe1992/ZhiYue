package com.lanou.mirror.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.net.JSONGlasses;

/**
 * Created by wyc on 16/3/30.
 */
public class HomePagerRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
    private JSONGlasses jsonGlasses;
    public HomePagerRecyclerViewAdapter(Context context,JSONGlasses jsonGlasses) {
        this.context = context;
        this.jsonGlasses=jsonGlasses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHome = LayoutInflater.from(context).inflate(R.layout.item_homepagefragment_recyclerview, null);
        return new HomePageViewHolder(viewHome);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomePageViewHolder homePageViewHolder = (HomePageViewHolder) holder;
        homePageViewHolder.homepageBrand.setText(jsonGlasses.getData().getList().get(position).getBrand());
        homePageViewHolder.homepageModle.setText(jsonGlasses.getData().getList().get(position).getModel());
        homePageViewHolder.homepageProduct.setText(jsonGlasses.getData().getList().get(position).getProduct_area());
        homePageViewHolder.homepagePrice.setText(jsonGlasses.getData().getList().get(position).getGoods_price());
    }

    @Override
    public int getItemCount() {
        return jsonGlasses.getData().getList().size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView ic;
        TextView homepageBrand,homepagePrice,homepageProduct,homepageModle;
        public HomePageViewHolder(View itemView) {
            super(itemView);
            ic = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
            homepageBrand= (TextView) itemView.findViewById(R.id.homepage_brand);
            homepagePrice= (TextView) itemView.findViewById(R.id.homepage_price);
            homepageProduct= (TextView) itemView.findViewById(R.id.homepage_product);
            homepageModle= (TextView) itemView.findViewById(R.id.homepage_modle);
        }
    }
}
