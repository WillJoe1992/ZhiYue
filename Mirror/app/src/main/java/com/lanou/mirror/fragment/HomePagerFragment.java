package com.lanou.mirror.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;

/**
 * Created by wyc on 16/3/29.
 */
public class HomePagerFragment extends BaseFragment {
    private RecyclerView homePageRecyclerView;
    private HomePagerRecyclerViewAdapter homePagerRecyclerViewAdapter;

    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;

    @Override
    public int getlayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {

        titleSelect =BlindView(R.id.title_select);
        fragmentHomepageTitle= BlindView(R.id.fragment_homepage_title);

        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
                intent.putExtra("title",fragmentHomepageTitle.getText());
                startActivityForResult(intent,101);


            }
        });
    }

    @Override
    protected void dataView() {
        homePageRecyclerView = BlindView(R.id.fragment_homepage_recyclerview);
        homePagerRecyclerViewAdapter = new HomePagerRecyclerViewAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePageRecyclerView.setLayoutManager(gridLayoutManager);
        homePageRecyclerView.setAdapter(homePagerRecyclerViewAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && requestCode == 101) {
            // 获得Intent对象中的值
            String name = data.getStringExtra("n");

            // 使用跳转返回值
            fragmentHomepageTitle.setText(name);
        }
    }
}
