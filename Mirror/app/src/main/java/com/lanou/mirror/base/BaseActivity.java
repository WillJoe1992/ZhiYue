package com.lanou.mirror.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by zouwei on 16/3/2.
 */
public abstract class BaseActivity extends AutoLayoutActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(setcontent());
        initView();
        initData();

    }
    //获取数据
    protected abstract void initData();

    //绑定组件
    protected abstract void initView();

    //绑定布局
    protected abstract int setcontent() ;


    //方便绑定布局的
    protected <T extends View>T BlindView (int id){
        return (T)findViewById(id);
    }
}
