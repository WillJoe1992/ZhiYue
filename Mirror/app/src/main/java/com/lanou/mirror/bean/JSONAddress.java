package com.lanou.mirror.bean;

import java.util.List;

/**
 * Created by dllo on 16/4/14.
 */
public class JSONAddress {

    /**
     * result : 1
     * msg :
     * data : {"pagination":{"first_time":"1460549109","last_time":"","has_more":"2"},"list":[{"addr_id":"648","zip_code":"150027","username":"王璐","cellphone":"13946029872","addr_info":"黑龙江省哈尔滨市松北区糖厂街1号","if_moren":"1","city":"哈尔滨"}]}
     */

    private String result;
    private String msg;
    /**
     * pagination : {"first_time":"1460549109","last_time":"","has_more":"2"}
     * list : [{"addr_id":"648","zip_code":"150027","username":"王璐","cellphone":"13946029872","addr_info":"黑龙江省哈尔滨市松北区糖厂街1号","if_moren":"1","city":"哈尔滨"}]
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
         * first_time : 1460549109
         * last_time :
         * has_more : 2
         */

        private PaginationEntity pagination;
        /**
         * addr_id : 648
         * zip_code : 150027
         * username : 王璐
         * cellphone : 13946029872
         * addr_info : 黑龙江省哈尔滨市松北区糖厂街1号
         * if_moren : 1
         * city : 哈尔滨
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
            private String addr_id;
            private String zip_code;
            private String username;
            private String cellphone;
            private String addr_info;
            private String if_moren;
            private String city;

            public void setAddr_id(String addr_id) {
                this.addr_id = addr_id;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
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

            public void setIf_moren(String if_moren) {
                this.if_moren = if_moren;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddr_id() {
                return addr_id;
            }

            public String getZip_code() {
                return zip_code;
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

            public String getIf_moren() {
                return if_moren;
            }

            public String getCity() {
                return city;
            }
        }
    }
}
