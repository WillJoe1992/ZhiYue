package com.lanou.mirror.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.HomePageFragmentAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.tool.VerticalViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> data;
    private VerticalViewPager verticalViewPager;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setVerticalViewPager();    // 设置竖向滑动的viewpager


    }
    public void setVerticalViewPager(){
        verticalViewPager = BlindView(R.id.vertical_viewpager);
        data = new ArrayList<>();
        data.add(new HomePagerFragment());
        data.add(new HomePagerFragment());
        data.add(new HomePagerFragment());
        data.add(new HomePagerFragment());

        FragmentManager manager = getSupportFragmentManager();
        HomePageFragmentAdapter adapter = new HomePageFragmentAdapter(manager,data);
        verticalViewPager.setAdapter(adapter);
    }

    @Override
    protected int setcontent() {
        return R.layout.activity_main;
    }
}
