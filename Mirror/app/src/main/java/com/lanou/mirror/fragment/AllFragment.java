package com.lanou.mirror.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.activity.MainActivity;
import com.lanou.mirror.adapter.AllRecyclerViewAdapter;
import com.lanou.mirror.adapter.NotNetAllAdapter;
import com.lanou.mirror.adapter.SelectTitleRecyclerViewAdapter;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONAll;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.bean.SelectTitleRecyclerBean;
import com.lanou.mirror.greendao.AllHolder;
import com.lanou.mirror.greendao.DaoMaster;
import com.lanou.mirror.greendao.DaoSession;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dllo on 16/4/1.
 */

public class AllFragment extends BaseFragment {
    private RecyclerView homePageRecyclerView;
    private AllRecyclerViewAdapter allRecyclerViewAdapter;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;

    private HashMap<String, String> headGlasses;
    private HashMap<String, String> headSpecial;
    private JSONAll jsonAll;
    private JSONSpecial jsonSpecial;

    private ArrayList<SelectTitleRecyclerBean> selectTitleRecyclerBeans;
    private String title;
    private PopupWindow popupWindow;
    private RecyclerView selectTitleRc;
    private SelectTitleRecyclerViewAdapter selectTitleRecyclerViewAdapter;
    private NotNetAllAdapter notNetAllAdapter;
    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        titleSelect = bindView(R.id.title_select);
        homePageRecyclerView = bindView(R.id.fragment_homepage_recyclerview);
        fragmentHomepageTitle = bindView(R.id.fragment_homepage_title);

        //bundle 传值的获取
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        String url = (String) bundle.get("CategoryId");
        fragmentHomepageTitle.setText(titleName);

        headGlasses = new HashMap<>();
        headGlasses.put("device_type", "1");
        //用户已登录返回token
        if (UsingData.GetUsingData().getAllLoginDao().size()>0 && UsingData.GetUsingData().getAllLoginDao().get(0).getToken() != null) {
            MyLog.showLog("ALLdbtoken",UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
           headGlasses.put("token",UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
        } else {
            headGlasses.put("token", "");
        }
        headGlasses.put("page", "");
        headGlasses.put("last_time", "");
        NetOkHttpClient.postAsyn(URL.INDEX_MRTJ, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                addNotNet();
                ShowToast.showToast("网络连接错误");
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                jsonAll = gson.fromJson(response, JSONAll.class);
                allRecyclerViewAdapter = new AllRecyclerViewAdapter(getActivity(), jsonAll);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
                UsingData.GetUsingData().deleteAllHolderDao(UsingData.GetUsingData().getHolderDa());
                addHolder(jsonAll);
            }
        }, headGlasses);
//        headSpecial=new HashMap<>();
//        headSpecial.put("device_type","1");
//        headSpecial.put("token","");
//        headSpecial.put("uid","");
//        headSpecial.put("page","");
//        headSpecial.put("last_time", "");
//
//        NetOkHttpClient.postAsyn(URL.TEST_STORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
//            @Override
//            public void onError(Request request, Exception e) {
//
//
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                jsonSpecial = gson.fromJson(response, JSONSpecial.class);
//                allRecyclerViewAdapter = new AllRecyclerViewAdapter(getContext(),jsonGlasses,jsonSpecial);
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                homePageRecyclerView.setLayoutManager(gridLayoutManager);
//                homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
//
//
//
//            }
//        }, headSpecial);


        ////////////
//        allRecyclerViewAdapter = new AllRecyclerViewAdapter(getContext(),jsonGlasses,jsonSpecial);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        homePageRecyclerView.setLayoutManager(gridLayoutManager);
//        homePageRecyclerView.setAdapter(allRecyclerViewAdapter);
//
//
//
//        titleSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), SelectTitleActivity.class);
//                intent.putExtra("title",fragmentHomepageTitle.getText());
//                startActivity(intent);
//            }
//        });

       //标签点击后弹popupwindow
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = fragmentHomepageTitle.getText().toString();
                ((MainActivity) getActivity()).showPopupWindow(v, title);

            }
        });
    }
//没有网络时的处理方式
    private void addNotNet() {
        if (UsingData.GetUsingData().getAllHolderDao().size()>0) {
            notNetAllAdapter = new NotNetAllAdapter(BaseApplication.getContext(), UsingData.GetUsingData().getHolderDa());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homePageRecyclerView.setLayoutManager(gridLayoutManager);
            homePageRecyclerView.setAdapter(notNetAllAdapter);
            ShowToast.showToast("网络连接错误");
        }
    }

    private void addHolder(JSONAll jsonAll) {
        for (int i = 0; i < jsonAll.getData().getList().size(); i++) {
            AllHolder allHolder = new AllHolder();
            allHolder.setGoods_img(jsonAll.getData().getList().get(i).getData_info().getGoods_img());
            allHolder.setBrand(jsonAll.getData().getList().get(i).getData_info().getBrand());
            allHolder.setModel(jsonAll.getData().getList().get(i).getData_info().getModel());
            allHolder.setGoods_price(jsonAll.getData().getList().get(i).getData_info().getGoods_price());
            allHolder.setType(jsonAll.getData().getList().get(i).getType());
         //   allHolderDao.insert(allHolder);
            UsingData.GetUsingData().addAllHolderDao(allHolder);
        }

    }
    @Override
    protected void dataView() {

    }


}