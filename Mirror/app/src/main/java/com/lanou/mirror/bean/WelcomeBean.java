package com.lanou.mirror.bean;

/**
 * Created by wyc on 16/4/12.
 */
public class WelcomeBean {

    /**
     * text : 雪屋07号
     * img : http://pic1.zhimg.com/e1cc747cbf2076a378d2fe0f8c3b2e20.jpg
     */

    private String text;
    private String img;

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }
}
