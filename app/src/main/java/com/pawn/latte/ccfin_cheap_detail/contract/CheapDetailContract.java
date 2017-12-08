package com.pawn.latte.ccfin_cheap_detail.contract;

import android.content.Context;

import com.pawn.latte.IBasePresenter;
import com.pawn.latte.IBaseView;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp.RedPacketResponseListBean;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by Pawn on 2017/11/20 11.
 */

public interface CheapDetailContract {

    interface View extends IBaseView {

        void onPageStart();

        void showSharePreferencesValues();

        void onNewIntent();

        void showToast(String msg);

        void showNoNetworkTask();

        void showRequestErrorTask();

        void onDetailRequestSuccess(CheapDetailResp cheapDetailResp);

        void onDetailRequestFail();

        void onPayListRequestSuccess(List<RedPacketResponseListBean> payList);

        void onPayListRequestFail();

        void onCollection();

        void onUnCollection();

        void onShare();

        void onCheapWrongInfo();

        void onPay();

        void onPayItem();

        void onPriceCompare();

        void onCurrentMcCall();

        void onCurrentMcMapGuide();

        void onAllMcMap();

    }

    interface Presenter extends IBasePresenter {

        void checkNetwork();

        void getSharePreferencesValues(Context context);

        void loadDataTask(WeakHashMap<String, Object> params);

        void loadPayListTask(WeakHashMap<String, Object> params);

        void collectionTask(WeakHashMap<String, Object> params);

        void shareTask(String type, WeakHashMap<String, Object> params);

        void priceCompareTask();

    }
}

