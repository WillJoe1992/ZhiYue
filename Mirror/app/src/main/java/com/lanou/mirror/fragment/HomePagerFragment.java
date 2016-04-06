package com.lanou.mirror.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.SelectTitleActivity;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LabelEntityDao;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wyc on 16/3/29.
 */
public class HomePagerFragment extends BaseFragment {
    private RecyclerView homePageRecyclerView;
    private HomePagerRecyclerViewAdapter homePagerRecyclerViewAdapter;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;
    private HashMap<String, String> head;
    private JSONGlasses jsonGlasses;

    private TextView tvTop, tvBottom;
    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;
    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans;
    // 数据库
    private SQLiteDatabase db;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private LabelEntityDao labelEntityDao;
    //操作数据库
    // 管理者
    private DaoMaster daoMaster;
    // 会话
    private DaoSession daoSession;
    private PopupWindow popupWindow;

    private String title;

    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }


    @Override
    protected void initView() {
        //绑定布局
        head = new HashMap<>();
        titleSelect = BindView(R.id.title_select);
        fragmentHomepageTitle = BindView(R.id.fragment_homepage_title);
        //bundle传值的获取
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        String url = (String) bundle.get("CategoryId");

        //给head赋值然后进行网络拉取
        head.put("device_type", "1");
        head.put("token", "");
        head.put("goods_id", url);
        Log.d("aaaaaaaa", url);
        //网络拉取
        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                // TODO: 16/4/6  如果拉取失败需要从数据库/本地获取

            }

            @Override
            public void onResponse(String response) {
                //获取到的数据放入gson类中。将数据放入横向滑动的recyclerview
                Gson gson = new Gson();
                jsonGlasses = gson.fromJson(response, JSONGlasses.class);
                homePagerRecyclerViewAdapter = new HomePagerRecyclerViewAdapter(getContext(), jsonGlasses);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                homePageRecyclerView.setAdapter(homePagerRecyclerViewAdapter);
            }
        }, head);

        //获取到的tile把
        fragmentHomepageTitle.setText(titleName);

        //标题设置监听。点击后弹出popwindow
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = fragmentHomepageTitle.getText().toString();
                showPopupWindow(v);

            }
        });
    }

    private void showPopupWindow(View v) {
//设置popwindow里的参数

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_select_title, null);
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
                getContext().sendBroadcast(intent);
            }
        });


        popupWindow.setContentView(view);

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);

    }

    @Override
    protected void dataView() {
        homePageRecyclerView = BindView(R.id.fragment_homepage_recyclerview);

    }
//初始化数据库
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "mirrorlib.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        labelEntityDao = daoSession.getLabelEntityDao();
    }


}
