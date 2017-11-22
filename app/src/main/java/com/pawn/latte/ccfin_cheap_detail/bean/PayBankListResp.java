package com.pawn.latte.ccfin_cheap_detail.bean;

import java.util.List;

/**
 * Created by Pawn on 2017/11/21 14.
 */

public class PayBankListResp {

    /**
     * baseObject : {}
     * code : string
     * message : string
     * postID : string
     * uid : string
     * userPayBanksOrCards : [{"uid":0,"bankId":0,"bankName":"string","bankIcon":"string","cardNo":"string","cardType":0,"disCount":0,"mc_id":"string","discounts":[{"mc_id":"string","bank_id":0,"dis_id":0,"dis_title":"string","dis_type":0,"dis_info":"string","dis_keywords":"string","dis_point":"string","dis_day":"string","full_cut":"string","top_dis_money":0}]}]
     * isContainsAllBanks : 0
     * redPacketResponseList : [{"money":0,"used_money":0,"type":0,"create_date":"2017-11-21T06:30:53.532Z","expire_date":"2017-11-21T06:30:53.532Z","status":0}]
     * redPacket_count : 0
     * redPacket_money : 0
     * allBankdiscounts : ["MerchantDiscount"]
     */

    private BaseObjectBean baseObject;
    private String code;
    private String message;
    private String postID;
    private String uid;
    private int isContainsAllBanks;
    private int redPacket_count;
    private int redPacket_money;
    private List<UserPayBanksOrCardsBean> userPayBanksOrCards;
    private List<RedPacketResponseListBean> redPacketResponseList;
    private List<String> allBankdiscounts;

    public BaseObjectBean getBaseObject() {
        return baseObject;
    }

    public void setBaseObject(BaseObjectBean baseObject) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getIsContainsAllBanks() {
        return isContainsAllBanks;
    }

    public void setIsContainsAllBanks(int isContainsAllBanks) {
        this.isContainsAllBanks = isContainsAllBanks;
    }

    public int getRedPacket_count() {
        return redPacket_count;
    }

    public void setRedPacket_count(int redPacket_count) {
        this.redPacket_count = redPacket_count;
    }

    public int getRedPacket_money() {
        return redPacket_money;
    }

    public void setRedPacket_money(int redPacket_money) {
        this.redPacket_money = redPacket_money;
    }

    public List<UserPayBanksOrCardsBean> getUserPayBanksOrCards() {
        return userPayBanksOrCards;
    }

    public void setUserPayBanksOrCards(List<UserPayBanksOrCardsBean> userPayBanksOrCards) {
        this.userPayBanksOrCards = userPayBanksOrCards;
    }

    public List<RedPacketResponseListBean> getRedPacketList() {
        return redPacketResponseList;
    }

    public void setRedPacketResponseList(List<RedPacketResponseListBean> redPacketResponseList) {
        this.redPacketResponseList = redPacketResponseList;
    }

    public List<String> getAllBankdiscounts() {
        return allBankdiscounts;
    }

    public void setAllBankdiscounts(List<String> allBankdiscounts) {
        this.allBankdiscounts = allBankdiscounts;
    }

    public static class BaseObjectBean {
    }

    public static class UserPayBanksOrCardsBean {
        /**
         * uid : 0
         * bankId : 0
         * bankName : string
         * bankIcon : string
         * cardNo : string
         * cardType : 0
         * disCount : 0
         * mc_id : string
         * discounts : [{"mc_id":"string","bank_id":0,"dis_id":0,"dis_title":"string","dis_type":0,"dis_info":"string","dis_keywords":"string","dis_point":"string","dis_day":"string","full_cut":"string","top_dis_money":0}]
         */

        private int uid;
        private int bankId;
        private String bankName;
        private String bankIcon;
        private String cardNo;
        private int cardType;
        private int disCount;
        private String mc_id;
        private List<DiscountsBean> discounts;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankIcon() {
            return bankIcon;
        }

        public void setBankIcon(String bankIcon) {
            this.bankIcon = bankIcon;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public int getDisCount() {
            return disCount;
        }

        public void setDisCount(int disCount) {
            this.disCount = disCount;
        }

        public String getMc_id() {
            return mc_id;
        }

        public void setMc_id(String mc_id) {
            this.mc_id = mc_id;
        }

        public List<DiscountsBean> getDiscounts() {
            return discounts;
        }

        public void setDiscounts(List<DiscountsBean> discounts) {
            this.discounts = discounts;
        }

        public static class DiscountsBean {
            /**
             * mc_id : string
             * bank_id : 0
             * dis_id : 0
             * dis_title : string
             * dis_type : 0
             * dis_info : string
             * dis_keywords : string
             * dis_point : string
             * dis_day : string
             * full_cut : string
             * top_dis_money : 0
             */

            private String mc_id;
            private int bank_id;
            private int dis_id;
            private String dis_title;
            private int dis_type;
            private String dis_info;
            private String dis_keywords;
            private String dis_point;
            private String dis_day;
            private String full_cut;
            private int top_dis_money;

            public String getMc_id() {
                return mc_id;
            }

            public void setMc_id(String mc_id) {
                this.mc_id = mc_id;
            }

            public int getBank_id() {
                return bank_id;
            }

            public void setBank_id(int bank_id) {
                this.bank_id = bank_id;
            }

            public int getDis_id() {
                return dis_id;
            }

            public void setDis_id(int dis_id) {
                this.dis_id = dis_id;
            }

            public String getDis_title() {
                return dis_title;
            }

            public void setDis_title(String dis_title) {
                this.dis_title = dis_title;
            }

            public int getDis_type() {
                return dis_type;
            }

            public void setDis_type(int dis_type) {
                this.dis_type = dis_type;
            }

            public String getDis_info() {
                return dis_info;
            }

            public void setDis_info(String dis_info) {
                this.dis_info = dis_info;
            }

            public String getDis_keywords() {
                return dis_keywords;
            }

            public void setDis_keywords(String dis_keywords) {
                this.dis_keywords = dis_keywords;
            }

            public String getDis_point() {
                return dis_point;
            }

            public void setDis_point(String dis_point) {
                this.dis_point = dis_point;
            }

            public String getDis_day() {
                return dis_day;
            }

            public void setDis_day(String dis_day) {
                this.dis_day = dis_day;
            }

            public String getFull_cut() {
                return full_cut;
            }

            public void setFull_cut(String full_cut) {
                this.full_cut = full_cut;
            }

            public int getTop_dis_money() {
                return top_dis_money;
            }

            public void setTop_dis_money(int top_dis_money) {
                this.top_dis_money = top_dis_money;
            }
        }
    }

    public static class RedPacketResponseListBean {
        /**
         * money : 0
         * used_money : 0
         * type : 0
         * create_date : 2017-11-21T06:30:53.532Z
         * expire_date : 2017-11-21T06:30:53.532Z
         * status : 0
         */

        private int money;
        private int used_money;
        private int type;
        private String create_date;
        private String expire_date;
        private int status;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getUsed_money() {
            return used_money;
        }

        public void setUsed_money(int used_money) {
            this.used_money = used_money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getExpire_date() {
            return expire_date;
        }

        public void setExpire_date(String expire_date) {
            this.expire_date = expire_date;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
