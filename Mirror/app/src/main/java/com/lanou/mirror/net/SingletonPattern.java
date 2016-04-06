package com.lanou.mirror.net;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lanou.mirror.base.BaseApplication;

/**
 * Created by dllo on 16/4/6.
 */
public class SingletonPattern {
    private static SingletonPattern singletonPattern;
    private RequestQueue requestQueue;
    private SingletonPattern() {
        requestQueue= Volley.newRequestQueue(BaseApplication.getContext());
    }

    public static SingletonPattern getSingletonPattern() {
        if (singletonPattern == null) {
            synchronized (SingletonPattern.class) {
                if (singletonPattern == null) {
                    singletonPattern = new SingletonPattern();
                }
            }
        }
        return singletonPattern;
    }
    public RequestQueue getRequestQueue(){return requestQueue;}
}
