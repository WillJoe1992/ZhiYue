package com.lanou.mirror.bean;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by dllo on 16/4/8.
 */
public class LoginBean{
    public  Map<String,String> data=new HashMap<>();
   public  Map<String,String> getLoginBean(String jsonString) throws JSONException {
       JSONObject obj=new JSONObject(jsonString);
       String result=obj.getString("result");
       if(result.equals("1")){
           data.put("result", result);
           JSONObject o = obj.getJSONObject("data");
           String token=o.getString("token");
           data.put("token", token);
           String uid=o.getString("uid");
           data.put("uid", uid);
       }else {
           String msg=obj.getString("msg");
           data.put("msg", msg);
           data.put("result", "请求错误");
       }
       return data;
   }
}
