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
import com.lanou.mirror.tool.URL;
import org.json.JSONObject;

/**
 * Created by wyc on 16/4/12.
 */
public class WelcomeActivity extends BaseActivity {
    public SingletonPattern singletonPattern;
    private WelcomeBean data;
    private ImageView welcomeIv;
    private Handler handler;

    @Override
    protected void initView() {
        singletonPattern = new SingletonPattern();
        data = new WelcomeBean();
        welcomeIv = bindView(R.id.welcome_iv);

        RequestQueue queue = SingletonPattern.getSingletonPattern().getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(URL.INDEX_STARTED_IMG, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                data = new Gson().fromJson(response.toString(), WelcomeBean.class);
                Log.d("WelcomeActivity", "data:" + data);
                handler.sendEmptyMessage(1);
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    @Override
    protected void initData() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("WelcomeActivity", data.getImg());
                new NetImageLoader().getImgOfLoader(welcomeIv, data.getImg());
                return false;
            }
        });

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
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
