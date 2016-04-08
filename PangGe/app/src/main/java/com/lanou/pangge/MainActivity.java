package com.lanou.pangge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定时器
        new Timer();
        //一个方法 调度，安排演示或定时任务
        //schedule（）；
        //参数1：完成任务Task
        //参数2:定时时间，xx时间后执行任务
        //参数3：每隔一段时间重复执行任务
        new  Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("xxx","执行");
            }
        },2000,3000);
        new Timer().cancel();//停止
    }
}
