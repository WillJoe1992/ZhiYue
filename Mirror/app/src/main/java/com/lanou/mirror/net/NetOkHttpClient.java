package com.lanou.mirror.net;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by dllo on 16/3/30.
 */
public class NetOkHttpClient {
//okHttp自带异步回调接口
Callback callback;
    //调用接口的方法为保证异步在网络解析前调用
    public void setCallback(Callback callback){
        this.callback = callback;
    }
    //网络解析的post头文件
    public RequestBody formBody;
    public void postKeyValue(String url,HashMap<String,String> head) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : head.keySet()) {
            formBody = formEncodingBuilder.add(key,head.get(key)).build();
        }
        //请求网络
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //安卓自带回调方法
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
