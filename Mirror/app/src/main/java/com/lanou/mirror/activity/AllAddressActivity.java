package com.lanou.mirror.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.adapter.AllAddressAdapter;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.JSONAddress;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.SwipeDeleteView;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wyc on 16/4/13.
 */
public class AllAddressActivity extends BaseActivity {
    private ImageView addAddressIv,closeIv;
    private HashMap<String, String> head;
    private AllAddressAdapter adapter;
    private SwipeDeleteView listView;
    private JSONAddress data;
    private String token = UsingData.GetUsingData().getAllLoginDao().get(0).getToken();
    @Override
    protected void initData() {
        jump();
        back();
    }

    @Override
    protected void initView() {
        addAddressIv = bindView(R.id.add_address_jump);
        closeIv = bindView(R.id.all_address_close);
        listView = bindView(R.id.all_address_listview);

    }
    public void jump(){
        addAddressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
        head=new HashMap<>();
        head.put("token", token);
        head.put("device_type", "1");
        NetOkHttpClient.postAsyn(URL.USER_ADDRESS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {
                MyLog.showLog("cvxcvxcv", response);
                data = new JSONAddress();
                Gson gson = new Gson();
                data = gson.fromJson(response,JSONAddress.class);
                Log.d("AllAddressActivity", "datas:" + data);
                Log.d("AllAddressActivity", response);
                adapter = new AllAddressAdapter(data,AllAddressActivity.this,token,listView.getRightViewWidth());
                // 删除
                adapter.setOnRightItemClickListener(new AllAddressAdapter.onRightItemClickListener() {
                    @Override
                    public void onRightItemClick(View v, int position) {
                        String addressId = data.getData().getList().get(position).getAddr_id();
                        data.getData().getList().remove(position);
                        adapter.setData(data);
                        listView.setAdapter(adapter);
                        HashMap<String,String> delete = new HashMap<String, String>();
                        delete.put("token",token);
                        delete.put("addr_id",addressId);
                        NetOkHttpClient.postAsyn(URL.USER_DEL_ADDRESS, new NetOkHttpClient.ResultCallback<String>() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) throws JSONException {

                            }
                        }, delete);

                    }
                });
                listView.setAdapter(adapter);
            }
        }, head);
    }
    public void back(){
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int setContent() {
        return R.layout.activity_all_address;
    }
}
