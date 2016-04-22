package com.lanou.mirror.activity;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyc on 16/4/5.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView registerClose;
    private Button newUser, sms;
    private EditText phoneNumber, code, password;
    private Map<String, String> head;

    @Override
    protected void initData() {
        setRegisterClose();
        newUser.setOnClickListener(this);
        sms.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        registerClose = bindView(R.id.activity_register_close);
        newUser = bindView(R.id.login_new_user);
        phoneNumber = bindView(R.id.phone_number);
        code = bindView(R.id.code);
        password = bindView(R.id.password);
        sms = bindView(R.id.sms);
    }

    public void setRegisterClose() {
        registerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int setContent() {
        return R.layout.activity_register;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sms:
                head = new HashMap<>();
                if (phoneNumber.getText() != null) {
                    head.put("phone number", phoneNumber.getText().toString());
                    NetOkHttpClient.postAsyn(URL.USER_SEND_CODE, new NetOkHttpClient.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) throws JSONException {
                            MyToast.myToast(response);
                        }
                    }, head);
                } else {
                    ShowToast.showToast("请输入手机号");
                }
                break;
            case R.id.login_new_user:
                if (!phoneNumber.getText().toString().isEmpty() && !code.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    head = new HashMap<>();
                    head.put("phone_number", phoneNumber.getText().toString());
                    head.put("number", code.getText().toString());
                    head.put("password", password.getText().toString());
                    NetOkHttpClient.postAsyn(URL.USER_REG, new NetOkHttpClient.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) throws JSONException {
                            finish();
                        }
                    }, head);
                }
                break;
        }
    }
}
