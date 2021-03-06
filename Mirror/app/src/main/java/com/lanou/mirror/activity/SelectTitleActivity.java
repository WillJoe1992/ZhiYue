package com.lanou.mirror.activity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.constant.Constant;
import com.lanou.mirror.greendao.DaoMaster;
import com.lanou.mirror.greendao.DaoSession;
import com.lanou.mirror.greendao.LabelEntityDao;
import com.lanou.mirror.tool.MyLog;

import java.util.ArrayList;


public class SelectTitleActivity extends BaseActivity implements SelectTitleRecyclerViewAdapter.ClickListener {


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

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTop = bindView(R.id.tv_top);
        tvBottom = bindView(R.id.tv_bottom);
        selectTitleRc = bindView(R.id.select_title_rc);

        tvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent getIntent = getIntent();
        String title = getIntent.getStringExtra("title");
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

        for (int i = 0; i < selectTitleRecyclerBeans.size(); i++) {
            if (selectTitleRecyclerBeans.get(i).getTitleName().equals(title)) {
                selectTitleRecyclerBeans.get(i).setUnderLine(true);

            }
        }

        selectTitleRecyclerViewAdapter = new SelectTitleRecyclerViewAdapter(this, selectTitleRecyclerBeans);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        selectTitleRc.setLayoutManager(gridLayoutManager);
        selectTitleRc.setAdapter(selectTitleRecyclerViewAdapter);


        selectTitleRecyclerViewAdapter.setPositionClickListener(this);


    }

    @Override
    protected int setContent() {
        return R.layout.activity_select_title;
    }


    @Override
    public void setClickListener(int popMenuPosition) {
        finish();
        Intent intent = new Intent();
        intent.setAction(Constant.ACTION_POSITION);
        intent.putExtra("position", popMenuPosition);
        sendBroadcast(intent);
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mirrorlib.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        labelEntityDao = daoSession.getLabelEntityDao();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(BaseApplication.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
