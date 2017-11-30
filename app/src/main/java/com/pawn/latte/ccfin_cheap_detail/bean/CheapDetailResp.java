package com.pawn.latte.ccfin_cheap_detail.bean;

import java.util.List;

/**
 * Created by Pawn on 2017/11/7 17.
 */

public class CheapDetailResp {

    /**
     * baseObject : null
     * code : 200
     * message : 成功
     * postID : null
     * mapListUrl : http://10.1.1.21:8080/ccfin_mobile/discountDetailController/toMcListMap?point_x=121.5099&point_y=31.2289&mc_id=28361&ticket=1
     * isSpecial : 0
     * band_name : 江边城外烤全鱼
     * band_logo_image_url : https://qnpic.billbear.cn/Fu7r-g_MCC2aJgdkkGhtUsA9mFH0
     * praise_point : 9
     * avg_consumption : 200
     * cat_name : 美食
     * discountList : [{"apply_card_url":"http://www.boc.cn/","bank_id":"27","bank_logo_url":"http://resourcetest.99love.com/20170111/94e2f41b796a4ddb8a6c8cecc89a759d.png","bank_name":"中国银行","dis_keywords":"立减","dis_available_date":1483200000000,"expire_date":1546272000000,"dis_day":null,"dis_content":"<p><strong>活动内容<\/strong><\/p><p>仅限银联卡使用<\/p><p>限银联卡<\/p><p>1. &nbsp;周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元；<\/p><p>2. &nbsp;限1次/卡/日，数量有限，先到先得。<\/p><p><br/><\/p>","cat_name":"美食","channel":null,"cat_id":null,"is_special":0,"id":47937,"dis_type":0,"discount_title_content":[{"title_id":101,"title_name":"活动内容 仅限银联卡使用 限银联卡","title_content":[{"contentId":170,"titleId":101,"contentType":0,"contentName":"周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元","contentOrder":null,"updateUser":null},{"contentId":171,"titleId":101,"contentType":0,"contentName":"限1次/卡/日，数量有限，先到先得","contentOrder":null,"updateUser":null}]}],"is_effective":0}]
     * merchantDiscountResponseV1List : [{"overSeas":0,"mapUrl":"http://10.1.1.21:8080/ccfin_mobile/discountDetailController/toMap?point_x=121.51674&point_y=31.229412&now_point_x=121.5099&now_point_y=31.2289","district":"浦东新区","address":"浦东南路1085号华申大厦3楼","phone":"021-58368818","distance":"762.2663805304308","point_x":121.51674,"point_y":31.229412,"mc_name":"江边城外烤全鱼(浦东新梅店)","is_preference":0,"mc_id":"28356"},{"overSeas":0,"mapUrl":"http://10.1.1.21:8080/ccfin_mobile/discountDetailController/toMap?point_x=121.474236&point_y=31.235903&now_point_x=121.5099&now_point_y=31.2289","district":"黄浦区","address":"南京东路830号第一百货B1层","phone":"021-63617001","distance":"4041.388210290427","point_x":121.474236,"point_y":31.235903,"mc_name":"江边城外烤全鱼(第一百货南京东...","is_preference":0,"mc_id":"28353"},{"overSeas":0,"mapUrl":"http://10.1.1.21:8080/ccfin_mobile/discountDetailController/toMap?point_x=121.55745&point_y=31.272003&now_point_x=121.5099&now_point_y=31.2289","district":"杨浦区","address":"杨树浦路2893号","phone":"4006665353","distance":"7136.155956720063","point_x":121.55745,"point_y":31.272003,"mc_name":"江边城外烤全鱼(上海国际时尚中...","is_preference":0,"mc_id":"28361"}]
     * total_merchant_count : 13
     * province : 上海市
     * city : 上海市
     * district : 浦东新区
     * dis_typeArray : ["0"]
     * is_preference : 0
     * isCollect : 0
     */

    private String baseObject;
    private String code;
    private String message;
    private String postID;

    private String mapListUrl;
    private int isSpecial;
    private String band_name;
    private String band_logo_image_url;
    private int praise_point;
    private int avg_consumption;
    private String cat_name;
    private int total_merchant_count;
    private String province;
    private String city;
    private String district;
    private int is_preference;
    private int isCollect;
    private List<DiscountListBean> discountList;
    private List<McDisRespBean> merchantDiscountResponseV1List;
    private List<String> dis_typeArray;

    public String getBaseObject() {
        return baseObject;
    }

