package com.lanou.mirror.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
import com.lanou.mirror.adapter.HomePagerRecyclerViewAdapter;
import com.lanou.mirror.adapter.NotNetAllAdapter;
import com.lanou.mirror.adapter.NotNetHomePagerRecyclerViewAdapter;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONGlasses;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.HomePager;
import com.lanou.mirror.greendaodemo.entity.greendao.HomePagerDao;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.List;

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

    private String title;
    // 数据库
    private SQLiteDatabase db;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    //  private AllHolderDao allHolderDao;
    private HomePagerDao homePagerDao;
    //操作数据库
    // 管理者
    private DaoMaster daoMaster;
    // 会话
    private DaoSession daoSession;

    private NotNetHomePagerRecyclerViewAdapter notNetHomePagerRecyclerViewAdapter;

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
        setupDatabase();
       // addNotNet();
        //网络拉取
        NetOkHttpClient.postAsyn(URL.GOODS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                addNotNet();
                ShowToast.showToast("请检查网络");
            }

            @Override
            public void onResponse(String response) {
                //获取到的数据放入gson类中。将数据放入横向滑动的recyclerview
                Gson gson = new Gson();
                jsonGlasses = gson.fromJson(response, JSONGlasses.class);
                homePagerRecyclerViewAdapter = new HomePagerRecyclerViewAdapter(getActivity(), jsonGlasses);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                homePageRecyclerView.setAdapter(homePagerRecyclerViewAdapter);
                homePagerDao.deleteAll();
                addHolder();
            }
        }, head);

        //获取到的tile把
        fragmentHomepageTitle.setText(titleName);

        //标题设置监听。点击后弹出popwindow
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = fragmentHomepageTitle.getText().toString();
                ((MainActivity) getActivity()).showPopupWindow(v, title);

            }
        });
    }

    private void addNotNet() {
        if (homePagerDao != null) {
            notNetHomePagerRecyclerViewAdapter = new NotNetHomePagerRecyclerViewAdapter(getContext(), homePagerDao);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homePageRecyclerView.setLayoutManager(gridLayoutManager);
            homePageRecyclerView.setAdapter(notNetHomePagerRecyclerViewAdapter);
        }else {
            ShowToast.showToast("请检查网络");
        }
    }

    private void addHolder() {
        for (int i = 0; i < jsonGlasses.getData().getList().size(); i++) {
            HomePager homePager = new HomePager();
            homePager.setGoods_img(jsonGlasses.getData().getList().get(i).getGoods_img());
            homePager.setBrand(jsonGlasses.getData().getList().get(i).getBrand());
            homePager.setModel(jsonGlasses.getData().getList().get(i).getModel());
            homePager.setProduct_area(jsonGlasses.getData().getList().get(i).getProduct_area());
            homePager.setGoods_price(jsonGlasses.getData().getList().get(i).getGoods_price());
            homePagerDao.insert(homePager);
        }
    }


    @Override
    protected void dataView() {
        homePageRecyclerView = BindView(R.id.fragment_homepage_recyclerview);
    }


    @Override
    public void onDestroy() {
        Log.d("Sysout", "Destroy");
        super.onDestroy();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), "AllHolder.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        homePagerDao = daoSession.getHomePagerDao();
    }
}
