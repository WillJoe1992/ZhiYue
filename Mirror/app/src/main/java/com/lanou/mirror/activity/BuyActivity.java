package com.lanou.mirror.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.tool.MyLog;
import com.lanou.mirror.tool.MyToast;
import com.lanou.mirror.tool.ShowToast;
import com.lanou.mirror.tool.URL;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by dllo on 16/4/11.
 */
public class BuyActivity extends BaseActivity implements View.OnClickListener {
    private HashMap<String,String>head;
    private TextView buyGlassesTitle,buyMore,freight,subtotal,placeAnOrder;
    private ImageView buyImageView,buyDelete;
    @Override
    protected void initData() {
        head=new HashMap<>();
        Intent intent=getIntent();
        //  intent.getStringExtra("buyGoods_id");
        //  MyLog.showLog("sssssss", intent.getStringExtra("getToken"));
        MyLog.showLog("0000000",intent.getStringExtra("getToken"));
        MyLog.showLog("0000000",intent.getStringExtra("img"));
        MyLog.showLog("0000000",intent.getStringExtra("goods_name"));
        MyLog.showLog("0000000",intent.getStringExtra("goods_price"));
        MyLog.showLog("0000000", intent.getStringExtra("buyGoods_id"));

        head.put("token", intent.getStringExtra("getToken"));
        head.put("goods_id", intent.getStringExtra("buyGoods_id"));
        NetOkHttpClient.postAsyn(URL.JION_SHOPPING_CART, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {
                MyLog.showLog("wwwwwwwwww", response);
            }
        }, head);
        ImageLoaderHelper imageLoaderHelper=new ImageLoaderHelper();
        imageLoaderHelper.loadImage(intent.getStringExtra("img"),buyImageView);
        buyGlassesTitle.setText(intent.getStringExtra("goods_name"));
        buyMore.setText("你所购买的商品为官网正品现货,我们将于三个工作日内为您免费发货,你所购买的商品均使用顺丰输运进行配送");
        freight.setText(intent.getStringExtra("goods_price"));
        subtotal.setText("免运费");
        placeAnOrder.setOnClickListener(this);
        buyDelete.setOnClickListener(this);
    }

    @Override
    protected void initView() {
     buyGlassesTitle=bindView(R.id.buy_glasses_title);
     buyImageView=bindView(R.id.buy_glasses_iv);
     buyMore=bindView(R.id.buy_more);
     freight=bindView(R.id.freight);
     subtotal=bindView(R.id.subtotal);
     placeAnOrder=bindView(R.id.place_an_order);
     buyDelete=bindView(R.id.buy_delete);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_buy;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.place_an_order:
                ShowToast.showToast("购买");
                break;
            case R.id.buy_delete:
                finish();
                break;
        }
    }
}
