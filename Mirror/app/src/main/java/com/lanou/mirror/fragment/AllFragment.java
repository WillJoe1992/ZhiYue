package com.lanou.mirror.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
import com.lanou.mirror.adapter.AllRecyclerViewAdapter;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntityDao;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/4/1.
 */
public class AllFragment extends BaseFragment {
    private RecyclerView homePageRecyclerView;
    private AllRecyclerViewAdapter allRecyclerViewAdapter;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;

    private HashMap<String, String> headGlasses;
    private HashMap<String, String> headSpecial;
    private JSONGlasses jsonGlasses;
    private JSONSpecial jsonSpecial;

    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans;
    private String title;
    private PopupWindow popupWindow;
    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;

    // 数据库
    private SQLiteDatabase db;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private LabelEntityDao labelEntityDao;
    //操作数据库
    // 管理者
    private DaoMaster daoMaster;
    // 会话
    private DaoSession daoSession;


    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        titleSelect = BindView(R.id.title_select);
        homePageRecyclerView = BindView(R.id.fragment_homepage_recyclerview);
        fragmentHomepageTitle = BindView(R.id.fragment_homepage_title);

        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        String url = (String) bundle.get("CategoryId");
        fragmentHomepageTitle.setText(titleName);
        headGlasses = new HashMap<>();
        headGlasses.put("device_type", "1");
        headGlasses.put("token", "");
        headGlasses.put("goods_id", url);

//        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
//            @Override
//            public void onError(Request request, Exception e) {
//            Log.d("111","111");
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                jsonGlasses = gson.fromJson(response, JSONGlasses.class);
//                allRecyclerViewAdapter = new AllRecyclerViewAdapter(getContext(),jsonGlasses,null);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                homePageRecyclerView.setLayoutManager(gridLayoutManager);
//                homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
//
//
//
//            }
//        }, headGlasses);
//        headSpecial=new HashMap<>();
//        headSpecial.put("device_type","1");
//        headSpecial.put("token","");
//        headSpecial.put("uid","");
//        headSpecial.put("page","");
//        headSpecial.put("last_time", "");
//
//        NetOkHttpClient.postAsyn(URL.TEST_STORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
//            @Override
//            public void onError(Request request, Exception e) {
//
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                jsonSpecial = gson.fromJson(response, JSONSpecial.class);
//                allRecyclerViewAdapter = new AllRecyclerViewAdapter(getContext(),jsonGlasses,jsonSpecial);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                homePageRecyclerView.setLayoutManager(gridLayoutManager);
//                homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
//
//
//
//            }
//        }, headSpecial);


        ////////////
//        allRecyclerViewAdapter = new AllRecyclerViewAdapter(getContext(),jsonGlasses,jsonSpecial);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        homePageRecyclerView.setLayoutManager(gridLayoutManager);
//        homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
//
//
//
//        titleSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
//                intent.putExtra("title",fragmentHomepageTitle.getText());
//                startActivity(intent);
//            }
//        });
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = fragmentHomepageTitle.getText().toString();
                ((MainActivity)getActivity()).showPopupWindow(v, title);

            }
        });
    }

    @Override
    protected void dataView() {

    }

  
}