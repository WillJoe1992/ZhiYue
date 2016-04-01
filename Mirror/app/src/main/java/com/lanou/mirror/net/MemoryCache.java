package com.lanou.mirror.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/3/3.
 */
public class MemoryCache implements ImageLoader.ImageCache {
    //Lruche 形式和Map很像
    //用法跟Map类似
    private LruCache<String,Bitmap> cache;

    public MemoryCache(){
        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);//之前是b然后变成kb//应用的内存空间,这个是系统分配的内存。
        int cacheSize =maxMemory/4;//大小随意。缓存占用的内存
        cache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回每一张图片占得内存大小 #透明度红绿蓝
                return value.getRowBytes()*value.getHeight()/1024;//之前是b然后变成kb//应用的内存空间,这个是系统分配的内存。
            }
        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);

    }
}
