package com.lanou.mirror.bean;

/**
 * Created by dllo on 16/4/15.
 */
public class JSONOrder {

    /**
     * result : 1
     * msg :
     * data : {"order_id":"1460707577dMB","address":{"addr_id":"648","username":"王璐","cellphone":"13946029872","addr_info":"黑龙江省哈尔滨市松北区糖厂街1号","zip_code":"150027","city":"哈尔滨"},"goods":{"goods_name":"GENTLE MONSTER","goods_num":"","des":"BIG BULLY 飛行員太陽鏡","price":"1300","pic":"http://7xprhi.com2.z0.glb.qiniucdn.com/gmsliver155cbc9a39c911ee63efda10378130330.jpg","book_copy":"文案（订购商品）文案（订购商品）文案（订购商品）文案（订购商品）"},"if_ordain":"1"}
     */

    private String result;
    private String msg;
    /**
     * order_id : 1460707577dMB
     * address : {"addr_id":"648","username":"王璐","cellphone":"13946029872","addr_info":"黑龙江省哈尔滨市松北区糖厂街1号","zip_code":"150027","city":"哈尔滨"}
     * goods : {"goods_name":"GENTLE MONSTER","goods_num":"","des":"BIG BULLY 飛行員太陽鏡","price":"1300","pic":"http://7xprhi.com2.z0.glb.qiniucdn.com/gmsliver155cbc9a39c911ee63efda10378130330.jpg","book_copy":"文案（订购商品）文案（订购商品）文案（订购商品）文案（订购商品）"}
     * if_ordain : 1
     */

    private DataEntity data;

    public void setResult(String result) {
        this.result = result;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String order_id;
        /**
         * addr_id : 648
         * username : 王璐
         * cellphone : 13946029872
         * addr_info : 黑龙江省哈尔滨市松北区糖厂街1号
         * zip_code : 150027
         * city : 哈尔滨
         */

        private AddressEntity address;
        /**
         * goods_name : GENTLE MONSTER
         * goods_num :
         * des : BIG BULLY 飛行員太陽鏡
         * price : 1300
         * pic : http://7xprhi.com2.z0.glb.qiniucdn.com/gmsliver155cbc9a39c911ee63efda10378130330.jpg
         * book_copy : 文案（订购商品）文案（订购商品）文案（订购商品）文案（订购商品）
         */

        private GoodsEntity goods;
        private String if_ordain;

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public void setAddress(AddressEntity address) {
            this.address = address;
        }

        public void setGoods(GoodsEntity goods) {
            this.goods = goods;
        }

        public void setIf_ordain(String if_ordain) {
            this.if_ordain = if_ordain;
        }

        public String getOrder_id() {
            return order_id;
        }

        public AddressEntity getAddress() {
            return address;
        }

        public GoodsEntity getGoods() {
            return goods;
        }

        public String getIf_ordain() {
            return if_ordain;
        }

        public static class AddressEntity {
            private String addr_id;
            private String username;
            private String cellphone;
            private String addr_info;
            private String zip_code;
            private String city;

            public void setAddr_id(String addr_id) {
                this.addr_id = addr_id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setCellphone(String cellphone) {
                this.cellphone = cellphone;
            }

            public void setAddr_info(String addr_info) {
                this.addr_info = addr_info;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddr_id() {
                return addr_id;
            }

            public String getUsername() {
                return username;
            }

            public String getCellphone() {
                return cellphone;
            }

            public String getAddr_info() {
                return addr_info;
            }

            public String getZip_code() {
                return zip_code;
            }

            public String getCity() {
                return city;
            }
        }

        public static class GoodsEntity {
            private String goods_name;
            private String goods_num;
            private String des;
            private String price;
            private String pic;
            private String book_copy;

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setBook_copy(String book_copy) {
                this.book_copy = book_copy;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public String getDes() {
                return des;
            }

            public String getPrice() {
                return price;
            }

            public String getPic() {
                return pic;
            }

            public String getBook_copy() {
                return book_copy;
            }
        }
    }
    /**
     * {"result":"1","msg":"","data":{"order_id":"1460707577dMB","address":{"addr_id":"648","username":"王璐","cellphone":"13946029872","addr_info":"黑龙江省哈尔滨市松北区糖厂街1号","zip_code":"150027","city":"哈尔滨"},"goods":{"goods_name":"GENTLE MONSTER","goods_num":"","des":"BIG BULLY 飛行員太陽鏡","price":"1300","pic":"http://7xprhi.com2.z0.glb.qiniucdn.com/gmsliver155cbc9a39c911ee63efda10378130330.jpg","book_copy":"文案（订购商品）文案（订购商品）文案（订购商品）文案（订购商品）"},"if_ordain":"1"}}
     */
}
