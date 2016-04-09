package com.lanou.mirror.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.EveryGlassesBackRecyclerViewAdapter;
import com.lanou.mirror.adapter.EveryGlassesFrontRecyclerViewAdapter;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.JSONAllson;
import com.lanou.mirror.bean.JSONGlasses;
<<<<<<< HEAD
import com.lanou.mirror.net.NetImageLoader;
=======
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LoginDao;
>>>>>>> feature/宋爱珍
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.FullyGridLayoutManager;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.ObservableScrollView;
import com.lanou.mirror.tool.ScrollViewListener;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;

public class EveryGlassesActivity extends BaseActivity implements ScrollViewListener, View.OnClickListener {

    private ObservableScrollView scrollViewFront = null;
    private ObservableScrollView scrollViewBack = null;

    private RecyclerView recyclerViewFront, recyclerViewBack;
    private EveryGlassesBackRecyclerViewAdapter backAdapter;
    private EveryGlassesFrontRecyclerViewAdapter frontAdapter;

    private RelativeLayout colorChanceRelativeLayout;
    private LinearLayout colorChanceLinearLayout;

    // 按钮
    private ImageView ivBack, ivBuy;
    private TextView tvToPic;
    private RelativeLayout buttonLayout;
    private boolean btnBL = false;

    private String goodsId;

    private HashMap<String, String> head;
    private JSONAllson jsonAllson;
    // 数据库
    private SQLiteDatabase db;
    // 管理者
    private DaoMaster mDaoMaster;
    // 会话
    private DaoSession mDaoSession;
    // 管理者
    private DaoMaster daoMaster;
    // 会话
    private DaoSession daoSession;

    private LoginDao loginDao;

    private TextView everyglassesEnglishTitle,everyglassesName,everyglassesGlassesContent,everyglassesPrice,everyglassesNameBeforerecyclerview;
    private ImageView backGround;

    @Override
    protected void initView() {
        buttonLayout = bindView(R.id.everyglasses_button_layout);
        ivBack = bindView(R.id.everyglasses_button_back);
        ivBuy = bindView(R.id.everyglasses_button_buy);
        tvToPic = bindView(R.id.everyglasses_button_topic);
        backGround= bindView(R.id.background);

        everyglassesEnglishTitle=bindView(R.id.everyglasses_englishTitle);
        everyglassesName=bindView(R.id.everyglasses_name);
        everyglassesGlassesContent=bindView(R.id.everyglasses_glassesContent);
        everyglassesPrice=bindView(R.id.everyglasses_price);
        everyglassesNameBeforerecyclerview=bindView(R.id.everyglasses_name_beforerecyclerview);
        setupDatabase();



        Intent intent =getIntent();
        goodsId=intent.getStringExtra("goodsId");
        NetImageLoader netImageLoader =new NetImageLoader();
        netImageLoader.getImgOfLoader(backGround,intent.getStringExtra("picUrl"));

        Log.d("EveryGlassesActivity", goodsId);
        head=new HashMap<>();
        //给head赋值然后进行网络拉取
        head.put("device_type", "1");
        head.put("token", "");
        head.put("goods_id", goodsId);





//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_create);
//        buttonLayout.setAnimation(animation);

        // 属性动画  刚进入页面时就隐藏到屏幕左边
        ObjectAnimator.ofFloat(buttonLayout, "translationX", 0, -800).setDuration(100).start();

        // 两层滑动的 scrollView
        scrollViewFront = bindView(R.id.everyglasses_scrollView_front);
        scrollViewFront.setScrollViewListener(this);
        scrollViewBack = bindView(R.id.everyglasses_scrollView_back);
//        scrollViewBack.setScrollViewListener(this);

        recyclerViewBack = bindView(R.id.everyglasses_recyclerviewBack);
        recyclerViewFront = bindView(R.id.everyglasses_recyclerviewFront);

        // 透明度渐变的地方
        colorChanceRelativeLayout = bindView(R.id.everyglasses_color_chance_relativeLayout);
        colorChanceLinearLayout = bindView(R.id.everyglasses_color_chance_linearLayout);


    }

    @Override
    protected int setContent() {
        return R.layout.activity_everyglasses;
    }

