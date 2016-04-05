package com.lanou.mirror.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.adapter.AllRecyclerViewAdapter;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.net.JSONGlasses;
import com.lanou.mirror.net.JSONSpecial;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.special.SpecialAdapter;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by dllo on 16/4/1.
 */
public class AllFragment extends BaseFragment{
    private RecyclerView homePageRecyclerView;
    private AllRecyclerViewAdapter allRecyclerViewAdapter;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;

    private HashMap<String, String> headGlasses;
    private HashMap<String, String> headSpecial;
    private JSONGlasses jsonGlasses;
    private JSONSpecial jsonSpecial;
    @Override
    public int getlayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        titleSelect =BlindView(R.id.title_select);
        homePageRecyclerView = BlindView(R.id.fragment_homepage_recyclerview);
        fragmentHomepageTitle= BlindView(R.id.fragment_homepage_title);

        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        String url = (String) bundle.get("CategoryId");
        fragmentHomepageTitle.setText(titleName);
        headGlasses=new HashMap<>();
        headGlasses.put("device_type", "1");
        headGlasses.put("token","");
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
    }

    @Override
    protected void dataView() {

    }
}
