package com.saz.vollypost3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Netloader netloader;
    private TextView textView;
    private HashMap<String, String> head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TextView);

//        head = new HashMap<String, String>();
//        head.put("token", "41bbc5786eb369a85bac122770325885");
//        head.put("uid", "50");
//        head.put("device_type", "iPhone6");
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        BasicNameValuePair pair = new BasicNameValuePair("token", "41bbc5786eb369a85bac122770325885");
        BasicNameValuePair pair1 = new BasicNameValuePair("uid", "50");
        BasicNameValuePair pair2 = new BasicNameValuePair("device_type", "iPhone6");
        list.add(pair);
        list.add(pair1);
        list.add(pair2);
         String url = "http://api101.test.mirroreye.cn/index.php/story/story_list";
        NetWorkLoader.getInstance().loadOrdinaryPostData(url, new NetWorkLoader.NetResponseListener() {
            @Override
            public void success(String resultString) {
                textView.setText(resultString);
            }

            @Override
            public void fail(String failString, Exception e) {

            }
        }, list);




//        Netloader.getInstance().postKeyValue("http://api101.test.mirroreye.cn/index.php/story/story_list", head, new Netloader.ResponseListener() {
//
//            @Override
//            public void suc(Response response) throws Exception {
//                textView.setText(response.body().string());
//            }
//
//            @Override
//            public void fail(Request request, Exception e) {
//
//            }
//        });


    }


}
