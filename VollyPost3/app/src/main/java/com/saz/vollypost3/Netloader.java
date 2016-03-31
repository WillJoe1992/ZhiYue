package com.saz.vollypost3;

import android.os.Handler;
import android.os.Message;
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
    private static Netloader loader;
    private OkHttpClient okHttpClient;

    private Netloader() {
        okHttpClient = new OkHttpClient();
    }

    public static Netloader getInstance() {
        if (loader == null) {
            loader = new Netloader();
        }
        return loader;
    }

    public interface ResponseListener {
        void suc(Response response) throws Exception;

        void fail(Request request, Exception e);

    }

    private ResponseListener listener;
    private static final int SUC_CODE = 0x110;
    private static final int FAIL_CODE = 0x111;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUC_CODE:
                    try {
                        getSucData(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case FAIL_CODE:
                    getFailData(msg);
                    break;
            }
        }
    };

    private void getFailData(Message msg) {
        FailHolder holder = (FailHolder) msg.obj;
        Request request = holder.request;
        Exception e = holder.e;
        listener.fail(request, e);

    }

    private void getSucData(Message msg) throws Exception {
        SucHolder holder = (SucHolder) msg.obj;
        Response response = holder.response;
        listener.suc(response);

    }


    public RequestBody formBody;

    public void postKeyValue(final String url, final HashMap<String, String> head,final  ResponseListener listener) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Netloader.this.listener = listener;
                FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
                for (String key : head.keySet()) {
                    formBody = formEncodingBuilder.add(key, head.get(key)).build();
                }
                final Request request = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();
                //安卓自带回调方法
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        handleFailMessage(request, e);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        handleSucMessage(response);
                    }
                });
            }
        }).start();




    }

    private void handleSucMessage(Response response) {

        SucHolder holder = new SucHolder();
        holder.response = response;
        Message message = Message.obtain();
        message.what = SUC_CODE;
        message.obj = holder;
        handler.sendMessage(message);

    }

    private void handleFailMessage(Request request, IOException e) {
        FailHolder holder = new FailHolder();
        holder.e = e;
        holder.request = request;
        Message message = Message.obtain();
        message.what = FAIL_CODE;
        message.obj = holder;
        handler.sendMessage(message);

    }

    class SucHolder {
        Response response;
    }

    class FailHolder {
        Request request;
        Exception e;
    }


}
