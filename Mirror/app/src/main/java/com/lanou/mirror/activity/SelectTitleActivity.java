package com.lanou.mirror.activity;





import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.net.JSONGlassesClassification;

import java.util.ArrayList;


public class SelectTitleActivity extends BaseActivity implements  SelectTitleRecyclerViewAdapter.ClickListener {


    private TextView tvTop,tvBottom;


    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;
    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans ;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTop=BlindView(R.id.tv_top);
        tvBottom=BlindView(R.id.tv_bottom);
        selectTitleRc=BlindView(R.id.select_title_rc);

        tvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent getIntent = getIntent();
        String title =getIntent.getStringExtra("title");
        Log.d("aaa",title);
        selectTitleRecyclerBeans=new ArrayList<>();
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("瀏覽所有分類"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("手工阳镜"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("浏览平光镜"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("浏览太阳镜"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("专题分享"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("我的购物车"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("返回首页"));
        selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("退出"));

        Log.d("SelectTitleActivity", title);
        for(int i= 0 ;i<selectTitleRecyclerBeans.size();i++) {
            Log.d("SelectTitleActivity", selectTitleRecyclerBeans.get(i).getTitleName());
            if (selectTitleRecyclerBeans.get(i).getTitleName().equals(title)) {
                Log.d("SelectTitleActivity", title + "1111111111");
                selectTitleRecyclerBeans.get(i).setUnderLine(true);

            }
        }

        selectTitleRecyclerViewAdapter = new SelectTitleRecyclerViewAdapter(this,selectTitleRecyclerBeans);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectTitleRc.setLayoutManager(gridLayoutManager);
        selectTitleRc.setAdapter(selectTitleRecyclerViewAdapter);


        selectTitleRecyclerViewAdapter.setPositionClickListener(this);


    }

    @Override
    protected int setcontent() {
        return R.layout.activity_select_title;
    }




    @Override
    public void ClickListener(int popMenuPosition) {
        finish();
        Intent intent = new Intent();
        Log.e("SelectTitleActivity", "popMenuPosition:" + popMenuPosition);
        intent.setAction(Constant.ACTION_POSITION);
        intent.putExtra("position",popMenuPosition);
        sendBroadcast(intent);
    }
}
