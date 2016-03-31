package com.lanou.mirror.activity;

import android.support.v4.app.Fragment;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.VerticalPagerAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.tool.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private VerticalViewPager verticalViewPager;
    List<Fragment> data;


    @Override
    protected void initData() {

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

        for (int i = 0; i < 5; i++) {
            HomePagerFragment oneFragment = new HomePagerFragment();
            listFragments.add(oneFragment);

        }
        return listFragments;
    }

    @Override
    protected int setcontent() {
        return R.layout.activity_main;
    }
}
