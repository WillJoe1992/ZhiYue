package com.lanou.mirror.activity;

import android.view.View;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/5.
 */
public class RegisterActivity extends BaseActivity {
    private ImageView registerClose;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        registerClose = bindView(R.id.activity_register_close);
        setRegisterClose();

    }
    public void setRegisterClose(){
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
}
