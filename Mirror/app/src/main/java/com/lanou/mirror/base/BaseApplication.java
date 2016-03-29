package com.lanou.mirror.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/3/2.
 */
public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context =this;

    }


    public static Context getContext() {
        return context;
    }
}
