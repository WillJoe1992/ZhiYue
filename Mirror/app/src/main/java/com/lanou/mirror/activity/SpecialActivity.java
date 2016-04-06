package com.lanou.mirror.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.adapter.SpecialPictureAdapter;
import com.lanou.mirror.net.NetImageLoader;

/**
 * Created by SAZ on 16/3/29.
 */
public class SpecialActivity extends BaseActivity {
    //okhttp
    private RecyclerView recyclerView;
    private SpecialPictureAdapter specialPictureAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView activitySpecialContentImageView;
    private NetImageLoader netImageLoader;
    @Override
    protected void initData() {
        netImageLoader=new NetImageLoader();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        //获得JSON数据
        final JSONSpecial jsonSpecial = (JSONSpecial) intent.getSerializableExtra("jsonSpecial");
        //获得其位置
        final int position=intent.getExtras().getInt("position");
        specialPictureAdapter = new SpecialPictureAdapter(this,jsonSpecial,position);
        recyclerView.setAdapter(specialPictureAdapter);
         netImageLoader.getImgOfLoader(activitySpecialContentImageView,jsonSpecial.getData().getList().get(position).getStory_data().getImg_array().get(0));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断含有详情
                if (jsonSpecial.getData().getList().get(position).getStory_data().getStory_date_type().equals("1")) {
                    //判断Fragment划过两个
                    if (linearLayoutManager.findFirstVisibleItemPosition() % 2 == 0) {
                        int i = linearLayoutManager.findFirstVisibleItemPosition() / 2;
                        netImageLoader.getImgOfLoader(activitySpecialContentImageView,jsonSpecial.getData().getList().get(position).getStory_data().getImg_array().get(i));
                        Log.d("aaaaaaaaaa", jsonSpecial.getData().getList().get(position).getStory_data().getImg_array().get(i));
                    }
                }

            }
        });
    }

    @Override
    protected void initView() {
        activitySpecialContentImageView = BindView(R.id.activity_special_content_image_view);
        recyclerView = BindView(R.id.activity_special_content_rv);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_special_content;
    }

}
