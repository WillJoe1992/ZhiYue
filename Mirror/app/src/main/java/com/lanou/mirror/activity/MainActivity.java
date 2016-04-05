package com.lanou.mirror.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.adapter.VerticalPagerAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.fragment.AllFragment;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.fragment.ShoppingCarFragment;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntity;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntityDao;
import com.lanou.mirror.net.JSONGlassesClassification;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.special.SpecialFragment;
import com.lanou.mirror.tool.URL;
import com.lanou.mirror.tool.VerticalViewPager;
import com.squareup.okhttp.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements SelectTitleRecyclerViewAdapter.ClickListener {
    private VerticalViewPager verticalViewPager;
    private TextView loginText;

    private HashMap<String, String> head;
    private JSONGlassesClassification jsonGlassesClassification;

    private List<Fragment> data;
    private long exitTime = 0;

    // 数据库
    private SQLiteDatabase db;
    // 管理者
    private DaoMaster mDaoMaster;
    // 会话
    private DaoSession mDaoSession;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private LabelEntityDao labelEntityDao;

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
        goToLogin();
    }

    public List<Fragment> getFragmentList() {
        List<Fragment> listFragments = new ArrayList<Fragment>();


        Bundle bundleAll = new Bundle();
        bundleAll.putString("titleName", "瀏覽所有分類");
        bundleAll.putSerializable("CategoryId", "110");
        AllFragment fragmentAll = new AllFragment();
        fragmentAll.setArguments(bundleAll);
        listFragments.add(fragmentAll);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mirrorlib.db", null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        labelEntityDao = mDaoSession.getLabelEntityDao();

        labelEntityDao.deleteAll();
        for (int i = 0; i < jsonGlassesClassification.getData().size(); i++) {
            LabelEntity labelEntity = new LabelEntity((long) i, jsonGlassesClassification.getData().get(i).getCategory_name());
            labelEntityDao.insert(labelEntity);
        }

        for (int i = 0; i < jsonGlassesClassification.getData().size(); i++) {
            Bundle bundleFlatGlass = new Bundle();
            bundleFlatGlass.putString("titleName", jsonGlassesClassification.getData().get(i).getCategory_name());
            bundleFlatGlass.putString("CategoryId", jsonGlassesClassification.getData().get(i).getCategory_id());
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
        SpecialFragment fragmentSpecial = new SpecialFragment();
        bundleSpecial.putString("titleName", "专题分享");
        fragmentSpecial.setArguments(bundleSpecial);
        listFragments.add(fragmentSpecial);


        Bundle bundleShoppingCart = new Bundle();
        bundleShoppingCart.putString("titleName", "我的购物车");
        bundleShoppingCart.putSerializable("CategoryId", "110");
        ShoppingCarFragment shoppingCarFragment=new ShoppingCarFragment();
        shoppingCarFragment.setArguments(bundleShoppingCart);
        listFragments.add(shoppingCarFragment);

        Bundle bundleGoback = new Bundle();
        bundleGoback.putString("titleName", "返回首页");
        bundleGoback.putString("CategoryId", "110");
        HomePagerFragment fragmentGoback = new HomePagerFragment();
        fragmentGoback.setArguments(bundleGoback);
        listFragments.add(fragmentGoback);

        Bundle bundleExit = new Bundle();
        bundleExit.putString("titleName", "退出");
        bundleExit.putString("CategoryId", "110");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public void goToLogin() {
        loginText = BlindView(R.id.goto_login);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
