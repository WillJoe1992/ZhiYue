package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;

/**
 * Created by wyc on 16/4/13.
 */
public class AllAddressActivity extends BaseActivity {

    private ImageView addAdressIv;


    private ImageView addAdressIv,closeIv;

    @Override
    protected void initData() {
        jump();
        back();
    }

    @Override
    protected void initView() {
        addAdressIv = bindView(R.id.add_address_jump);
        closeIv = bindView(R.id.all_address_close);

    }
    public void jump(){
        addAdressIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });
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
        return R.layout.activity_all_address;
    }
}
