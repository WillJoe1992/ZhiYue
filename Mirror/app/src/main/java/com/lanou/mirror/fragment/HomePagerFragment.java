package com.lanou.mirror.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
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
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        fragmentHomepageTitle.setText(titleName);

        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
                intent.putExtra("title",fragmentHomepageTitle.getText());
                startActivity(intent);


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


}
