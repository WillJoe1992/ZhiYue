package com.lanou.mirror.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lanou.mirror.R;
import com.lanou.mirror.base.BaseActivity;
import com.lanou.mirror.pay.PayResult;
import com.lanou.mirror.pay.SignUtils;


/**
 * Created by dllo on 16/4/16.
 */
public class PayActivity extends BaseActivity{
    String str = "service=\"mobile.securitypay.pay\"&partner=\"2088021758262531\"&_input_charset=\"utf-8\"¬ify_url=\"http%3A%2F%2Fapi.mirroreye.cn%2Findex.php%2Fali_notify\"&out_trade_no=\"1460518641e0l\"&subject=\"KAREN WALKER\"&payment_type=\"1\"&seller_id=\"2088021758262531\"&total_fee=\"1538.00\"&body=\"KAREN WALKER\"&it_b_pay =\"30m\"&sign=\"PquJroNOMX8wLqX0h36eQUkYjqhB4yYmwqILXIvMD4TBL4F%2FRSiSgr9wxdZujU8hgFs0qCtC2OorN5Tsbl3LLgtzXRHzcWmLVLQQREQvDy6vJOhrwYXrKhlHS%2FXPR2r21U9thEtW3IvXgjvCtnQdDkU0LWhRKgr%2FptH7P0OXqkg%3D\"&sign_type=\"RSA\"";
    private final static int SDK_PAY_FLAG = 1;
    public static String RSA_PRIVATE = "";// 商户私钥，pkcs8格式

    @Override
    protected void initData() {
        String sign = sign(str);

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = str + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
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



    @Override
    protected void initView() {

    }

    @Override
    protected int setContent() {
        return R.layout.pay_main;
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
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();


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



}
