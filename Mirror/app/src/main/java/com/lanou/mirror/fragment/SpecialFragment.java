package com.lanou.mirror.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import com.lanou.mirror.activity.SpecialActivity;
import com.lanou.mirror.adapter.NotNetAllAdapter;
import com.lanou.mirror.adapter.NotNetSpecialAdapter;
import com.lanou.mirror.adapter.SpecialAdapter;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.base.BaseFragment;
import com.lanou.mirror.bean.JSONSpecial;
import com.lanou.mirror.greendaodemo.entity.greendao.AllHolderDao;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoMaster;
import com.lanou.mirror.greendaodemo.entity.greendao.DaoSession;
import com.lanou.mirror.greendaodemo.entity.greendao.LoginDao;
import com.lanou.mirror.greendaodemo.entity.greendao.Special;
import com.lanou.mirror.greendaodemo.entity.greendao.SpecialDao;
import com.lanou.mirror.greendaodemo.entity.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by dllo on 16/3/31.
 */
public class SpecialFragment extends BaseFragment{
    private RecyclerView homePageRecyclerView;
    private RelativeLayout titleSelect;
    public TextView fragmentHomepageTitle;
    private HashMap<String, String> head;
    private SpecialAdapter specialAdapter;
    private JSONSpecial jsonSpecial;
    private String title;
//
//    private NotNetAllAdapter notNetAllAdapter;
//    // 数据库
//    private SQLiteDatabase db;
//    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
//    private SpecialDao specialDao;
//    //操作数据库
//    // 管理者
//    private DaoMaster daoMaster;
//    // 会话
//    private DaoSession daoSession;
//    private LoginDao loginDao;
    private NotNetSpecialAdapter notNetSpecialAdapter;
    @Override
    public int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {

        head=new HashMap<>();
        titleSelect = bindView(R.id.title_select);
        fragmentHomepageTitle= bindView(R.id.fragment_homepage_title);
        Bundle bundle = getArguments();
        String titleName = (String) bundle.get("titleName");
        fragmentHomepageTitle.setText(titleName);
        head.put("device_type", "1");
        //初始化数据库
     //   setupDatabase();
        //用户已登录返回token
        if (UsingData.GetUsingData().getAllLoginDao().size()>0 && UsingData.GetUsingData().getAllLoginDao().get(0).getToken() != null) {
            MyLog.showLog("SpecialPagerdbtoken", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
            head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
        } else {
            head.put("token", "");
        }
        head.put("uid","");
        head.put("page","");
        head.put("last_time", "");
        titleSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = fragmentHomepageTitle.getText().toString();
                ((MainActivity) getActivity()).showPopupWindow(v, title);
            }
        });
        NetOkHttpClient.postAsyn(URL.TEST_STORY_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("111", "111");
                addNotNet();
                ShowToast.showToast("请检查网络");
            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                jsonSpecial=gson.fromJson(response,JSONSpecial.class);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                homePageRecyclerView.setLayoutManager(gridLayoutManager);
                specialAdapter=new SpecialAdapter(jsonSpecial);
                homePageRecyclerView.setAdapter(specialAdapter);
                //specialDao.deleteAll();
                UsingData.GetUsingData().deleteSpecialDao();
                addHolder();
                specialAdapter.MySpecialOnClick(new SpecialAdapter.SpecialOnClick() {
                    @Override
                    public void specialOnClick(int position) {
                        Intent intent=new Intent(getActivity(),SpecialActivity.class);
                        intent.putExtra("jsonSpecial", jsonSpecial);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
            }
        },head);
    }

    private void addNotNet() {
        if(UsingData.GetUsingData().getSpecialDao()!=null){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            homePageRecyclerView.setLayoutManager(gridLayoutManager);
            notNetSpecialAdapter=new NotNetSpecialAdapter(UsingData.GetUsingData().getSpecialDao());
            homePageRecyclerView.setAdapter(notNetSpecialAdapter);
        }
    //    MyLog.showLog("wewewewewew",""+specialDao.loadAll().get(0).getStory_img());
    }

    private void addHolder() {
        if(jsonSpecial!=null){
            for (int i = 0; i <jsonSpecial.getData().getList().size() ; i++) {
                Special special=new Special();
                special.setStory_img(jsonSpecial.getData().getList().get(i).getStory_img());
                special.setStory_title(jsonSpecial.getData().getList().get(i).getStory_title());
              //  specialDao.insert(special);
                UsingData.GetUsingData().addSpecialDao(special);
            }
        }else {
            ShowToast.showToast("请检查网络");
        }
    }


    @Override
    protected void dataView() {
        homePageRecyclerView = bindView(R.id.fragment_homepage_recyclerview);
    }

//    private void setupDatabase() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity()
//                , "AllHolder.db", null);
//        db = helper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//        specialDao = daoSession.getSpecialDao();
//        /////toke数据库
//        DaoMaster.DevOpenHelper helper2 = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), "Login.db", null);
//        SQLiteDatabase db2 = helper2.getWritableDatabase();
//        DaoMaster daoMaster2 = new DaoMaster(db2);
//        DaoSession daoSession2 = daoMaster2.newSession();
//        loginDao = daoSession2.getLoginDao();
//    }
}
