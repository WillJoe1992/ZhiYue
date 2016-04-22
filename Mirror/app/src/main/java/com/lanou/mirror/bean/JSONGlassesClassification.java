package com.lanou.mirror.bean;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/4/1.
 */
public class JSONGlassesClassification implements Serializable {

    /**
     * result : 1
     * msg :
     * data : [{"category_name":"手工眼镜","category_id":"271"},{"category_name":"平光镜","category_id":"269"},{"category_name":"太阳镜","category_id":"268"}]
     */

    private String result;
    private String msg;
    /**
     * category_name : 手工眼镜
     * category_id : 271
     */

    private List<DataEntity> data;

    public void setResult(String result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }



    public static class DataEntity implements Serializable{
        private String category_name;
        private String category_id;

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public String getCategory_id() {
            return category_id;
        }


    }
}
