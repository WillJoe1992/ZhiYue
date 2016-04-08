package com.lanou.mirror.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.mirror.tool.MyLog;

import java.util.ArrayList;

/**
 * Created by zouwei on 16/3/5.
 */
public abstract class BaseFragment extends Fragment {


    //绑定布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Sysout", "onCreateView");
        addFragment(this);
        return inflater.inflate(getLayout(),container,false);
    }
    public static ArrayList<Fragment>fragments;
    //添加Fregment
    public static void addFragment(Fragment fragment){
        if(fragments==null){
            fragments=new ArrayList<>();
        }
        fragments.add(fragment);
    }
    //移除Fragmnet
    public static void removeFragment(Fragment fragment){
        fragments.remove(fragment);
    }
    //绑定组件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        Log.d("Sysout", "onDestroy");
        super.onDestroy();
    }

    //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataView();
    }



    public abstract int getLayout();
    protected abstract void initView();
    protected abstract void dataView();

    //方便绑定布局的
    protected <T extends View>T bindView(int id){
        return (T)getView().findViewById(id);
    }

    @Override
    public void onPause() {
        MyLog.showLog("qqqq","onPause");

        removeFragment(this);
        super.onPause();
    }
}
