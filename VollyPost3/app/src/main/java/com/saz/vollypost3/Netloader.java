package com.saz.vollypost3;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okio.BufferedSink;


/**
 * Created by dllo on 16/3/30.
 */
public class Netloader {
    //回调接口
    Callback callback;
    public void setCallback(Callback callback){
        this.callback = callback;
    }
    public RequestBody formBody;
    public void postKeyValue(String url,HashMap<String,String>head) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : head.keySet()) {
            formBody = formEncodingBuilder.add(key,head.get(key)).build();
        }
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //安卓自带回调方法
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }


}
