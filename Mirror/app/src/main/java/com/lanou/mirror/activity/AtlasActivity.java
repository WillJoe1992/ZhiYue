package com.lanou.mirror.activity;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
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
    private JSONGlasses data;

    @Override
    protected void initData(){
        head = new HashMap<>();
        head.put("token","");
        head.put("device_type","1");
        head.put("page","");
        head.put("last_time","");
        head.put("category_id","");

        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                data = gson.fromJson(response,JSONGlasses.class);

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
