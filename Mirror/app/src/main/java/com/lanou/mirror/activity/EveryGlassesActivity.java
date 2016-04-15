package com.lanou.mirror.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
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
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONAllson;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetImageLoader;
import com.lanou.mirror.greendao.DaoMaster;
import com.lanou.mirror.greendao.DaoSession;
import com.lanou.mirror.greendao.LoginDao;
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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class EveryGlassesActivity extends BaseActivity implements ScrollViewListener, View.OnClickListener {

    private ObservableScrollView scrollViewFront = null;
    private ObservableScrollView scrollViewBack = null;

    private RecyclerView recyclerViewFront, recyclerViewBack;
    private EveryGlassesBackRecyclerViewAdapter backAdapter;
    private EveryGlassesFrontRecyclerViewAdapter frontAdapter;

    private RelativeLayout colorChanceRelativeLayout;
    private LinearLayout colorChanceLinearLayout;
    private AnimationDrawable animationDrawable;

    // 按钮
    private ImageView ivBack, ivBuy,ivShare;
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

    private int position;

    @Override
    protected void initView() {
        buttonLayout = bindView(R.id.everyglasses_button_layout);
        ivBack = bindView(R.id.everyglasses_button_back);
        ivBuy = bindView(R.id.everyglasses_button_buy);
        tvToPic = bindView(R.id.everyglasses_button_topic);
        backGround= bindView(R.id.background);

        backGround.setImageResource(R.drawable.loading);
        animationDrawable = (AnimationDrawable) backGround.getDrawable();
        animationDrawable.start();

        everyglassesEnglishTitle=bindView(R.id.everyglasses_englishTitle);
        everyglassesName=bindView(R.id.everyglasses_name);
        everyglassesGlassesContent=bindView(R.id.everyglasses_glassesContent);
        everyglassesPrice=bindView(R.id.everyglasses_price);
        everyglassesNameBeforerecyclerview=bindView(R.id.everyglasses_name_beforerecyclerview);

        ivShare = bindView(R.id.everyglasses_share);
        share(); // 分享
        setupDatabase();



        Intent intent =getIntent();
        goodsId=intent.getStringExtra("goodsId");
        position =intent.getIntExtra("position",100);
      //  NetImageLoader netImageLoader =new NetImageLoader();
       // netImageLoader.getImgOfLoader(backGround,intent.getStringExtra("picUrl"));
        ImageLoaderHelper imageLoaderHelper=new ImageLoaderHelper();
        imageLoaderHelper.loadImage(intent.getStringExtra("picUrl"),backGround);
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
                if(!jsonAllson.getData().getGoods_id().isEmpty()){
                    Intent intent = new Intent(EveryGlassesActivity.this,AtlasActivity.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
                break;
            case R.id.everyglasses_button_buy:
             //   Toast.makeText(EveryGlassesActivity.this, "点击了购买按钮", Toast.LENGTH_SHORT).show();
             //   MyLog.showLog("cccccccc",loginDao.loadAll().get(0).getToken());
                if(!UsingData.GetUsingData().getAllLoginDao().isEmpty()&&!jsonAllson.getData().getGoods_id().isEmpty()){
                    Intent intent1=new Intent(EveryGlassesActivity.this,BuyActivity.class);
                    intent1.putExtra("buyGoods_id",jsonAllson.getData().getGoods_id());
                    intent1.putExtra("getToken",UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                    intent1.putExtra("img",jsonAllson.getData().getDesign_des().get(0).getImg());
                    intent1.putExtra("goods_name",jsonAllson.getData().getGoods_name());
                    intent1.putExtra("goods_price",jsonAllson.getData().getGoods_price());
                    startActivity(intent1);
                }else {
                    Intent intentEveryGlasses=new Intent(EveryGlassesActivity.this,LoginActivity.class);
                    startActivity(intentEveryGlasses);
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

    public void share(){
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(BaseApplication.getContext());
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("mob分享");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(BaseApplication.getContext());


            }
        });
    }
}
