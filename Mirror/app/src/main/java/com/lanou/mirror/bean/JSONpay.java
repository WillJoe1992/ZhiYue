package com.lanou.mirror.bean;

/**
 * Created by dllo on 16/4/15.
 */
public class JSONpay {

    /**
     * msg :
     * data : {"str":"service=\"mobile.securitypay.pay\"&partner=\"2088021758262531\"&_input_charset=\"utf-8\"&notify_url=\"http%3A%2F%2Fapi.mirroreye.cn%2Findex.php%2Fali_notify\"&out_trade_no=\"1460724832E2G\"&subject=\"KAREN WALKER\"&payment_type=\"1\"&seller_id=\"2088021758262531\"&total_fee=\"1538.00\"&body=\"KAREN WALKER\"&it_b_pay =\"30m\"&sign=\"TNNOb6dKSstI01puxojIXAxSh68c%2B%2BqpY894E7p7bTg8rsoehnJxMvk5Pr87rHcsasbhRQ%2FiUuaOw%2Fao5760sqEb4KSXVs%2Ft1qmz7QWgBx8DWf1g%2BqiwlKcZB6gwOMKd8S33dG4KM3oclmm1bDb%2Bo4zNJqXTchTtLWNEkLh1k0Q%3D\"&sign_type=\"RSA\""}
     */

    private String msg;
    /**
     * str : service="mobile.securitypay.pay"&partner="2088021758262531"&_input_charset="utf-8"&notify_url="http%3A%2F%2Fapi.mirroreye.cn%2Findex.php%2Fali_notify"&out_trade_no="1460724832E2G"&subject="KAREN WALKER"&payment_type="1"&seller_id="2088021758262531"&total_fee="1538.00"&body="KAREN WALKER"&it_b_pay ="30m"&sign="TNNOb6dKSstI01puxojIXAxSh68c%2B%2BqpY894E7p7bTg8rsoehnJxMvk5Pr87rHcsasbhRQ%2FiUuaOw%2Fao5760sqEb4KSXVs%2Ft1qmz7QWgBx8DWf1g%2BqiwlKcZB6gwOMKd8S33dG4KM3oclmm1bDb%2Bo4zNJqXTchTtLWNEkLh1k0Q%3D"&sign_type="RSA"
     */

    private DataEntity data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String str;

        public void setStr(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }
}
