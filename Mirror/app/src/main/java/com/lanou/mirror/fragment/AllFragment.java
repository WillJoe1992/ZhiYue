package com.lanou.mirror.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.base.BaseFragment;

import java.util.HashMap;

/**
 * Created by dllo on 16/4/1.
 */
public class AllFragment extends BaseFragment{
    private RecyclerView homePageRecyclerView;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;
    private HashMap<String, String> head;
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
    }
}
