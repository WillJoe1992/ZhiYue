package com.lanou.mirror.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
import com.lanou.mirror.activity.SpecialActivity;
import com.lanou.mirror.adapter.SpecialAdapter;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONSpecial;
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
    private SpecialAdapter specialAdapter;
    private JSONSpecial jsonSpecial;
    private String title;
    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {

        head=new HashMap<>();
        titleSelect = bindView(R.id.title_select);
        fragmentHomepageTitle= bindView(R.id.fragment_homepage_title);
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


                title = fragmentHomepageTitle.getText().toString();
                ((MainActivity)getActivity()).showPopupWindow(v,title);
//                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
//                intent.putExtra("title", fragmentHomepageTitle.getText());
//                startActivity(intent);
            }
        });
        NetOkHttpClient.postAsyn(URL.TEST_STORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                jsonSpecial=gson.fromJson(response,JSONSpecial.class);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                specialAdapter=new SpecialAdapter(jsonSpecial);
                homePageRecyclerView.setAdapter(specialAdapter);
                specialAdapter.MySpecialOnClick(new SpecialAdapter.SpecialOnClick() {
                    @Override
                    public void specialOnClick(int position) {
                        Intent intent=new Intent(getContext(),SpecialActivity.class);
                        intent.putExtra("jsonSpecial",jsonSpecial);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }
                });
            }
        },head);
    }



    @Override
    protected void dataView() {
        homePageRecyclerView = bindView(R.id.fragment_homepage_recyclerview);
    }
}
