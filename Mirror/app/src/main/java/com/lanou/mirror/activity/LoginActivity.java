package com.lanou.mirror.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/5.
 */
public class LoginActivity extends BaseActivity {
    private EditText phoneEdt, passwordEdt;
    private Button loginBtn, registerBtn;
    private MyTextWatcher myTextWatcher;
    private ImageView closeImage;

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
        setLoginBtn(); // 设置登录按钮在editText没输入东西的时候为不可点击状态
        goBack(); // 返回首页
        goToRegister(); // 去注册界面
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
        phoneEdt.addTextChangedListener(myTextWatcher); // 添加phoneEdt的监听
        passwordEdt.addTextChangedListener(myTextWatcher);//添加passwordEdt的监听
        if (phoneEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()) {
            loginBtn.setEnabled(false);// 使loginBtn为不可点击状态
            loginBtn.setBackgroundResource(R.mipmap.login_btn_gray);
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
                loginBtn.setEnabled(true);// 使loginBtn为可点击状态
                loginBtn.setBackgroundResource(R.drawable.login_btn);
            }
        }
    }


    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }
}
