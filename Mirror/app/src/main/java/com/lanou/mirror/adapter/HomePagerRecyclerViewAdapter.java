package com.lanou.mirror.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.EveryGlassesActivity;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;

/**
 * Created by wyc on 16/3/30.
 */
public class HomePagerRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
    private JSONGlasses jsonGlasses;
    private NetImageLoader netImageLoader;
    private AnimationDrawable animationDrawable;
 //   private NetImageLoader netImageLoader;
    private ImageLoaderHelper imageLoaderHelper;
    public HomePagerRecyclerViewAdapter(Context context,JSONGlasses jsonGlasses) {
        this.context = context;
        this.jsonGlasses=jsonGlasses;
  //      netImageLoader=new NetImageLoader();
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
    //    netImageLoader.getImgOfLoader(homePageViewHolder.imageView,jsonGlasses.getData().getList().get(position).getGoods_img());
        imageLoaderHelper.loadImage(jsonGlasses.getData().getList().get(position).getGoods_img(),homePageViewHolder.imageView);
        homePageViewHolder.homepageBrand.setText(jsonGlasses.getData().getList().get(position).getBrand());
        homePageViewHolder.homepageModle.setText(jsonGlasses.getData().getList().get(position).getModel());
        homePageViewHolder.homepageProduct.setText(jsonGlasses.getData().getList().get(position).getProduct_area());
        homePageViewHolder.homepagePrice.setText(jsonGlasses.getData().getList().get(position).getGoods_price());
        homePageViewHolder.position=position;
        Log.d("IIIIIII",""+jsonGlasses.getData().getList().get(position).getGoods_img());
    }

    @Override
    public int getItemCount() {
        return jsonGlasses.getData().getList().size()>0?jsonGlasses.getData().getList().size():0;
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,fragmentLoadingIv;
        TextView homepageBrand,homepagePrice,homepageProduct,homepageModle;
        RelativeLayout line;
        int position;

        public HomePageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_recyclerview_iv);
            homepageBrand= (TextView) itemView.findViewById(R.id.homepage_brand);
            homepagePrice= (TextView) itemView.findViewById(R.id.homepage_price);
            homepageProduct= (TextView) itemView.findViewById(R.id.homepage_comefrom);
            homepageModle= (TextView) itemView.findViewById(R.id.homepage_modle);
            line= (RelativeLayout) itemView.findViewById(R.id.line);
            fragmentLoadingIv= (ImageView) itemView.findViewById(R.id.fragment_loading_iv);

            fragmentLoadingIv.setImageResource(R.drawable.loading);
            animationDrawable = (AnimationDrawable) fragmentLoadingIv.getDrawable();
            animationDrawable.start();
            line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, EveryGlassesActivity.class);

                    intent.putExtra("goodsId",jsonGlasses.getData().getList().get(position).getGoods_id());
                    intent.putExtra("picUrl",jsonGlasses.getData().getList().get(position).getGoods_img());
                    Log.d("HomePageViewHolder", jsonGlasses.getData().getList().get(position).getGoods_id());


                    context.startActivity(intent);
                }
            });
        }
    }
}


