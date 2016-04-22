package com.lanou.mirror.bean;

/**
 * Created by dllo on 16/3/31.
 */
public class SelectTitleRecyclerBean {
    private String titleName;
    private boolean underLine=false;


    public SelectTitleRecyclerBean(String titleName) {
        this.titleName = titleName;
    }
    public SelectTitleRecyclerBean(String titleName,boolean underLine) {
        this.titleName = titleName;
        this.underLine = underLine;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public boolean isUnderLine() {
        return underLine;
    }

    public void setUnderLine(boolean underLine) {
        this.underLine = underLine;
    }
}
