package com.lanou.mirror.greendaodemo.entity.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "HOME_PAGER".
 */
public class HomePager {

    private Long id;
    private String Goods_img;
    private String Brand;
    private String Model;
    private String Product_area;
    private String Goods_price;

    public HomePager() {
    }

    public HomePager(Long id) {
        this.id = id;
    }

    public HomePager(Long id, String Goods_img, String Brand, String Model, String Product_area, String Goods_price) {
        this.id = id;
        this.Goods_img = Goods_img;
        this.Brand = Brand;
        this.Model = Model;
        this.Product_area = Product_area;
        this.Goods_price = Goods_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoods_img() {
        return Goods_img;
    }

    public void setGoods_img(String Goods_img) {
        this.Goods_img = Goods_img;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getProduct_area() {
        return Product_area;
    }

    public void setProduct_area(String Product_area) {
        this.Product_area = Product_area;
    }

    public String getGoods_price() {
        return Goods_price;
    }

    public void setGoods_price(String Goods_price) {
        this.Goods_price = Goods_price;
    }

}
