package com.lanou.mirror.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * Created by zouwei on 16/3/2.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(setContent());
        initView();
        initData();
        BaseApplication.addActivity(this);

    }
    //获取数据
    protected abstract void initData();

    //绑定组件
    protected abstract void initView();

    //绑定布局
    protected abstract int setContent() ;





    //方便绑定布局的
    protected <T extends View>T BindView(int id){
        return (T)findViewById(id);
    }
}
