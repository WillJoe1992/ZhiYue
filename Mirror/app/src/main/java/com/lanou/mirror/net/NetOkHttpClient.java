package com.lanou.mirror.net;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;
import java.util.Set;

/**
 * Created by SAZ on 16/3/30.
 */
public class NetOkHttpClient {
    private static NetOkHttpClient mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;


    public NetOkHttpClient() {
        mOkHttpClient = new OkHttpClient();
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        //表示主线程
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();

    }

    public static NetOkHttpClient getInstance()
    {
        if (mInstance == null)
        {
            synchronized (NetOkHttpClient.class)
            {
                if (mInstance == null)
                {
                    mInstance=new NetOkHttpClient();

                }
            }
        }
        return mInstance;
    }

    //对外暴漏的方法,post表格形式请求,参数网址,接口,post头文件
    public static void postAsyn(String url, final ResultCallback callback, Map<String, String> params)
    {
        getInstance().postAsynAll(url, callback, params);
    }
    /**
     * 异步的post请求
     *
     * @param url 网址
     * @param callback 接口
     * @param params 头文件
     */
    private void postAsynAll(String url, final ResultCallback callback, Map<String, String> params)
    {
        Param[] paramsArr = map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        deliveryResult(callback, request);
    }
    public static class Param
    {
        public Param()
        {
        }

        public Param(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }
    private Param[] map2Params(Map<String, String> params)
    {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries)
        {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }
    public static abstract class ResultCallback<T>
    {
        Type mType;

        public ResultCallback()
        {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass)
        {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class)
            {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response) throws JSONException;
    }
    private Request buildPostRequest(String url, Param[] params)
    {
        if (params == null)
        {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params)
        {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }
    //Result返回
    private void deliveryResult(final ResultCallback callback, Request request)
    {
        mOkHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(final Request request, final IOException e)
            {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(final Response response)
            {
                try
                {
                    final String string = response.body().string();
                    if (callback.mType == String.class)
                    {
                        sendSuccessResultCallback(string, callback);
                    } else
                    {
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }


                } catch (IOException e)
                {
                    sendFailedStringCallback(response.request(), e, callback);
                } catch (Exception e)//Json解析的错误
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }
    private void sendSuccessResultCallback(final Object object, final ResultCallback callback)
    {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    try {
                        callback.onResponse(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //解析失败回调
    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }
}