    @Override
    protected void initData() {
        backAdapter = new EveryGlassesBackRecyclerViewAdapter();
        frontAdapter = new EveryGlassesFrontRecyclerViewAdapter();

        FullyGridLayoutManager backManager = new FullyGridLayoutManager(this, 1);
        recyclerViewBack.setLayoutManager(backManager);
        FullyGridLayoutManager frontManager = new FullyGridLayoutManager(this, 1);
        recyclerViewFront.setLayoutManager(frontManager);


        // 添加数据的方法  输入的是一个集合

        //网络拉取
        NetOkHttpClient.postAsyn(URL.TEST_GOODS_INFO, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                // TODO: 16/4/6  如果拉取失败需要从数据库/本地获取

            }

            @Override
            public void onResponse(String response) {
                //获取到的数据放入gson类中。将数据放入横向滑动的recyclerview
                Gson gson = new Gson();
                jsonAllson = gson.fromJson(response, JSONAllson.class);

                backAdapter.addData(jsonAllson.getData().getDesign_des());
                frontAdapter.addData(jsonAllson.getData().getGoods_data());
                recyclerViewBack.setAdapter(backAdapter);
                recyclerViewFront.setAdapter(frontAdapter);

                colorChanceRelativeLayout.setAlpha((float) 0.8);
                colorChanceLinearLayout.setAlpha((float) 0.8);

                Log.d("EveryGlassesActivity", jsonAllson.getData().getGoods_name());
                Log.d("EveryGlassesActivity", jsonAllson.getData().getBrand());
                Log.d("EveryGlassesActivity", jsonAllson.getData().getInfo_des());
                Log.d("EveryGlassesActivity", jsonAllson.getData().getGoods_price());

                everyglassesEnglishTitle.setText(jsonAllson.getData().getGoods_name());
                everyglassesName.setText(jsonAllson.getData().getBrand());
                everyglassesGlassesContent.setText(jsonAllson.getData().getInfo_des());
                everyglassesPrice.setText(jsonAllson.getData().getGoods_price());
                everyglassesNameBeforerecyclerview.setText(jsonAllson.getData().getModel());



            }
        }, head);

        ivBack.setOnClickListener(this);
        tvToPic.setOnClickListener(this);
        ivBuy.setOnClickListener(this);


    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        if (scrollView == scrollViewFront) {
            scrollViewBack.scrollTo(x, y);


            // Y值改变的量
            int chanceY = (int) ((y / 0.8) - oldy);

            // 透明度渐变
            if (y > 0 && y <= 800) {
                if (chanceY > 0) {
                    //  除0.8  乘0.8 不能消
                    colorChanceRelativeLayout.setAlpha((float) (0.8 - y / 0.8 / 800 * 0.8 / 2));
                    colorChanceLinearLayout.setAlpha((float) (0.8 - y / 0.8 / 800 * 0.8 / 2));
                }

                if (chanceY < 0) {
                    int nowAlpha = (int) colorChanceLinearLayout.getAlpha();
                    colorChanceRelativeLayout.setAlpha((float) (0.8 - y / 0.8 / 800 * 0.8 / 2));
                    colorChanceLinearLayout.setAlpha((float) (0.8 - y / 0.8 / 800 * 0.8 / 2));
                }
            }


            // 滑动按钮飞入.飞出
            if (y > 2700) {
                if (chanceY > 0) {
                    if (btnBL == false) {
                        ObjectAnimator.ofFloat(buttonLayout, "translationX", -800, 0).setDuration(1000).start();
                        btnBL = true;
                    }
                }

            }
            if (y < 2650) {
                if (chanceY < 0) {
                    if (btnBL == true) {
                        ObjectAnimator.ofFloat(buttonLayout, "translationX", 0, -800).setDuration(1000).start();
                        btnBL = false;
                    }
                }
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.everyglasses_button_back:
                // TODO 跳转
                finish();
                Toast.makeText(EveryGlassesActivity.this, "点击了返回按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.everyglasses_button_topic:
                Intent intent = new Intent(EveryGlassesActivity.this,AtlasActivity.class);
                startActivity(intent);
                break;
            case R.id.everyglasses_button_buy:
             //   Toast.makeText(EveryGlassesActivity.this, "点击了购买按钮", Toast.LENGTH_SHORT).show();
                MyLog.showLog("cccccccc",loginDao.loadAll().get(0).getToken());
                if(!loginDao.loadAll().get(0).getToken().isEmpty()&&loginDao.loadAll().get(0).getToken().length()>0){
                   MyLog.showLog("hhhhhhhhhh","hhhhhhhhhh");
                }else {
                    Intent intent=new Intent(EveryGlassesActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
    //初始化数据库
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Login.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        loginDao = daoSession.getLoginDao();
    }
}
