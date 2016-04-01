package com.lanou.mirror.net;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lanou.mirror.base.BaseApplication;


/**
 * Created by dllo on 16/3/1.
 *
 * 单例模式，用来保证整个项目的所有请求队列只有一个
 */
public class SingleQueue {
    //第二部 ，在内部保存个自己
    private static SingleQueue singleQueue = null;
    private RequestQueue requestQueue;//请求队列的对象



    //第一步 构造方法私有化 ：没有任何方法可以new出它了

    private SingleQueue() {
        Log.e("SingleQueue", "BaseApplication.getContext():" + BaseApplication.getContext());
        requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
    }

    //第三部，提供方法，将自己暴露出去
    public static SingleQueue getInstance(){
        if(singleQueue == null){//效率
            synchronized (SingleQueue.class){
                //能保证大括号内部只有一个线程
                if(singleQueue == null) {//安全
                    singleQueue = new SingleQueue();
                }
            }
        }

        return  singleQueue;
    }


    public RequestQueue getRequestQueue(){
        return  requestQueue;
    }

}
