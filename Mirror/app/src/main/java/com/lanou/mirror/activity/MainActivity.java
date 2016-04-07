package com.lanou.mirror.activity;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.adapter.VerticalPagerAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.fragment.AllFragment;
import com.lanou.mirror.fragment.HomePagerFragment;
import com.lanou.mirror.fragment.ShoppingCarFragment;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntity;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntityDao;
import com.lanou.mirror.bean.JSONGlassesClassification;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.fragment.SpecialFragment;
import com.lanou.mirror.tool.URL;
import com.lanou.mirror.tool.VerticalViewPager;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements SelectTitleRecyclerViewAdapter.ClickListener {
    private VerticalViewPager verticalViewPager;
    private TextView loginText;
    private ImageView mirrorIv;

    private HashMap<String, String> head;
    private JSONGlassesClassification jsonGlassesClassification;

    private List<Fragment> data;
    private long exitTime = 0;

    private TextView tvTop, tvBottom;
    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;
    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans;

    private PopupWindow popupWindow;





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
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private LabelEntityDao labelEntityDao;

    @Override
    protected void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_POSITION);
        registerReceiver(receiver, filter);
        head.put("token", "");
        NetOkHttpClient.postAsyn(URL.CATEGORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("bbbbbb", response);
                Gson gson = new Gson();
                jsonGlassesClassification = gson.fromJson(response, JSONGlassesClassification.class);
                data = getFragmentList();
                VerticalPagerAdapter fragmentAdapter = new VerticalPagerAdapter(
                        getSupportFragmentManager(), data);
                verticalViewPager.setAdapter(fragmentAdapter);
            }
        }, head);
    }
    @Override
    protected void initView() {
        verticalViewPager = bindView(R.id.vertical_viewpager);


        head = new HashMap<>();
        goToLogin();
        setMirrorAnim();
    }

    public List<Fragment> getFragmentList() {
        List<Fragment> listFragments = new ArrayList<Fragment>();


        Bundle bundleAll = new Bundle();
        bundleAll.putString("titleName", "瀏覽所有分類");
        bundleAll.putSerializable("CategoryId", "110");
        AllFragment fragmentAll = new AllFragment();
        fragmentAll.setArguments(bundleAll);
        listFragments.add(fragmentAll);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mirrorlib.db", null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        labelEntityDao = mDaoSession.getLabelEntityDao();

        labelEntityDao.deleteAll();
        for (int i = 0; i < jsonGlassesClassification.getData().size(); i++) {
            LabelEntity labelEntity = new LabelEntity((long) i, jsonGlassesClassification.getData().get(i).getCategory_name());
            labelEntityDao.insert(labelEntity);
        }

        for (int i = 0; i < jsonGlassesClassification.getData().size(); i++) {
            Bundle bundleFlatGlass = new Bundle();
            bundleFlatGlass.putString("titleName", jsonGlassesClassification.getData().get(i).getCategory_name());
            bundleFlatGlass.putString("CategoryId", jsonGlassesClassification.getData().get(i).getCategory_id());
            HomePagerFragment fragmentFlatGlass = new HomePagerFragment();
            fragmentFlatGlass.setArguments(bundleFlatGlass);
            listFragments.add(fragmentFlatGlass);

        }


        ///////////////////////////
        //将网络拉取的titile复用一个fragment代码如上
//        Bundle bundleFlatGlass = new Bundle();
//        bundleFlatGlass.putString("titleName", jsonGlassesClassification.getData().get(0).getCategory_name());
//        HomePagerFragment fragmentFlatGlass = new HomePagerFragment();
//        fragmentFlatGlass.setArguments(bundleFlatGlass);
//        listFragments.add(fragmentFlatGlass);
//
//        Bundle bundleSunGlass = new Bundle();
//        bundleSunGlass.putString("titleName", jsonGlassesClassification.getData().get(1).getCategory_name());
//        HomePagerFragment fragmentSunGlass = new HomePagerFragment();
//        fragmentSunGlass.setArguments(bundleSunGlass);
//        listFragments.add(fragmentSunGlass);
//
//        Bundle bundleHandGlass = new Bundle();
//        Log.d("sssss",jsonGlassesClassification.getData().get(2).getCategory_name());
//        bundleHandGlass.putString("titleName", jsonGlassesClassification.getData().get(2).getCategory_name());
//        HomePagerFragment fragmentHandGlass  = new HomePagerFragment();
//        fragmentHandGlass .setArguments(bundleHandGlass);
//        listFragments.add(fragmentHandGlass);
        ////////////////////////////


        Bundle bundleSpecial = new Bundle();
        SpecialFragment fragmentSpecial = new SpecialFragment();
        bundleSpecial.putString("titleName", "专题分享");
        fragmentSpecial.setArguments(bundleSpecial);
        listFragments.add(fragmentSpecial);


        Bundle bundleShoppingCart = new Bundle();
        bundleShoppingCart.putString("titleName", "我的购物车");
        bundleShoppingCart.putSerializable("CategoryId", "110");
        ShoppingCarFragment shoppingCarFragment=new ShoppingCarFragment();
        shoppingCarFragment.setArguments(bundleShoppingCart);
        listFragments.add(shoppingCarFragment);


        return listFragments;
    }

    @Override
    protected int setContent() {
        return R.layout.activity_main;
    }

    @Override
    public void setClickListener(int popMenuPosition) {
        verticalViewPager.setCurrentItem(popMenuPosition);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.ACTION_POSITION)) {
                int position = intent.getIntExtra("position", 0);
                Log.e("MainActivity", "position:" + position);
                verticalViewPager.setCurrentItem(position);

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public void goToLogin() {
        loginText = bindView(R.id.goto_login);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setMirrorAnim(){
        mirrorIv = bindView(R.id.mirror_icon);
        mirrorIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.2f, 1.0f, 1.2f, 1.0f).setDuration(600).start();
                ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.2f, 1.0f, 1.2f, 1.0f).setDuration(600).start();
            }
        });
    }

    public void showPopupWindow(View v,String title) {
//设置popwindow里的参数

        DisplayMetrics dm = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);
        View view = getLayoutInflater().inflate(R.layout.activity_select_title, null);
        popupWindow = new PopupWindow(view, dm.widthPixels, dm.heightPixels - 190, true);

