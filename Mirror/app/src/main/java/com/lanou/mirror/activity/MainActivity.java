package com.lanou.mirror.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.adapter.VerticalPagerAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.tool.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SelectTitleRecyclerViewAdapter.ClickListener{
    private VerticalViewPager verticalViewPager;
    private List<Fragment> data;
    private long exitTime = 0;


    @Override
    protected void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_POSITION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void initView() {
        verticalViewPager = BlindView(R.id.vertical_viewpager);
        data = getFragmentList();
        VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
                getSupportFragmentManager(), data);
        verticalViewPager.setAdapter(fragmentAdapter);

    }

    public List<Fragment> getFragmentList() {
        List<Fragment> listFragments = new ArrayList<Fragment>();


        Bundle bundleAll = new Bundle();
        bundleAll.putString("titleName", "瀏覽所有分類");
        HomePagerFragment fragmentAll = new HomePagerFragment();
        fragmentAll.setArguments(bundleAll);
        listFragments.add(fragmentAll);

        
        Bundle bundleFlatGlass = new Bundle();
        bundleFlatGlass.putString("titleName", "浏览平光镜");
        HomePagerFragment fragmentFlatGlass = new HomePagerFragment();
        fragmentFlatGlass.setArguments(bundleFlatGlass);
        listFragments.add(fragmentFlatGlass);

        Bundle bundleSunGlass = new Bundle();
        bundleSunGlass.putString("titleName", "浏览太阳镜");
        HomePagerFragment fragmentSunGlass = new HomePagerFragment();
        fragmentSunGlass.setArguments(bundleSunGlass);
        listFragments.add(fragmentSunGlass);

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
            if (action.equals(Constant.ACTION_POSITION)){
                int position  = intent.getIntExtra("position",0);
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
}
