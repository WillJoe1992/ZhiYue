package com.lanou.mirror.activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.view.View;
import android.widget.ImageView;


import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by wyc on 16/4/13.
 */

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    private EditText addName, addPhone, addAddress;
    private Button addAddressBtn;
    private ImageView closeIv;
    private String token = "41bbc5786eb369a85bac122770325885";

    public static HashMap<String, String> data;


    @Override
    protected void initData() {
        back();


    }

    @Override
    protected void initView() {

        addName = bindView(R.id.add_address_add_name);
        addPhone = bindView(R.id.add_address_add_phone);
        addAddress = bindView(R.id.add_address_add_address);
        addAddressBtn = bindView(R.id.add_address_btn);
        closeIv = bindView(R.id.add_address_close);
        closeIv.setOnClickListener(this);
        addAddressBtn.setOnClickListener(this);
        closeIv = bindView(R.id.add_address_close);

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
        return R.layout.activity_add_address;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_btn:
                String name = addName.getText().toString();
                String phoneNumber = addPhone.getText().toString();
                String address = addAddress.getText().toString();
                if (name.isEmpty() && phoneNumber.isEmpty() && address.isEmpty()) {
                    data = new HashMap<>();
                    data.put("token", token);
                    data.put("name", name);
                    data.put("number", phoneNumber);
                    data.put("address", address);
                    NetOkHttpClient.postAsyn(URL.USER_ADD_ADDRESS, new NetOkHttpClient.ResultCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(Object response) throws JSONException {
                            Toast.makeText(AddAddressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddAddressActivity.this,AllAddressActivity.class);
                            intent.putExtra("token",token);
                            startActivity(intent);
                        }
                    }, data);
                } else {
                    Toast.makeText(AddAddressActivity.this, "你这没写全啊,亲", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_address_close:
                    finish();
                break;
        }

    }
}
