package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.view.View;
import android.widget.ImageView;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wyc on 16/4/13.
 */

public class AddAddressActivity extends BaseActivity {
    private EditText addName, addPhoneNumber, addCity, addAddress;
    private Button button;
    private HashMap<String, String> head;

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                head = new HashMap<>();
                if (addAddress.getText().length() > 0 &&
                        addCity.getText().length() > 0 &&
                        addPhoneNumber.getText().length() > 0 &
                        addName.getText().length() > 0) {
                    Toast.makeText(AddAddressActivity.this, "fdsfdsfsdf", Toast.LENGTH_SHORT).show();
                    head.put("username", addName.getText().toString());
                    head.put("cellphone", addPhoneNumber.getText().toString());
                    head.put("addr_info", addAddress.getText().toString());
                    head.put("zip_code", "150018");
                    head.put("city", addCity.getText().toString());
                    //    head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                    head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                    NetOkHttpClient.postAsyn(URL.USER_ADD_ADDRESS, new NetOkHttpClient.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) throws JSONException {
                            MyLog.showLog("dsdsdsd", response);
                        }
                    }, head);
                } else {
                    ShowToast.showToast("请输入完整参数");
                }
            }
        });
    }

    @Override
    protected void initView() {
        addName = bindView(R.id.add_name);
        addPhoneNumber = bindView(R.id.add_phone_number);
        addCity = bindView(R.id.add_city);
        addAddress = bindView(R.id.add_address);
        button = bindView(R.id.add_address_btn);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_add_address;
    }
}
