package com.lanou.mirror.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.adapter.VerticalPagerAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.net.JSONGlassesClassification;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.URL;
import com.lanou.mirror.tool.VerticalViewPager;
import com.squareup.okhttp.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements SelectTitleRecyclerViewAdapter.ClickListener {
    private VerticalViewPager verticalViewPager;
    List<Fragment> data;
    private HashMap<String, String> head;
    private JSONGlassesClassification jsonGlassesClassification;
    @Override
    protected void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_POSITION);
        registerReceiver(receiver, filter);
        head.put("token", "");
        NetOkHttpClient.postAsyn(URL.CATEGORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("bbbbbb", response);
                Gson gson = new Gson();
                jsonGlassesClassification = gson.fromJson(response, JSONGlassesClassification.class);
                data = getFragmentList();
                VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
                        getSupportFragmentManager(), data);
                verticalViewPager.setAdapter(fragmentAdapter);
            }
        }, head);

    }

    @Override
    protected void initView() {
        verticalViewPager = BlindView(R.id.vertical_viewpager);
        head = new HashMap<>();
    }

    public List<Fragment> getFragmentList() {
        List<Fragment> listFragments = new ArrayList<Fragment>();


        Bundle bundleAll = new Bundle();
        bundleAll.putString("titleName", "瀏覽所有分類");
        HomePagerFragment fragmentAll = new HomePagerFragment();
        fragmentAll.setArguments(bundleAll);
        listFragments.add(fragmentAll);


        for (int i = 0; i <jsonGlassesClassification.getData().size() ; i++) {
            Bundle bundleFlatGlass = new Bundle();
            bundleFlatGlass.putString("titleName", jsonGlassesClassification.getData().get(i).getCategory_name());
            bundleFlatGlass.putString("allURL", jsonGlassesClassification.getData() + "");
            HomePagerFragment fragmentFlatGlass = new HomePagerFragment();
            fragmentFlatGlass.setArguments(bundleFlatGlass);
            listFragments.add(fragmentFlatGlass);
        }


        ///////////////////////////
        //将网络拉取的titile复用一个fragment代码如上
//        Bundle bundleFlatGlass = new Bundle();
//        bundleFlatGlass.putString("titleName", jsonGlassesClassification.getData().get(0).getCategory_name());
//        HomePagerFragment fragmentFlatGlass = new HomePagerFragment();
//        fragmentFlatGlass.setArguments(bundleFlatGlass);
//        listFragments.add(fragmentFlatGlass);
//
//        Bundle bundleSunGlass = new Bundle();
//        bundleSunGlass.putString("titleName", jsonGlassesClassification.getData().get(1).getCategory_name());
//        HomePagerFragment fragmentSunGlass = new HomePagerFragment();
//        fragmentSunGlass.setArguments(bundleSunGlass);
//        listFragments.add(fragmentSunGlass);
//
//        Bundle bundleHandGlass = new Bundle();
//        Log.d("sssss",jsonGlassesClassification.getData().get(2).getCategory_name());
//        bundleHandGlass.putString("titleName", jsonGlassesClassification.getData().get(2).getCategory_name());
//        HomePagerFragment fragmentHandGlass  = new HomePagerFragment();
//        fragmentHandGlass .setArguments(bundleHandGlass);
//        listFragments.add(fragmentHandGlass);
        ////////////////////////////

         Bundle bundleSpecial = new Bundle();
         bundleSpecial.putString("titleName", "专题分享");

        HomePagerFragment fragmentSpecial = new HomePagerFragment();
        fragmentSpecial.setArguments(bundleSpecial);
        listFragments.add(fragmentSpecial);


        Bundle bundleShoppingCart = new Bundle();
        bundleShoppingCart.putString("titleName", "我的购物车");
        HomePagerFragment fragmentShoppingCart = new HomePagerFragment();
        fragmentShoppingCart.setArguments(bundleShoppingCart);
        listFragments.add(fragmentShoppingCart);

        Bundle bundleGoback = new Bundle();
        bundleGoback.putString("titleName", "返回首页");
        HomePagerFragment fragmentGoback = new HomePagerFragment();
        fragmentGoback.setArguments(bundleGoback);
        listFragments.add(fragmentGoback);

        Bundle bundleExit = new Bundle();
        bundleExit.putString("titleName", "退出");
        HomePagerFragment fragmentExit = new HomePagerFragment();
        fragmentExit.setArguments(bundleExit);
        listFragments.add(fragmentExit);

        return listFragments;
    }

    @Override
    protected int setcontent() {
        return R.layout.activity_main;
    }

    @Override
    public void ClickListener(int popMenuPosition) {
        verticalViewPager.setCurrentItem(popMenuPosition);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.ACTION_POSITION)) {
                int position = intent.getIntExtra("position", 0);
                Log.e("MainActivity", "position:" + position);
                verticalViewPager.setCurrentItem(position);

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
