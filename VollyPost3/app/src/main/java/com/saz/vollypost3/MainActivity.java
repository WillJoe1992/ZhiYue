package com.saz.vollypost3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


public class MainActivity extends AppCompatActivity implements Callback {
    private Netloader netloader;
    private TextView textView;
    private HashMap<String, String> head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TextView);
        head = new HashMap<>();
        head.put("token", "41bbc5786eb369a85bac122770325885");
        head.put("uid", "50");
        head.put("device_type", "iPhone6");
        netloader = new Netloader();
        netloader.setCallback(this);
        netloader.postKeyValue("http://api101.test.mirroreye.cn/index.php/products/goods_list", head);
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        textView.setText(response.body().string());
    }
}
