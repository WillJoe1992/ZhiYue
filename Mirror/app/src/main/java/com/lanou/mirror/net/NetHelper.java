package com.lanou.mirror.net;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 16/3/1.
 * 所有的网络请求都通过该类实现
 * 目标给我参数，给你结果
 */
public class NetHelper {
    RequestQueue requestQueue;//请求队列
    //NetListener netListener;//接口对象请求后回调

    private ImageLoader imageLoader ;//用来加载网络图片


    public NetHelper(){
        //请求队列初始化
        SingleQueue singleQueue =SingleQueue.getInstance();
        requestQueue= singleQueue.getRequestQueue();

        //输出化ImageLoader需要2个参数
        //第一个参数是请求队列
        //第二个参数是缓存对象//网络缓存分3级，
        // 1.硬盘缓存。sd卡能看到 2.内存缓存，先保存在内存里 3.网络缓存。服务器端的缓存，
        //imageloader 灵活。可以自己制定缓存规则。可以确定缓存路径等等
        imageLoader =new ImageLoader(requestQueue,new MemoryCache());
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }




    //从网络获取信息
    private void getDataFromNet(String url,final Map<String,String> head , final NetListener netListener){
        JsonObjectRequest request =new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                netListener.getSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.getfailed("拉取失败");
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                if(head!=null){
                    return head;//
                }
                return super.getHeaders();
            }
        };
         requestQueue.add(request);
    }


}
