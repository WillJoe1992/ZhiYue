package com.lanou.mirror.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/5.
 */
public class LoginActivity extends BaseActivity {
    private EditText phoneEdt, passwordEdt;
    private Button loginBtn, registBtn;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        phoneEdt = BlindView(R.id.login_phone_edt);
        passwordEdt = BlindView(R.id.login_password_edt);
        loginBtn = BlindView(R.id.login_btn);
        registBtn = BlindView(R.id.login_regist_btn);
        setLoginBtn();
    }

    public void setLoginBtn() {
    loginBtn.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });


    }

    @Override
    protected int setcontent() {
        return R.layout.activity_login;
    }
}
