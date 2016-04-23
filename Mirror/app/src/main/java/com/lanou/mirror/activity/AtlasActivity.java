package com.lanou.mirror.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.AtlasAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONAllson;
import com.lanou.mirror.bean.JSONAtlas;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.HashMap;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by wyc on 16/3/31.
 */
public class AtlasActivity extends BaseActivity {
    public static HashMap<String, String> head;
    private JSONAtlas data;
    private JCVideoPlayer jcVideoPlayer;
    private RecyclerView recyclerView;
    private AtlasAdapter atlasAdapter;
    private int position;
    private ImageView ivBuy;
    private JSONAllson jsonAllson;
    private HashMap<String, String> headBuy;
    private String goodsId;

    @Override
    protected void initData() {



        head = new HashMap<>();
        head.put("token", "");
        head.put("device_type", "1");
        head.put("page", "");
        head.put("last_time", "");
        head.put("category_id", "");
        head.put("version", "1.0.1");
        final AtlasActivity atlasActivity = this;
        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                data = gson.fromJson(response, JSONAtlas.class);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(atlasActivity, 1);
                recyclerView.setLayoutManager(gridLayoutManager);
                atlasAdapter = new AtlasAdapter(atlasActivity, data);
                recyclerView.setAdapter(atlasAdapter);
            }
        }, head);


    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 100);
        goodsId = intent.getStringExtra("goodsId");
        headBuy=new HashMap<>();

        recyclerView = bindView(R.id.atlas_recyclerview);
        ivBuy = bindView(R.id.everyglasses_button_buy);
        head = new HashMap<>();
        //给head赋值然后进行网络拉取
        headBuy.put("device_type", "1");
        headBuy.put("token", "");
        headBuy.put("goods_id", goodsId);

        NetOkHttpClient.postAsyn(URL.TEST_GOODS_INFO, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                // TODO: 16/4/6  如果拉取失败需要从数据库/本地获取

            }

            @Override
            public void onResponse(String response) {
                //获取到的数据放入gson类中。将数据放入横向滑动的recyclerview
                Gson gson = new Gson();
                jsonAllson = gson.fromJson(response, JSONAllson.class);




            }
        }, headBuy);


        ivBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UsingData.GetUsingData().getAllLoginDao().isEmpty() && !jsonAllson.getData().getGoods_id().isEmpty()) {
                    Intent intentBuy = new Intent(AtlasActivity.this, BuyActivity.class);
                    intentBuy.putExtra("buyGoods_id", jsonAllson.getData().getGoods_id());
                    intentBuy.putExtra("getToken", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                    intentBuy.putExtra("img", jsonAllson.getData().getDesign_des().get(0).getImg());
                    intentBuy.putExtra("goods_name", jsonAllson.getData().getGoods_name());
                    intentBuy.putExtra("goods_price", jsonAllson.getData().getGoods_price());
                    startActivity(intentBuy);
                } else {
                    Intent intentEveryGlasses = new Intent(AtlasActivity.this, LoginActivity.class);
                    startActivity(intentEveryGlasses);
                }
            }
        });
    }

    @Override
    protected int setContent() {
        return R.layout.activity_atlas;
    }
}