//设置view的监听点其他地方退出
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });

//获取title如果数据库没有手动添加
        setupDatabase();
        if (labelEntityDao.loadAll().size() > 0) {
            selectTitleRecyclerBeans = new ArrayList<>();
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("瀏覽所有分類"));
            for (int i = 0; i < labelEntityDao.loadAll().size(); i++) {
                selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean(labelEntityDao.loadAll().get(i).getLabelname()));
            }
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("专题分享"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("我的购物车"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("返回首页"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("退出"));
        } else {
            selectTitleRecyclerBeans = new ArrayList<>();
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("瀏覽所有分類"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("手工阳镜"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("浏览平光镜"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("浏览太阳镜"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("专题分享"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("我的购物车"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("返回首页"));
            selectTitleRecyclerBeans.add(new SelectTitleRecyclerBean("退出"));
        }

        Log.d("SelectTitleActivity", title);
        for (int i = 0; i < selectTitleRecyclerBeans.size(); i++) {
            Log.d("SelectTitleActivity", selectTitleRecyclerBeans.get(i).getTitleName());
            if (selectTitleRecyclerBeans.get(i).getTitleName().equals(title)) {
                Log.d("SelectTitleActivity", title + "1111111111");
                selectTitleRecyclerBeans.get(i).setUnderLine(true);

            }
        }

        selectTitleRc = (RecyclerView) view.findViewById(R.id.select_title_rc);

        selectTitleRecyclerViewAdapter = new SelectTitleRecyclerViewAdapter(view.getContext(), selectTitleRecyclerBeans);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectTitleRc.setLayoutManager(gridLayoutManager);
        selectTitleRc.setAdapter(selectTitleRecyclerViewAdapter);
        selectTitleRecyclerViewAdapter.setPositionClickListener(new SelectTitleRecyclerViewAdapter.ClickListener() {
            @Override
            public void setClickListener(int popMenuPosition) {
                popupWindow.dismiss();
                Intent intent = new Intent();
                Log.e("SelectTitleActivity", "popMenuPosition:" + popMenuPosition);
                intent.setAction(Constant.ACTION_POSITION);
                intent.putExtra("position", popMenuPosition);
                sendBroadcast(intent);
            }
        });


        popupWindow.setContentView(view);

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);

    }
    //初始化数据库
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mirrorlib.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        labelEntityDao = daoSession.getLabelEntityDao();
    }

}
