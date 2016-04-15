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
import com.lanou.mirror.greendao.DaoMaster;
import com.lanou.mirror.greendao.DaoSession;
import com.lanou.mirror.greendao.HomePager;
import com.lanou.mirror.greendao.HomePagerDao;
import com.lanou.mirror.greendao.LoginDao;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
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
    private NotNetHomePagerRecyclerViewAdapter notNetHomePagerRecyclerViewAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }


    @Override
    protected void initView() {
        //绑定布局
        head = new HashMap<>();
        titleSelect = bindView(R.id.title_select);
        fragmentHomepageTitle = bindView(R.id.fragment_homepage_title);
        //bundle传值的获取
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        String url = (String) bundle.get("CategoryId");
        //给head赋值然后进行网络拉取
        head.put("device_type", "1");
        //用户已登录返回token
        if (UsingData.GetUsingData().getAllLoginDao()!=null&& UsingData.GetUsingData().getAllLoginDao().size()>0) {
            MyLog.showLog("HomePagerdbtoken", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
            head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
        } else {
            head.put("token", "");
        }
        /**
         * 分类id
         */
    //    head.put("category_id", url);
        MyLog.showLog("aaaaaaaaaaaaaa","initView");
        MyLog.showLog("rrrrrrrrrrrrrrrrr", url);
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
                //homePagerDao.deleteAll();
                UsingData.GetUsingData().deleteHomePagerDao();
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

    //当没有网络时走的方法
    private void addNotNet() {
        if (UsingData.GetUsingData().getHomePagerDao() != null) {
            notNetHomePagerRecyclerViewAdapter = new NotNetHomePagerRecyclerViewAdapter(BaseApplication.getContext(), UsingData.GetUsingData().getHomePagerDao());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homePageRecyclerView.setLayoutManager(gridLayoutManager);
            homePageRecyclerView.setAdapter(notNetHomePagerRecyclerViewAdapter);
        } else {
            ShowToast.showToast("请检查网络");
        }
    }

    //获取数据。 并赋值
    private void addHolder() {
        for (int i = 0; i < jsonGlasses.getData().getList().size(); i++) {
            HomePager homePager = new HomePager();
            homePager.setGoods_img(jsonGlasses.getData().getList().get(i).getGoods_img());
            homePager.setBrand(jsonGlasses.getData().getList().get(i).getBrand());
            homePager.setModel(jsonGlasses.getData().getList().get(i).getModel());
            homePager.setProduct_area(jsonGlasses.getData().getList().get(i).getProduct_area());
            homePager.setGoods_price(jsonGlasses.getData().getList().get(i).getGoods_price());
            UsingData.GetUsingData().addHomePagerDao(homePager);
        }
    }


    @Override
    protected void dataView() {
        homePageRecyclerView = bindView(R.id.fragment_homepage_recyclerview);

    }


}
