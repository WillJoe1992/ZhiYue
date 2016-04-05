package com.lanou.mirror.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.ArrayList;

/**
 * Created by zouwei on 16/3/2.
 */
public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();

        context =this;

    }

    public static ArrayList<Activity> myActivitys;

    public static void addActivity(Activity activity) {
        if (myActivitys == null) {
            myActivitys = new ArrayList<>();
        }
        myActivitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        myActivitys.remove(activity);
    }

    public static void finshAll() {
        for(Activity activity:myActivitys){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public static Context getContext() {
        return context;
    }
}
