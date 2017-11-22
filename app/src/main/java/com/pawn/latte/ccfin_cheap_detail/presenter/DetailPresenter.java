package com.pawn.latte.ccfin_cheap_detail.presenter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;
import com.pawn.latte.ccfin_cheap_detail.contract.DetailContract;

import org.goldian.ccfin_core.net.RestClient;
import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.ISuccess;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by Pawn on 2017/11/20 15.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void loadDataTask() {
        view.showProgress();
        requestMcData();
    }

    @Override
    public void loadPayListTask() {

    }

    @Override
    public void collectionTask() {

    }

    @Override
    public void uncollectionTask() {

    }

    private void requestMcData() {

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "aadf1479-a8a0-474d-ad7e-b9aff3f4c23d");
        params.put("uid", "-1");
        params.put("mc_id", "28361");
        params.put("isNear", 1);
        params.put("point_x", 121.5099f);
        params.put("point_y", 31.2289f);
        params.put("category_id", "-1");
        params.put("band_id", "-1");
        RestClient.builder()
                .url("ccfin-business-service-1.0/api/v1/business/getBandAndDiscountDetailV2")
                .params(params)
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            view.hideProgress();

                            Gson gson = new Gson();
                            CheapDetailResp detailResp = gson.fromJson(response, CheapDetailResp.class);
                            String code = detailResp.getCode();
                            if ("200".equals(code)) {
                                String logoImageUrl = detailResp.getBand_logo_image_url();


                                // 不同银行所对应的优惠
                                List<DiscountListBean> discountList = detailResp.getDiscountList();
                                if (discountList != null) {
//                                    cheapDetailList.clear();
//                                    cheapDetailList.add(new DiscountListBean());
//                                    cheapDetailList.addAll(discountList);
//                                    if (detailAdapter != null) {
//                                        detailAdapter.notifyDataSetChanged();
//                                    }
                                }


                                // 该品牌所对应的最近的几个门店(最多三条)---其中第一条显示到top，剩下的显示到bottom
                                List<McDisRespBean> mcDisRespList = detailResp.getMcDisRespList();
                                if (mcDisRespList != null) {
                                    if (mcDisRespList.size() >= 1) {
                                        // 拿到第一条数据显示到top
                                        McDisRespBean mcBean = mcDisRespList.remove(0);
                                        // 设置当前门店名
//                                        topViewHolder.tvMcName.setText(mcBean.getMc_name());
//                                        topViewHolder.tvMcAddress.setText(mcBean.getAddress());
//
//
//                                        if (mcDisRespList.size() > 0) {
//                                            // 用一个集合来存储剩下的门店
//                                            bottomMcList.clear();
//                                            bottomMcList.addAll(mcDisRespList);
//                                            if (bottomMcAdapter != null) {
//                                                bottomMcAdapter.notifyDataSetChanged();
//                                            }
//                                        }

                                    }
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .onFailure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {

                    }
                })
                .onError(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
