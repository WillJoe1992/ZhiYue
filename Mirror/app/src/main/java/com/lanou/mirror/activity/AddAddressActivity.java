package com.lanou.mirror.activity;

import android.view.View;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/13.
 */
public class AddAddressActivity extends BaseActivity {
    private ImageView closeIv;

    @Override
    protected void initData() {
        back();

    }

    @Override
    protected void initView() {
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
}
