package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/13.
 */
public class AllAddressActivity extends BaseActivity {
    private ImageView addAdressIv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        addAdressIv = bindView(R.id.add_address_jump);
        addAdressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int setContent() {
        return R.layout.activity_all_address;
    }
}
