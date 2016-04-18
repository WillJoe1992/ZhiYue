package com.lanou.mirror.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.bean.JSONOrder;
import com.lanou.mirror.greendao.AllHolderDao;
import com.lanou.mirror.bean.JSONAddress;
import com.lanou.mirror.greendao.UsingData;
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
    private HashMap<String, String> head;
    private TextView buyGlassesTitle, buyMore, freight, subtotal, placeAnOrder, goToAllAddress, nameDetails, addressDetails, phoneNumberDetails;
    private ImageView buyImageView, buyDelete;
    private String orderNo, addId, goodName;

    @Override
    protected void initData() {
        head = new HashMap<>();
        Intent intent = getIntent();


        head.put("token", intent.getStringExtra("getToken"));
        head.put("goods_id", intent.getStringExtra("buyGoods_id"));
        head.put("device_type", "1");
        NetOkHttpClient.postAsyn(URL.JION_SHOPPING_CART, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {


            }
        }, head);
        NetOkHttpClient.postAsyn(URL.USER_ADDRESS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {
                Gson gson = new Gson();
                JSONAddress jsonAddress = gson.fromJson(response, JSONAddress.class);
                nameDetails.setText(jsonAddress.getData().getList().get(0).getUsername());
                addressDetails.setText(jsonAddress.getData().getList().get(0).getAddr_info());
                phoneNumberDetails.setText(jsonAddress.getData().getList().get(0).getCellphone());
            }
        }, head);
        ImageLoaderHelper imageLoaderHelper = new ImageLoaderHelper();
        imageLoaderHelper.loadImage(intent.getStringExtra("img"), buyImageView);
        buyGlassesTitle.setText(intent.getStringExtra("goods_name"));
        buyMore.setText("你所购买的商品为官网正品现货,我们将于三个工作日内为您免费发货,你所购买的商品均使用顺丰输运进行配送");
        subtotal.setText(intent.getStringExtra("goods_price"));
        freight.setText("免运费");
        placeAnOrder.setOnClickListener(this);
        buyDelete.setOnClickListener(this);
        goToAllAddress.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        buyGlassesTitle = bindView(R.id.buy_glasses_title);
        buyImageView = bindView(R.id.buy_glasses_iv);
        buyMore = bindView(R.id.buy_more);
        freight = bindView(R.id.freight);
        subtotal = bindView(R.id.subtotal);
        placeAnOrder = bindView(R.id.place_an_order);
        buyDelete = bindView(R.id.buy_delete);
        goToAllAddress = bindView(R.id.goto_alladdress);
        nameDetails = bindView(R.id.name_details);
        addressDetails = bindView(R.id.address_details);
        phoneNumberDetails = bindView(R.id.phone_number_details);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_buy;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.place_an_order:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View view1 = LayoutInflater.from(this).inflate(R.layout.item_buy_view, null);
                LinearLayout linearLayout = (LinearLayout) view1.findViewById(R.id.buy_linear_layout);
                HashMap<String, String> head = new HashMap<String, String>();
                Intent intent = getIntent();
                head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                head.put("goods_id", intent.getStringExtra("buyGoods_id"));
                head.put("goods_num", "1");
                head.put("price", intent.getStringExtra("getToken"));
                head.put("device_type", "1");
                NetOkHttpClient.postAsyn(URL.ORDER_SUB, new NetOkHttpClient.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws JSONException {
                        Gson gson = new Gson();
                        JSONOrder jsonOrder = gson.fromJson(response, JSONOrder.class);
                        orderNo = jsonOrder.getData().getOrder_id();
                        addId = jsonOrder.getData().getAddress().getAddr_id();
                        goodName = jsonOrder.getData().getGoods().getGoods_name();
                    }
                }, head);

                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ShowToast.showToast("支付宝支付");
                        final PayActivity payActivity = new PayActivity();
                        HashMap<String, String> head = new HashMap<String, String>();
                        head.put("token", UsingData.GetUsingData().getAllLoginDao().get(0).getToken());
                        head.put("order_no", orderNo);
                        head.put("addr_id", addId);
                        head.put("goodsname", goodName);
                        NetOkHttpClient.postAsyn(URL.PAY_ALI, new NetOkHttpClient.ResultCallback<String>() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) throws JSONException {
                                payActivity.sign(response);
                                payActivity.pay(view1);

                            }
                        }, head);
                    }
                });
                builder.setView(view1);
                builder.show();
                break;
            case R.id.buy_delete:
                finish();
                break;
            case R.id.goto_alladdress:
                //    Intent intent = new Intent(BuyActivity.this, AllAddressActivity.class);
                //    startActivity(intent);
                break;
        }
    }
}
