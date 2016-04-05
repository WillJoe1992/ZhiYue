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
        phoneEdt = BindView(R.id.login_phone_edt);
        passwordEdt = BindView(R.id.login_password_edt);
        loginBtn = BindView(R.id.login_btn);
        registBtn = BindView(R.id.login_regist_btn);
        setLoginBtn();
    }

    public void setLoginBtn() {
        if (phoneEdt.getText().toString().isEmpty() || passwordEdt.getText().toString().isEmpty()) {
            loginBtn.setEnabled(false);
            loginBtn.setBackgroundResource(R.mipmap.login_btn_gray);
        }

        phoneEdt.addTextChangedListener(new TextWatcher() {

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
                } else if (phoneEdt.getText().length() > 0 || passwordEdt.getText().length() > 3) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundResource(R.drawable.login_btn);
                    loginBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(LoginActivity.this, "啊啊啊啊啊啊啊啊", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }
}
