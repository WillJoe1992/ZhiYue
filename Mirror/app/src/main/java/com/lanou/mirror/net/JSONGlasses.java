package com.lanou.mirror.net;

import java.util.List;

/**
 * Created by dllo on 16/3/31.
 */
public class JSONGlasses {

    /**
     * result : 1
     * msg :
     * data : {"pagination":{"first_time":"1454389988","last_time":"1454389988","has_more":"2"},"list":[{"goods_id":"bUmq31454389988","goods_pic":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest10074f0fafdd463fdebfabf92da0c6f06.jpg","goods_img":"http://7xprhi.com2.z0.glb.qiniucdn.com/weibiaoti161d0d17dd4481d3a09e4ab1c00f0489ff.jpg","goods_name":"KAREN WALKER","model":"KW-S-01|銀色箭頭大框太陽鏡","last_storge":"0","whole_storge":"0","height":"","ordain":"1","product_area":"新西蘭","goods_price":"1538","discount_price":"","brand":"銀色箭頭大框太陽鏡","info_des":"Karen Walker是一个来自新西兰的太阳镜品牌，是新西兰设计师Karen Walker的同名品牌。设计师以另类青少年作为设计灵感，成功打破时髦与街头、奢华与质朴、沉稳与超级可爱之间的界限，她设计的太阳镜更是完美地诠释了\u201c不要用力过猛\u201d的时尚法则。\n搭配建议：复古牛仔衫、白色短裙、卡其色短靴","goods_data":[{"introContent":"大框太阳镜一直是经典百搭造型款，既实用又不减时尚感，十分符合设计师本人的设计理念。这款太阳镜，黑色的大框遮挡肉肉脸，银色箭头给中规中矩的镜框增加亮点，是范冰冰、李小璐、张柏芝同款。","cellHeight":"590","name":"","location":"來自時尚之都","country":"新西蘭","english":"The frame comes from New Zealand"},{"introContent":"从侧面看，这款眼镜的镜架设计轻巧，尤其是镜腿，由金属与板材拼接而成，银色金属光泽凸显街头叛逆感，黑色板材回转质朴气息。","cellHeight":"590","name":"設計輕巧","location":"","country":"","english":""},{"introContent":"Karen Walker成立二十周年来，几乎无人不知无人不晓，明星们都十分钟爱Karen Walker的眼镜，从街拍弄潮儿蕾哈娜，格温·史蒂芬妮，到公主安妮·海瑟薇，凯特·博斯沃斯等几乎都是该品牌的忠实粉丝。\n","cellHeight":"590","name":"大牌明星同款","location":"","country":"","english":""}],"design_des":[{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest13fc8a8ba2a799df45e406dbf8a2e3197.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest295c3cd1783f6680fb1d3784795ae834f.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest3b40943c915f52543acc13486e2f269f0.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/60151424a75051e0c10ff1b8e319daa5d6777a9.jpg","cellHeight":"550","type":"2"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/shouhouxuzhi10712650154fb2b154d2699ccf9273faf.jpg","cellHeight":"828","type":"3"}],"goods_share":"http://api101.test.mirroreye.cn/index.php/goodweb/info?id=bUmq31454389988"}]}
     */

