package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.JSONAddress;
import com.lanou.mirror.greendaodemo.entity.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by wyc on 16/4/13.
 */
public class AllAddressActivity extends BaseActivity {
    private ImageView addAdressIv;
    private HashMap<String, String> head;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        addAdressIv = bindView(R.id.add_address_jump);
        addAdressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
        head=new HashMap<>();
        head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
        head.put("device_type", "1");
        NetOkHttpClient.postAsyn(URL.USER_ADDRESS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {
                MyLog.showLog("cvxcvxcv", response);
                Gson gson = new Gson();
                JSONAddress jsonAddress = gson.fromJson(response, JSONAddress.class);
            }
        }, head);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_all_address;
    }
}
