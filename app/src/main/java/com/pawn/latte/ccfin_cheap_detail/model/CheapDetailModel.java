package com.pawn.latte.ccfin_cheap_detail.model;

import com.pawn.latte.IBaseModel;

/**
 * Created by Pawn on 2017/12/6 17.
 */

public interface CheapDetailModel extends IBaseModel {

    void requestDetailData();

    void requestPayListData();

    void shareToQQ();

    void shareToWeChat();

    void shareToWeChatCircle();

    void priceCompare();

    void collectTask();

}
