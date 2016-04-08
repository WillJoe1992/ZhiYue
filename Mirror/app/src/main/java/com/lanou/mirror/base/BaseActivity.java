package com.lanou.mirror.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.lanou.mirror.R;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * Created by zouwei on 16/3/2.
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置屏幕全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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


    @Override
    protected void onDestroy() {
        BaseApplication.removeActivity(this);
        super.onDestroy();
    }

    //方便绑定布局的
    protected <T extends View>T bindView(int id){
        return (T)findViewById(id);
    }
}
