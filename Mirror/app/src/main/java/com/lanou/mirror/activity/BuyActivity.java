package com.lanou.mirror.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.bean.JSONAddress;
import com.lanou.mirror.bean.JSONOrder;
import com.lanou.mirror.bean.JSONpay;
import com.lanou.mirror.net.ImageLoaderHelper;
import com.lanou.mirror.net.NetOkHttpClient;
import com.lanou.mirror.pay.H5PayDemoActivity;
import com.lanou.mirror.pay.PayResult;
import com.lanou.mirror.pay.SignUtils;
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
    private JSONOrder jsonorder;
    private String goodsName;
    private String addrId;
    private String orderNo;

    String str = "service=\"mobile.securitypay.pay\"&partner=\"2088021758262531\"&_input_charset=\"utf-8\"¬ify_url=\"http%3A%2F%2Fapi.mirroreye.cn%2Findex.php%2Fali_notify\"&out_trade_no=\"1460518641e0l\"&subject=\"KAREN WALKER\"&payment_type=\"1\"&seller_id=\"2088021758262531\"&total_fee=\"1538.00\"&body=\"KAREN WALKER\"&it_b_pay =\"30m\"&sign=\"PquJroNOMX8wLqX0h36eQUkYjqhB4yYmwqILXIvMD4TBL4F%2FRSiSgr9wxdZujU8hgFs0qCtC2OorN5Tsbl3LLgtzXRHzcWmLVLQQREQvDy6vJOhrwYXrKhlHS%2FXPR2r21U9thEtW3IvXgjvCtnQdDkU0LWhRKgr%2FptH7P0OXqkg%3D\"&sign_type=\"RSA\"";
    private final static int SDK_PAY_FLAG = 1;
    public static String RSA_PRIVATE = "";// 商户私钥，pkcs8格式


    @Override
    protected void initData() {
        head = new HashMap<>();
        Intent intent = getIntent();
        //  intent.getStringExtra("buyGoods_id");
        //  MyLog.showLog("sssssss", intent.getStringExtra("getToken"));
        MyLog.showLog("0000000", intent.getStringExtra("getToken"));
        MyLog.showLog("0000000", intent.getStringExtra("img"));
        MyLog.showLog("0000000", intent.getStringExtra("goods_name"));
        MyLog.showLog("0000000", intent.getStringExtra("goods_price"));
        MyLog.showLog("0000000", intent.getStringExtra("buyGoods_id"));

        head.put("token", intent.getStringExtra("getToken"));
        head.put("goods_id", intent.getStringExtra("buyGoods_id"));
        head.put("goods_num", "1");
        head.put("price", intent.getStringExtra("goods_price"));
        head.put("device_type", "1");
        head.put("discout_id", "");
        NetOkHttpClient.postAsyn(URL.USER_ADDRESS_LIST, new NetOkHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) throws JSONException {
                MyLog.showLog("cvxcvxcv", response);
                Gson gson = new Gson();
                JSONAddress jsonAddress = gson.fromJson(response, JSONAddress.class);
                if (!jsonAddress.getData().getList().isEmpty()) {
                    nameDetails.setText(jsonAddress.getData().getList().get(0).getUsername());
                    addressDetails.setText(jsonAddress.getData().getList().get(0).getAddr_info());
                    phoneNumberDetails.setText(jsonAddress.getData().getList().get(0).getCellphone());
                }
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

                NetOkHttpClient.postAsyn(URL.ORDER_SUB, new NetOkHttpClient.ResultCallback<String>() {


                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) throws JSONException {
                        MyLog.showLog("wwwwwwwwww", response);
                        Gson gson = new Gson();
                        jsonorder = gson.fromJson(response, JSONOrder.class);
                        orderNo = jsonorder.getData().getOrder_id();
                        addrId = jsonorder.getData().getAddress().getAddr_id();
                        goodsName = jsonorder.getData().getGoods().getDes();
                    }
                }, head);

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // builder.setTitle("请选择支付方式");
                final View view1 = LayoutInflater.from(this).inflate(R.layout.item_buy_view, null);
                LinearLayout linearLayout = (LinearLayout) view1.findViewById(R.id.buy_linear_layout);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ShowToast.showToast("支付宝支付");
                        head.put("order_no", orderNo);
                        head.put("addr_id", addrId);
                        head.put("goodsname", goodsName);
                        NetOkHttpClient.postAsyn(URL.PAY_ALI, new NetOkHttpClient.ResultCallback<String>() {
                            @Override
                            public void onError(Request request, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) throws JSONException {
                                MyLog.showLog("解析支付宝", response);
                                Gson gson = new Gson();
                                JSONpay jsoNpay = gson.fromJson(response, JSONpay.class);
                                str = jsoNpay.getData().getStr();
                                MyLog.showLog("字符串", str);
                                String sign = sign(str);
                                /**
                                 * 完整的符合支付宝参数规范的订单信息
                                 */
                                final String payInfo = str + "&sign=\"" + sign + "\"&" + getSignType();

                                Runnable payRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        // 构造PayTask 对象
                                        PayTask alipay = new PayTask(BuyActivity.this);
                                        // 调用支付接口，获取支付结果
                                        String result = alipay.pay(payInfo, true);

                                        Message msg = new Message();
                                        msg.what = SDK_PAY_FLAG;
                                        msg.obj = result;
                                        mHandler.sendMessage(msg);
                                    }
                                };
                                // 必须异步调用
                                Thread payThread = new Thread(payRunnable);
                                payThread.start();
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
                Intent intent = new Intent(BuyActivity.this, AllAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    //支付宝之后的回调 判断是否支付成功

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // Toast.makeText(BaseActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        ShowToast.showToast("支付成功");
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            //  Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            ShowToast.showToast("支付失败8000");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            //  Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            ShowToast.showToast("支付失败");
                            MyLog.showLog("ssss", resultStatus);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.meituan.com";
        // url可以是一号店或者美团等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);

    }
}
