package com.lanou.mirror.tool;

import android.widget.Toast;

import com.lanou.mirror.base.BaseApplication;
/**
 * Created by dllo on 16/3/3.
 */
public class ShowToast {
    public static void showToast(String data){
        if(true){
            Toast.makeText(BaseApplication.getContext(), data, Toast.LENGTH_SHORT).show();
        }
    }
}
