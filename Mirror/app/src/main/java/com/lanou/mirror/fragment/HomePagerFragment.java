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

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.net.JSONGlasses;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by wyc on 16/3/29.
 */
public class HomePagerFragment extends BaseFragment {
    private RecyclerView homePageRecyclerView;
    private HomePagerRecyclerViewAdapter homePagerRecyclerViewAdapter;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;
    private HashMap<String, String> head;
    private JSONGlasses jsonGlasses;
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
        String url = (String) bundle.get("CategoryId");
        head.put("device_type","1");
        head.put("token","");
        head.put("goods_id",url);
        Log.d("aaaaaaaa",url);
        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                jsonGlasses=gson.fromJson(response,JSONGlasses.class);


                ////////////
                homePagerRecyclerViewAdapter = new HomePagerRecyclerViewAdapter(getContext(),jsonGlasses);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                homePageRecyclerView.setAdapter(homePagerRecyclerViewAdapter);
            }
        },head);





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
