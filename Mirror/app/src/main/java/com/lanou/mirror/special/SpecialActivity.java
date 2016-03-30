package com.lanou.mirror.special;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

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
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    if(linearLayoutManager.findLastVisibleItemPosition()<=recyclerView.getHeight()){
                        //自己调到自己的activity
                        Intent intent = new Intent(SpecialActivity.this, SpecialActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
     recyclerView=BlindView(R.id.activity_special_content_rv);
    }

    @Override
    protected int setcontent() {
        return R.layout.activity_special_content;
    }

}
