package com.lanou.mirror.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wyc on 16/3/29.
 */
public class HomePageFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> data;

    public HomePageFragmentAdapter(FragmentManager fm, ArrayList<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        if (data != null && data.size() > 0) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }
}
