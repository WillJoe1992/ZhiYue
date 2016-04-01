package com.lanou.mirror.net;

import org.json.JSONObject;

/**
 * Created by dllo on 16/3/1.
 * 网络请求成功或失败的接口
 */
public interface NetListener {
    void getSuccess(JSONObject jsonObject);
    void getfailed(String string);
}
