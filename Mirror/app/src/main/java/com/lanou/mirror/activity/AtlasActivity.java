package com.lanou.mirror.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.AtlasAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONAtlas;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by wyc on 16/3/31.
 */
public class AtlasActivity extends BaseActivity {
    private HashMap<String,String> head;
    private JSONAtlas data;
    private RecyclerView recyclerView;
    private AtlasAdapter atlasAdapter;

    @Override
    protected void initData(){
        head = new HashMap<>();
        head.put("token","");
        head.put("device_type","1");
        head.put("page","");
        head.put("last_time","");
        head.put("category_id", "");
        head.put("version","1.0.1");
        final AtlasActivity atlasActivity = this;
        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                data = gson.fromJson(response,JSONAtlas.class);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(atlasActivity,1);

                atlasAdapter = new AtlasAdapter(data);
                recyclerView.setAdapter(atlasAdapter);
            }
        },head);


    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_atlas;
    }
}
