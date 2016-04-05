package com.lanou.mirror.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;

/**
 * Created by dllo on 16/4/5.
 */
public class ShoppingCarFragment extends BaseFragment{
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;
    @Override
    public int getlayout() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    protected void initView() {
        titleSelect =BlindView(R.id.title_select_shopping);
        fragmentHomepageTitle= BlindView(R.id.fragment_shopping_title);
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        fragmentHomepageTitle.setText(titleName);
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
                intent.putExtra("title",fragmentHomepageTitle.getText());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void dataView() {

    }

}