    public void setBaseObject(String baseObject) {
        this.baseObject = baseObject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getMapListUrl() {
        return mapListUrl;
    }

    public void setMapListUrl(String mapListUrl) {
        this.mapListUrl = mapListUrl;
    }

    public int getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(int isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getBand_logo_image_url() {
        return band_logo_image_url;
    }

    public void setBand_logo_image_url(String band_logo_image_url) {
        this.band_logo_image_url = band_logo_image_url;
    }

    public int getPraise_point() {
        return praise_point;
    }

    public void setPraise_point(int praise_point) {
        this.praise_point = praise_point;
    }

    public int getAvg_consumption() {
        return avg_consumption;
    }

    public void setAvg_consumption(int avg_consumption) {
        this.avg_consumption = avg_consumption;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getTotal_merchant_count() {
        return total_merchant_count;
    }

    public void setTotal_merchant_count(int total_merchant_count) {
        this.total_merchant_count = total_merchant_count;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getIs_preference() {
        return is_preference;
    }

    public void setIs_preference(int is_preference) {
        this.is_preference = is_preference;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public List<DiscountListBean> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountListBean> discountList) {
        this.discountList = discountList;
    }

    public List<McDisRespBean> getMcDisRespList() {
        return merchantDiscountResponseV1List;
    }

    public void setMcDisRespList(List<McDisRespBean> merchantDiscountResponseV1List) {
        this.merchantDiscountResponseV1List = merchantDiscountResponseV1List;
    }

    public List<String> getDis_typeArray() {
        return dis_typeArray;
    }

    public void setDis_typeArray(List<String> dis_typeArray) {
        this.dis_typeArray = dis_typeArray;
    }

    public static class DiscountListBean {
        /**
         * apply_card_url : http://www.boc.cn/
         * bank_id : 27
         * bank_logo_url : http://resourcetest.99love.com/20170111/94e2f41b796a4ddb8a6c8cecc89a759d.png
         * bank_name : 中国银行
         * dis_keywords : 立减
         * dis_available_date : 1483200000000
         * expire_date : 1546272000000
         * dis_day : null
         * dis_content : <p><strong>活动内容</strong></p><p>仅限银联卡使用</p><p>限银联卡</p><p>1. &nbsp;周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元；</p><p>2. &nbsp;限1次/卡/日，数量有限，先到先得。</p><p><br/></p>
         * cat_name : 美食
         * channel : null
         * cat_id : null
         * is_special : 0
         * id : 47937
         * dis_type : 0
         * discount_title_content : [{"title_id":101,"title_name":"活动内容 仅限银联卡使用 限银联卡","title_content":[{"contentId":170,"titleId":101,"contentType":0,"contentName":"周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元","contentOrder":null,"updateUser":null},{"contentId":171,"titleId":101,"contentType":0,"contentName":"限1次/卡/日，数量有限，先到先得","contentOrder":null,"updateUser":null}]}]
         * is_effective : 0
         */

        private String apply_card_url;
        private String bank_id;
        private String bank_logo_url;
        private String bank_name;
        private String dis_keywords;
        private long dis_available_date;
        private long expire_date;
        private String dis_day;
        private String dis_content;
        private String cat_name;
        private String channel;
        private String cat_id;
        private int is_special;
        private int id;
        private int dis_type;
        private int is_effective;
        private List<DiscountTitleContent> discount_title_content;

        private boolean isExpaned;

        public boolean isExpaned() {
            return isExpaned;
        }

        public void setExpaned(boolean expaned) {
            isExpaned = expaned;
        }

        public String getApply_card_url() {
            return apply_card_url;
        }

        public void setApply_card_url(String apply_card_url) {
            this.apply_card_url = apply_card_url;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_logo_url() {
            return bank_logo_url;
        }

        public void setBank_logo_url(String bank_logo_url) {
            this.bank_logo_url = bank_logo_url;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getDis_keywords() {
            return dis_keywords;
        }

        public void setDis_keywords(String dis_keywords) {
            this.dis_keywords = dis_keywords;
        }

        public long getDis_available_date() {
            return dis_available_date;
        }

        public void setDis_available_date(long dis_available_date) {
            this.dis_available_date = dis_available_date;
        }

        public long getExpire_date() {
            return expire_date;
        }

        public void setExpire_date(long expire_date) {
            this.expire_date = expire_date;
        }

        public String getDis_day() {
            return dis_day;
        }

        public void setDis_day(String dis_day) {
            this.dis_day = dis_day;
        }

        public String getDis_content() {
            return dis_content;
        }

        public void setDis_content(String dis_content) {
            this.dis_content = dis_content;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public int getIs_special() {
            return is_special;
        }

        public void setIs_special(int is_special) {
            this.is_special = is_special;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDis_type() {
            return dis_type;
        }

        public void setDis_type(int dis_type) {
            this.dis_type = dis_type;
        }

        public int getIs_effective() {
            return is_effective;
        }

        public void setIs_effective(int is_effective) {
            this.is_effective = is_effective;
        }

        public List<DiscountTitleContent> getDiscountTitleContent() {
            return discount_title_content;
        }

        public void setDiscount_title_content(List<DiscountTitleContent> discount_title_content) {
            this.discount_title_content = discount_title_content;
        }

        public static class DiscountTitleContent {
            /**
             * title_id : 101
             * title_name : 活动内容 仅限银联卡使用 限银联卡
             * title_content : [{"contentId":170,"titleId":101,"contentType":0,"contentName":"周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元","contentOrder":null,"updateUser":null},{"contentId":171,"titleId":101,"contentType":0,"contentName":"限1次/卡/日，数量有限，先到先得","contentOrder":null,"updateUser":null}]
             */

            private int title_id;
            private String title_name;
            private List<TitleContentBean> title_content;

            public int getTitle_id() {
                return title_id;
            }

            public void setTitle_id(int title_id) {
                this.title_id = title_id;
            }

            public String getTitle_name() {
                return title_name;
            }

            public void setTitle_name(String title_name) {
                this.title_name = title_name;
            }

            public List<TitleContentBean> getTitle_content() {
                return title_content;
            }

            public void setTitle_content(List<TitleContentBean> title_content) {
                this.title_content = title_content;
            }

            public static class TitleContentBean {
                /**
                 * contentId : 170
                 * titleId : 101
                 * contentType : 0
                 * contentName : 周五/周六/周日持卡至店刷卡消费享单笔满100元立减20元，最高优惠20元
                 * contentOrder : null
                 * updateUser : null
                 */

                private int contentId;
                private int titleId;
                private int contentType;
                private String contentName;
                private Object contentOrder;
                private Object updateUser;

                public int getContentId() {
                    return contentId;
                }

                public void setContentId(int contentId) {
                    this.contentId = contentId;
                }

                public int getTitleId() {
                    return titleId;
                }

                public void setTitleId(int titleId) {
                    this.titleId = titleId;
                }

                public int getContentType() {
                    return contentType;
                }

                public void setContentType(int contentType) {
                    this.contentType = contentType;
                }

                public String getContentName() {
                    return contentName;
                }

                public void setContentName(String contentName) {
                    this.contentName = contentName;
                }

                public Object getContentOrder() {
                    return contentOrder;
                }

                public void setContentOrder(Object contentOrder) {
                    this.contentOrder = contentOrder;
                }

                public Object getUpdateUser() {
                    return updateUser;
                }

                public void setUpdateUser(Object updateUser) {
                    this.updateUser = updateUser;
                }
            }
        }
    }

    public static class McDisRespBean {
        /**
         * overSeas : 0
         * mapUrl : http://10.1.1.21:8080/ccfin_mobile/discountDetailController/toMap?point_x=121.51674&point_y=31.229412&now_point_x=121.5099&now_point_y=31.2289
         * district : 浦东新区
         * address : 浦东南路1085号华申大厦3楼
         * phone : 021-58368818
         * distance : 762.2663805304308
         * point_x : 121.51674
         * point_y : 31.229412
         * mc_name : 江边城外烤全鱼(浦东新梅店)
         * is_preference : 0
         * mc_id : 28356
         */

        private int overSeas;
        private String mapUrl;
        private String district;
        private String address;
        private String phone;
        private String distance;
        private double point_x;
        private double point_y;
        private String mc_name;
        private int is_preference;
        private String mc_id;

        public int getOverSeas() {
            return overSeas;
        }

        public void setOverSeas(int overSeas) {
            this.overSeas = overSeas;
        }

        public String getMapUrl() {
            return mapUrl;
        }

        public void setMapUrl(String mapUrl) {
            this.mapUrl = mapUrl;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public double getPoint_x() {
            return point_x;
        }

        public void setPoint_x(double point_x) {
            this.point_x = point_x;
        }

        public double getPoint_y() {
            return point_y;
        }

        public void setPoint_y(double point_y) {
            this.point_y = point_y;
        }

        public String getMc_name() {
            return mc_name;
        }

        public void setMc_name(String mc_name) {
            this.mc_name = mc_name;
        }

        public int getIs_preference() {
            return is_preference;
        }

        public void setIs_preference(int is_preference) {
            this.is_preference = is_preference;
        }

        public String getMc_id() {
            return mc_id;
        }

        public void setMc_id(String mc_id) {
            this.mc_id = mc_id;
        }
    }
}