    private String result;
    private String msg;
    /**
     * pagination : {"first_time":"1454389988","last_time":"1454389988","has_more":"2"}
     * list : [{"goods_id":"bUmq31454389988","goods_pic":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest10074f0fafdd463fdebfabf92da0c6f06.jpg","goods_img":"http://7xprhi.com2.z0.glb.qiniucdn.com/weibiaoti161d0d17dd4481d3a09e4ab1c00f0489ff.jpg","goods_name":"KAREN WALKER","model":"KW-S-01|銀色箭頭大框太陽鏡","last_storge":"0","whole_storge":"0","height":"","ordain":"1","product_area":"新西蘭","goods_price":"1538","discount_price":"","brand":"銀色箭頭大框太陽鏡","info_des":"Karen Walker是一个来自新西兰的太阳镜品牌，是新西兰设计师Karen Walker的同名品牌。设计师以另类青少年作为设计灵感，成功打破时髦与街头、奢华与质朴、沉稳与超级可爱之间的界限，她设计的太阳镜更是完美地诠释了\u201c不要用力过猛\u201d的时尚法则。\n搭配建议：复古牛仔衫、白色短裙、卡其色短靴","goods_data":[{"introContent":"大框太阳镜一直是经典百搭造型款，既实用又不减时尚感，十分符合设计师本人的设计理念。这款太阳镜，黑色的大框遮挡肉肉脸，银色箭头给中规中矩的镜框增加亮点，是范冰冰、李小璐、张柏芝同款。","cellHeight":"590","name":"","location":"來自時尚之都","country":"新西蘭","english":"The frame comes from New Zealand"},{"introContent":"从侧面看，这款眼镜的镜架设计轻巧，尤其是镜腿，由金属与板材拼接而成，银色金属光泽凸显街头叛逆感，黑色板材回转质朴气息。","cellHeight":"590","name":"設計輕巧","location":"","country":"","english":""},{"introContent":"Karen Walker成立二十周年来，几乎无人不知无人不晓，明星们都十分钟爱Karen Walker的眼镜，从街拍弄潮儿蕾哈娜，格温·史蒂芬妮，到公主安妮·海瑟薇，凯特·博斯沃斯等几乎都是该品牌的忠实粉丝。\n","cellHeight":"590","name":"大牌明星同款","location":"","country":"","english":""}],"design_des":[{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest13fc8a8ba2a799df45e406dbf8a2e3197.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest295c3cd1783f6680fb1d3784795ae834f.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest3b40943c915f52543acc13486e2f269f0.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/60151424a75051e0c10ff1b8e319daa5d6777a9.jpg","cellHeight":"550","type":"2"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/shouhouxuzhi10712650154fb2b154d2699ccf9273faf.jpg","cellHeight":"828","type":"3"}],"goods_share":"http://api101.test.mirroreye.cn/index.php/goodweb/info?id=bUmq31454389988"}]
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
        /**
         * first_time : 1454389988
         * last_time : 1454389988
         * has_more : 2
         */

        private PaginationEntity pagination;
        /**
         * goods_id : bUmq31454389988
         * goods_pic : http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest10074f0fafdd463fdebfabf92da0c6f06.jpg
         * goods_img : http://7xprhi.com2.z0.glb.qiniucdn.com/weibiaoti161d0d17dd4481d3a09e4ab1c00f0489ff.jpg
         * goods_name : KAREN WALKER
         * model : KW-S-01|銀色箭頭大框太陽鏡
         * last_storge : 0
         * whole_storge : 0
         * height :
         * ordain : 1
         * product_area : 新西蘭
         * goods_price : 1538
         * discount_price :
         * brand : 銀色箭頭大框太陽鏡
         * info_des : Karen Walker是一个来自新西兰的太阳镜品牌，是新西兰设计师Karen Walker的同名品牌。设计师以另类青少年作为设计灵感，成功打破时髦与街头、奢华与质朴、沉稳与超级可爱之间的界限，她设计的太阳镜更是完美地诠释了“不要用力过猛”的时尚法则。
         搭配建议：复古牛仔衫、白色短裙、卡其色短靴
         * goods_data : [{"introContent":"大框太阳镜一直是经典百搭造型款，既实用又不减时尚感，十分符合设计师本人的设计理念。这款太阳镜，黑色的大框遮挡肉肉脸，银色箭头给中规中矩的镜框增加亮点，是范冰冰、李小璐、张柏芝同款。","cellHeight":"590","name":"","location":"來自時尚之都","country":"新西蘭","english":"The frame comes from New Zealand"},{"introContent":"从侧面看，这款眼镜的镜架设计轻巧，尤其是镜腿，由金属与板材拼接而成，银色金属光泽凸显街头叛逆感，黑色板材回转质朴气息。","cellHeight":"590","name":"設計輕巧","location":"","country":"","english":""},{"introContent":"Karen Walker成立二十周年来，几乎无人不知无人不晓，明星们都十分钟爱Karen Walker的眼镜，从街拍弄潮儿蕾哈娜，格温·史蒂芬妮，到公主安妮·海瑟薇，凯特·博斯沃斯等几乎都是该品牌的忠实粉丝。\n","cellHeight":"590","name":"大牌明星同款","location":"","country":"","english":""}]
         * design_des : [{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest13fc8a8ba2a799df45e406dbf8a2e3197.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest295c3cd1783f6680fb1d3784795ae834f.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest3b40943c915f52543acc13486e2f269f0.jpg","cellHeight":"480","type":"1"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/60151424a75051e0c10ff1b8e319daa5d6777a9.jpg","cellHeight":"550","type":"2"},{"img":"http://7xprhi.com2.z0.glb.qiniucdn.com/shouhouxuzhi10712650154fb2b154d2699ccf9273faf.jpg","cellHeight":"828","type":"3"}]
         * goods_share : http://api101.test.mirroreye.cn/index.php/goodweb/info?id=bUmq31454389988
         */

