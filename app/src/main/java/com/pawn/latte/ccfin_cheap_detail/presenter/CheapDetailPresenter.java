package com.pawn.latte.ccfin_cheap_detail.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pawn.latte.IBaseView;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp.RedPacketResponseListBean;
import com.pawn.latte.ccfin_cheap_detail.contract.CheapDetailContract;

import org.goldian.ccfin_core.net.RestClient;
import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.ISuccess;
import org.goldian.ccfin_core.net.rx.RxRestClient;

import java.util.List;
import java.util.WeakHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pawn on 2017/12/7 10.
 */

public class CheapDetailPresenter implements CheapDetailContract.Presenter {

    private CheapDetailContract.View mView = null;
//    private CheapDetailModel mDetailModel = null;

    public CheapDetailPresenter() {
//        mDetailModel = new CheapDetailModel();
    }

    @Override
    public void subscribe(IBaseView view) {
        this.mView = (CheapDetailContract.View) view;
        mView.onPageStart();
    }

    @Override
    public void unSubscribe() {
        mView = null;
    }

    @Override
    public void checkNetwork() {

    }

    @Override
    public void getSharePreferencesValues(Context context) {

    }

    @Override
    public void loadDataTask(WeakHashMap<String, Object> params) {

        RestClient.builder()
                .url("ccfin-business-service-1.0/api/v1/business/getBandAndDiscountDetailV2")
                .params(params)
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            Gson gson = new Gson();
                            CheapDetailResp detailResp = gson.fromJson(response, CheapDetailResp.class);
                            String code = detailResp.getCode();
                            if ("200".equals(code)) {
                                mView.onDetailRequestSuccess(detailResp);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .onFailure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {
                        mView.onDetailRequestFail();
                    }
                })
                .onError(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mView.showRequestErrorTask();
                    }
                })
                .build()
                .get();

    }

    @Override
    public void loadPayListTask(WeakHashMap<String, Object> params) {

        RxRestClient.builder()
                .url("ccfin-business-service-1.0/api/v1/business/getPayBankList")
                .header("token", "")
                .params(params)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Gson gson = new Gson();
                        try {
                            PayBankListResp payBankListResp = gson.fromJson(s, PayBankListResp.class);
                            String code = payBankListResp.getCode();
                            if ("200".equals(code)) {
                                List<RedPacketResponseListBean> redPacketList = payBankListResp.getRedPacketList();
                                mView.onPayListRequestSuccess(redPacketList);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

    @Override
    public void collectionTask(WeakHashMap<String, Object> params) {

    }

    @Override
    public void shareTask(String type, WeakHashMap<String, Object> params) {

    }

    @Override
    public void priceCompareTask() {

    }
}
