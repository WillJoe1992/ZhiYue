package com.lanou.mirror.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.WelcomeBean;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.net.SingletonPattern;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.URL;

import org.json.JSONObject;

/**
 * Created by wyc on 16/4/12.
 *
 */

public class WelcomeActivity extends BaseActivity {

    public SingletonPattern singletonPattern;
    private WelcomeBean data;
    private ImageView welcomeIv;


    @Override
    protected void initView() {
        singletonPattern = new SingletonPattern();
        data = new WelcomeBean();
        welcomeIv = bindView(R.id.welcome_iv);


        //Volley 请求数据
        RequestQueue queue = SingletonPattern.getSingletonPattern().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(URL.INDEX_STARTED_IMG, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //成功的获取数据
                data = new Gson().fromJson(response.toString(), WelcomeBean.class);
                new NetImageLoader().getImgOfLoader(welcomeIv, data.getImg());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyToast.myToast("首页图片数据拉取失败");
            }
        });
        //将request请求加入队列
        queue.add(request);
    }

    @Override
    protected void initData() {

        //子线程睡眠3秒
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jump();
            }

        }).start();
    }

    @Override
    protected int setContent() {
        return R.layout.activity_welcome;
    }

    public void jump() {
        //直接跳转
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        //结束页面
        finish();
    }
}
