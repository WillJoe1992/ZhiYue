package com.lanou.mirror.special;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by dllo on 16/3/31.
 */
public class SpecialFragment extends BaseFragment{
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

        head=new HashMap<>();
        titleSelect =BlindView(R.id.title_select);
        fragmentHomepageTitle= BlindView(R.id.fragment_homepage_title);
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        fragmentHomepageTitle.setText(titleName);
        head.put("device_type","1");
        head.put("token","");
        head.put("uid","");
        head.put("page","");
        head.put("last_time","");
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
                intent.putExtra("title", fragmentHomepageTitle.getText());
                startActivity(intent);
            }
        });
        NetOkHttpClient.postAsyn(URL.TEST_STORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
            }
        },head);
    }



    @Override
    protected void dataView() {
        homePageRecyclerView = BlindView(R.id.fragment_homepage_recyclerview);
    }
}
