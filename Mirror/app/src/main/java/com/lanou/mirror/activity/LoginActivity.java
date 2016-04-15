package com.lanou.mirror.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.mtp.MtpConstants;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.LoginBean;
import com.lanou.mirror.greendao.DaoMaster;
import com.lanou.mirror.greendao.DaoSession;
import com.lanou.mirror.greendao.Login;
import com.lanou.mirror.greendao.LoginDao;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * Created by wyc on 16/4/5.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText phoneEdt, passwordEdt;
    private Button loginBtn, registerBtn;
    private MyTextWatcher myTextWatcher;
    private ImageView closeImage, sinaLogin, weChatLogin;
    String phone, password;

    private HashMap<String, String> head;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        phoneEdt = bindView(R.id.login_phone_edt);
        passwordEdt = bindView(R.id.login_password_edt);
        loginBtn = bindView(R.id.login_btn);
        registerBtn = bindView(R.id.login_register_btn);
        closeImage = bindView(R.id.activity_login_close);
        sinaLogin = bindView(R.id.sina_login);
        weChatLogin = bindView(R.id.weichat_login);
        sinaLogin.setOnClickListener(this);
        // 设置登录按钮在editText没输入东西的时候为不可点击状态
        setLoginBtn();
        // 返回首页
        goBack();
        // 去注册界面
        goToRegister();
    }

    public void goBack() {
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void goToRegister() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setLoginBtn() {
        MyTextWatcher myTextWatcher = new MyTextWatcher();
        // 添加phoneEdt的监听
        phoneEdt.addTextChangedListener(myTextWatcher);
        //添加passwordEdt的监听
        passwordEdt.addTextChangedListener(myTextWatcher);
        if (phoneEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()) {
            // 使loginBtn为不可点击状态
            loginBtn.setEnabled(false);
            loginBtn.setBackgroundResource(R.mipmap.login_btn_gray);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sina_login:
                sinaGoToLogin();
                break;
            case R.id.weichat_login:
                weChatGoToLogin();
                break;
        }
    }

    // 对editText进行监听
    private class MyTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (phoneEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()) {
                loginBtn.setEnabled(false);
                loginBtn.setBackgroundResource(R.mipmap.login_btn_gray);
            } else if (phoneEdt.getText().toString().length() > 0 || passwordEdt.getText().toString().length() > 3) {
                // 使loginBtn为可点击状态
                loginBtn.setEnabled(true);
                loginBtn.setBackgroundResource(R.drawable.login_btn);
                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        phone = phoneEdt.getText().toString();
                        password = passwordEdt.getText().toString();
                        head = new HashMap<String, String>();
                        head.put("phone_number", phone);
                        head.put("password", password);
                        NetOkHttpClient.postAsyn(URL.USER_LOGIN, new NetOkHttpClient.ResultCallback<String>() {
                            @Override
                            public void onError(Request request, Exception e) {
                                ShowToast.showToast("网络连接错误");
                            }

                            @Override
                            public void onResponse(String response) throws JSONException {
                                MyLog.showLog("登录状态", response.toString());
                                LoginBean loginBean = new LoginBean();
                                Map<String, String> data = new HashMap<>();
                                data = loginBean.getLoginBean(response);
                                if (data.get("result").equals("1") && data != null) {
                                    ShowToast.showToast("登录成功");
                                    //将凭证传入数据库
                                    setupDatabase();
                                    UsingData.GetUsingData().deleteLoginDao();
                                    Login login = new Login();
                                    login.setToken(data.get("token"));
                                    login.setUid(data.get("uid"));
                                    UsingData.GetUsingData().addLoginDao(login);
                                    Intent intent = new Intent(BaseApplication.getContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    ShowToast.showToast(data.get("msg"));
                                }
                            }
                        }, head);
                    }
                });
            }
        }


    }


    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Login.db", null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }

    public void sinaGoToLogin() {
        ShareSDK.initSDK(this);
        Platform sinaPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (sinaPlatform.isAuthValid()) {
            sinaPlatform.removeAccount();
        }
        sinaPlatform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.i("android", platform.getDb().getUserName());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        sinaPlatform.SSOSetting(false);
        sinaPlatform.showUser(null);
    }

    public void weChatGoToLogin() {
        ShareSDK.initSDK(this);
        Platform sinaPlatform = ShareSDK.getPlatform(Wechat.NAME);
        if (sinaPlatform.isAuthValid()) {
            sinaPlatform.removeAccount();
        }
        sinaPlatform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.i("android", platform.getDb().getUserName());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        sinaPlatform.SSOSetting(false);
        sinaPlatform.showUser(null);
    }


}
