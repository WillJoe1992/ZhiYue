package com.lanou.mirror.special;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by SAZ on 16/3/29.
 */
public class SpecialActivity extends BaseActivity{
    //okhttp
    private RecyclerView recyclerView;
    private SpecialPictureAdapter specialPictureAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView activitySpecialContentImageView;
    @Override
    protected void initData() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        specialPictureAdapter=new SpecialPictureAdapter(this);
        recyclerView.setAdapter(specialPictureAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                    Log.d("ccccccccccc", "" + linearLayoutManager.findFirstVisibleItemPosition());
                    if(linearLayoutManager.findFirstVisibleItemPosition()%2==0){
                        Toast.makeText(SpecialActivity.this, "22222", Toast.LENGTH_SHORT).show();
                        activitySpecialContentImageView.setImageResource(R.mipmap.background);
                    }
            }
        });
    }

    @Override
    protected void initView() {
        activitySpecialContentImageView=BlindView(R.id.activity_special_content_image_view);
        recyclerView=BlindView(R.id.activity_special_content_rv);
    }

    @Override
    protected int setcontent() {
        return R.layout.activity_special_content;
    }

}
