package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.greendao.UsingData;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by wyc on 16/4/21.
 */
public class ReviseAddressActivity extends BaseActivity implements View.OnClickListener {
    private EditText nameEdt,numberEdt,addressEdt;
    private Button commitBtn;
    private String name,number,address;
    private String token = UsingData.GetUsingData().getAllLoginDao().get(0).getToken();
    private HashMap<String, String> head;


    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("phoneNumber");
        address = getIntent().getStringExtra("address");

        nameEdt.setText(name);
        numberEdt.setText(number);
        addressEdt.setText(address);

    }

    @Override
    protected void initView() {
        nameEdt = bindView(R.id.revise_add_name);
        numberEdt = bindView(R.id.revise_add_phone_number);
        addressEdt = bindView(R.id.revise_address);
        commitBtn = bindView(R.id.revise_address_btn);
        commitBtn.setOnClickListener(this);

    }

    @Override
    protected int setContent() {
        return R.layout.activity_revise_address;
    }

    @Override
    public void onClick(View v) {
        head = new HashMap<>();
        if (name != null &&
                number != null &
                        address != null) {
            head.put("username", nameEdt.getText().toString());
            head.put("cellphone", numberEdt.getText().toString());
            head.put("addr_info", addressEdt.getText().toString());
            head.put("zip_code", "150018");
            head.put("token", token);
            NetOkHttpClient.postAsyn(URL.USER_ADD_ADDRESS, new NetOkHttpClient.ResultCallback<String>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) throws JSONException {
                    Intent intent = new Intent(ReviseAddressActivity.this, AllAddressActivity.class);
                    intent.putExtra("token", token);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ReviseAddressActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }, head);
        }


    }
}
