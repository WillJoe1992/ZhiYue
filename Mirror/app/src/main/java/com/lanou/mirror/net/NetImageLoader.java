package com.lanou.mirror.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dllo on 16/4/6.
 */
public class NetImageLoader {
    ImageLoader imageLoader;
    RequestQueue requestQueue;
    //路径名
    String diskPath;
    public NetImageLoader() {
        //通过Volley的静态方法:newRequestQueue获得对象;
        requestQueue = SingletonPattern.getSingletonPattern().getRequestQueue();
        //对ImageLoader的初始化的调用;
        initImageLoader();
    }

    private void initImageLoader() {
        //Environment 获得外部存储路径,如果含有SD卡及其读写权限
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //获取SD卡上的目录
            File file=Environment.getExternalStorageDirectory();
            diskPath=file.getAbsolutePath();
        }else {
            //固定存储路径可随应用删除
            File file = BaseApplication.getContext().getFilesDir() ;
            diskPath=file.getAbsolutePath();
        }
        Log.d("abc", "--->>>" + diskPath);
        //新建文件夹(判断文件是否存在不存在新建一个);
        File file=new File(diskPath+"/img");
        if(!file.exists()){
            file.mkdir();
        }
        diskPath=file.getAbsolutePath();
        imageLoader=new ImageLoader(requestQueue,new DoubleCache());

    }

    public class DoubleCache implements ImageLoader.ImageCache{
        DiskCache diskCache;
        MemoryCache memoryCache;

        public DoubleCache() {
            diskCache = new DiskCache();
            memoryCache = new MemoryCache();
        }

        @Override
        public Bitmap getBitmap(String url) {
            Bitmap bitmap = null;
            bitmap = memoryCache.getBitmap(url);
            if(bitmap == null){
                bitmap = diskCache.getBitmap(url);
            }
            return bitmap;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            memoryCache.putBitmap(url,bitmap);
            diskCache.putBitmap(url,bitmap);
        }
    }

    //用硬盘缓存网络获取的图片请求;
    public class DiskCache implements ImageLoader.ImageCache{
        public Bitmap getBitmap(String url) {



            String fileName=url.substring(url.lastIndexOf("/")+1,url.length());
            String filePath = diskPath+"/"+fileName;
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            return bitmap;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            String fileName=url.substring(url.lastIndexOf("/")+1,url.length());
            String filePath = diskPath+"/"+MD5(fileName);
            //以字节流的方式保存到所指定文件
            FileOutputStream fos = null;
            try {
                //以流的形式写入相应路径
                fos=new FileOutputStream(filePath);
                //将bitmap对象写入对象中;
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //关流
            }finally {
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //ImageLoader内部类,网络请求
    private class MemoryCache implements ImageLoader.ImageCache{
        //缓存区域大小
        private LruCache<String,Bitmap> cache;

        public MemoryCache() {
            //内存大小
            long maxSize=Runtime.getRuntime().maxMemory();
            //分配相应的缓存空间
            int cacheSize= (int) (maxSize/32);
            cache=new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    //图片的大小*图片显示在屏幕上的高/1024,等于图片占用空间的大小
                    return value.getRowBytes()*value.getHeight()/1024;
                }
            };
        }
        //得到Bitmap
        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url,bitmap);
        }
    }
    public void getImgOfLoader(ImageView imageView,String net){
        String url =net==null?"http://img5.imgtn.bdimg.com/it/u=115697286,2847986213&fm=21&gp=0.jpg":net;
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(url,listener);
    }
    /**
     * 将字符串转成16 位MD5值
     *
     * @param string
     * @return
     */
    private   String MD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }


        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));

        }

        return hex.toString();// 32位
    }
}