        private List<ListEntity> list;

        public void setPagination(PaginationEntity pagination) {
            this.pagination = pagination;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public PaginationEntity getPagination() {
            return pagination;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class PaginationEntity {
            private String first_time;
            private String last_time;
            private String has_more;

            public void setFirst_time(String first_time) {
                this.first_time = first_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public void setHas_more(String has_more) {
                this.has_more = has_more;
            }

            public String getFirst_time() {
                return first_time;
            }

            public String getLast_time() {
                return last_time;
            }

            public String getHas_more() {
                return has_more;
            }
        }

        public static class ListEntity {
            private String goods_id;
            private String goods_pic;
            private String goods_img;
            private String goods_name;
            private String model;
            private String last_storge;
            private String whole_storge;
            private String height;
            private String ordain;
            private String product_area;
            private String goods_price;
            private String discount_price;
            private String brand;
            private String info_des;
            private String goods_share;
            /**
             * introContent : 大框太阳镜一直是经典百搭造型款，既实用又不减时尚感，十分符合设计师本人的设计理念。这款太阳镜，黑色的大框遮挡肉肉脸，银色箭头给中规中矩的镜框增加亮点，是范冰冰、李小璐、张柏芝同款。
             * cellHeight : 590
             * name :
             * location : 來自時尚之都
             * country : 新西蘭
             * english : The frame comes from New Zealand
             */

            private List<GoodsDataEntity> goods_data;
            /**
             * img : http://7xprhi.com2.z0.glb.qiniucdn.com/TheHarvest13fc8a8ba2a799df45e406dbf8a2e3197.jpg
             * cellHeight : 480
             * type : 1
             */

            private List<DesignDesEntity> design_des;

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public void setLast_storge(String last_storge) {
                this.last_storge = last_storge;
            }

            public void setWhole_storge(String whole_storge) {
                this.whole_storge = whole_storge;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public void setOrdain(String ordain) {
                this.ordain = ordain;
            }

            public void setProduct_area(String product_area) {
                this.product_area = product_area;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public void setInfo_des(String info_des) {
                this.info_des = info_des;
            }

            public void setGoods_share(String goods_share) {
                this.goods_share = goods_share;
            }

            public void setGoods_data(List<GoodsDataEntity> goods_data) {
                this.goods_data = goods_data;
            }

            public void setDesign_des(List<DesignDesEntity> design_des) {
                this.design_des = design_des;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getModel() {
                return model;
            }

            public String getLast_storge() {
                return last_storge;
            }

            public String getWhole_storge() {
                return whole_storge;
            }

            public String getHeight() {
                return height;
            }

            public String getOrdain() {
                return ordain;
            }

            public String getProduct_area() {
                return product_area;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public String getBrand() {
                return brand;
            }

            public String getInfo_des() {
                return info_des;
            }

            public String getGoods_share() {
                return goods_share;
            }

            public List<GoodsDataEntity> getGoods_data() {
                return goods_data;
            }

            public List<DesignDesEntity> getDesign_des() {
                return design_des;
            }

            public static class GoodsDataEntity {
                private String introContent;
                private String cellHeight;
                private String name;
                private String location;
                private String country;
                private String english;

                public void setIntroContent(String introContent) {
                    this.introContent = introContent;
                }

                public void setCellHeight(String cellHeight) {
                    this.cellHeight = cellHeight;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public void setEnglish(String english) {
                    this.english = english;
                }

                public String getIntroContent() {
                    return introContent;
                }

                public String getCellHeight() {
                    return cellHeight;
                }

                public String getName() {
                    return name;
                }

                public String getLocation() {
                    return location;
                }

                public String getCountry() {
                    return country;
                }

                public String getEnglish() {
                    return english;
                }
            }

            public static class DesignDesEntity {
                private String img;
                private String cellHeight;
                private String type;

                public void setImg(String img) {
                    this.img = img;
                }

                public void setCellHeight(String cellHeight) {
                    this.cellHeight = cellHeight;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImg() {
                    return img;
                }

                public String getCellHeight() {
                    return cellHeight;
                }

                public String getType() {
                    return type;
                }
            }
        }
    }
}
