package com.pawn.latte.ccfin_cheap_detail;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pawn.latte.R;
import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp.RedPacketResponseListBean;
import com.pawn.latte.ccfin_cheap_detail.widget.CustomDialog;
import com.pawn.latte.ccfin_cheap_detail.widget.CustomPopupWindow;
import com.pawn.latte.ccfin_cheap_detail.widget.ExpandTextView;
import com.pawn.latte.ccfin_cheap_detail.widget.MyStarBar;
import com.pawn.latte.ccfin_cheap_detail.widget.ScrollListView;

import org.goldian.ccfin_core.net.RestClient;
import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.ISuccess;
import org.goldian.ccfin_core.net.rx.RxRestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CheapDetailActivity extends AppCompatActivity {

    private static final String TAG = CheapDetailActivity.class.getSimpleName();
    private Context context;

    private ImageView ivMcLogo, ivMcPhone;
    private TextView tvMcName, tvMcAddress;
    private TabLayout tabLayout;
    private ListView lvDetail;

    private List<DiscountListBean> cheapDetailList = new ArrayList<>();
    private List<McDisRespBean> bottomMcList = new ArrayList<>();
    private List<Object> priceCompareList = new ArrayList<>();

    private BaseLvAdapter<McDisRespBean> bottomMcAdapter;
    private BaseLvAdapter<DiscountListBean> detailAdapter;
    private View detailBottomView;
    private DetailBottomViewHolder bottomViewHolder;
    private BaseLvAdapter<Object> priceAdapter;
    private View detailTopView;
    private DetailTopViewHolder topViewHolder;

    private int scrollY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheap_detail);
        ButterKnife.bind(this);
        context = this;

        initView();

        requestMcData();
        requestPayListData();

        // TODO:测试RxRestService
        getRxServiceData();

        // TODO:测试RxJava
        testRxJava();
    }

    private void initView() {

        ivMcLogo = (ImageView) findViewById(R.id.iv_mc_logo);
        tvMcName = (TextView) findViewById(R.id.tv_mc_name);
        tvMcAddress = (TextView) findViewById(R.id.tv_mc_address);
        ivMcPhone = (ImageView) findViewById(R.id.iv_mc_phone);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        lvDetail = (ListView) findViewById(R.id.lv_detail);
        detailTopView = LayoutInflater.from(this).inflate(R.layout.cheap_detail_top, null);
        topViewHolder = new DetailTopViewHolder(detailTopView);
        detailBottomView = LayoutInflater.from(this).inflate(R.layout.cheap_detail_bottom, null);
        bottomViewHolder = new DetailBottomViewHolder(detailBottomView);

        detailAdapter = new BaseLvAdapter<DiscountListBean>(this, cheapDetailList, R.layout.item_cheap_detail) {
            @Override
            public void convert(BaseLvViewHolder viewHolder, DiscountListBean item, int position) {
                ExpandTextView detailContent = viewHolder.getView(R.id.detail_content);
//                detailContent.setText(item.getDis_content(), true);
                detailContent.setText("1111111111111111111111111111111111111111111111111111111111111" +
                        "11111111111111111111111111111111111111111111111111111111111111111111111111" +
                        "11111111111111111111111111111111111111111111111111111111111111111111111111" +
                        "11111111111111111111111111111111111111111111111111111111111111111111111111", true);

//                TextView tvDisTitle = viewHolder.getView(R.id.tv_dis_title);
//                StringBuilder disTitle =
//                        new StringBuilder(item.getBank_name())
//                                .append("：")
//                                .append(item.getDis_keywords());
//                tvDisTitle.setText(disTitle);

                detailContent.setListener(new ExpandTextView.OnExpandStateChangeListener() {
                    @Override
                    public void onExpandStateChanged(boolean isExpanded) {

                    }
                });
            }
        };

        for (int i = 0; i < 7; i++) {
            priceCompareList.add("");
        }

        priceAdapter = new BaseLvAdapter<Object>(this, priceCompareList, R.layout.item_cheap_price_compare) {

            @Override
            public void convert(BaseLvViewHolder viewHolder, Object item, int position) {
                TextView tvPreMoney = viewHolder.getView(R.id.tv_pre_money);
                tvPreMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            }
        };
        lvDetail.addHeaderView(detailTopView);
        lvDetail.addFooterView(detailBottomView);
        lvDetail.setAdapter(detailAdapter);


        bottomMcAdapter = new BaseLvAdapter<McDisRespBean>(this, bottomMcList, R.layout.item_cheap_mc) {
            @Override
            public void convert(BaseLvViewHolder viewHolder, McDisRespBean item, int position) {

            }
        };
        bottomViewHolder.lvOtherMc.setAdapter(bottomMcAdapter);
        bottomViewHolder.lvOtherMc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        final List<String> payList = new ArrayList<>();
        payList.add("");
        payList.add("");
        final View payTypeListView = View.inflate(this, R.layout.dialog_pay, null);
        final ListView lvPayType = payTypeListView.findViewById(R.id.lv_pay_type);
        final BaseLvAdapter<String> payListAdapter = new BaseLvAdapter<String>(this, payList, R.layout.item_pay_for_cheap) {

            @Override
            public void convert(BaseLvViewHolder viewHolder, String item, int position) {

            }
        };
        lvPayType.setAdapter(payListAdapter);
        final CustomDialog dialog = new CustomDialog.Build(context)
                .cancelTouchOut(false)
                .style(R.style.CustomDialog)
                .view(payTypeListView)
                .widthDp(270)
                .heightDp(300)
                .build();

        bottomViewHolder.rlCheapWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payListAdapter.notifyDataSetChanged();
                dialog.show();
                payList.add("");
            }
        });

        // TODO: 2017/11/17 BasePopupWindow测试
        final CustomPopupWindow popupWindow = new CustomPopupWindow.Build(context)
                .setHeightAndWidthDp(75, WindowManager.LayoutParams.WRAP_CONTENT)
                .setCanTouchOutside(false)
                .setFocusable(true)
                .setAnimStyle(R.style.AnimHorizontal)
                .setContentView(R.layout.item_cheap_price_compare)
                .build();
        bottomViewHolder.btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float alpha = bottomViewHolder.btPay.getAlpha();
                popupWindow.showAsLocation(v, Gravity.CENTER, 0, 0);
                float alpha1 = bottomViewHolder.btPay.getAlpha();
                Log.e(TAG, "onClick: preAlpha: " + alpha + "---afterAlpha: " + alpha1);
            }
        });

        topViewHolder.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                String tabTitle = tab.getText().toString();
                if ("优惠详情".equals(tabTitle)) {

                    lvDetail.addFooterView(detailBottomView);
                    lvDetail.setAdapter(detailAdapter);

                    bottomViewHolder.btPay.setVisibility(View.VISIBLE);

                } else if ("买单比价".equals(tabTitle)) {

                    lvDetail.removeFooterView(detailBottomView);
                    lvDetail.setAdapter(priceAdapter);

                    bottomViewHolder.btPay.setVisibility(View.GONE);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
                            Gson gson = new Gson();
                            CheapDetailResp detailResp = gson.fromJson(response, CheapDetailResp.class);
                            String code = detailResp.getCode();
                            if ("200".equals(code)) {
                                String logoImageUrl = detailResp.getBand_logo_image_url();


                                // 不同银行所对应的优惠
                                List<DiscountListBean> discountList = detailResp.getDiscountList();
                                if (discountList != null) {
                                    cheapDetailList.clear();
                                    cheapDetailList.add(new DiscountListBean());
//                                    cheapDetailList.add(new DiscountListBean());
//                                    cheapDetailList.add(new DiscountListBean());
//                                    cheapDetailList.add(new DiscountListBean());
                                    cheapDetailList.addAll(discountList);
                                    if (detailAdapter != null) {
                                        detailAdapter.notifyDataSetChanged();
                                    }
                                }


                                // 该品牌所对应的最近的几个门店(最多三条)---其中第一条显示到top，剩下的显示到bottom
                                List<McDisRespBean> mcDisRespList = detailResp.getMcDisRespList();
                                if (mcDisRespList != null) {
                                    if (mcDisRespList.size() >= 1) {
                                        // 拿到第一条数据显示到top
                                        McDisRespBean mcBean = mcDisRespList.remove(0);
                                        // 设置当前门店名
                                        topViewHolder.tvMcName.setText(mcBean.getMc_name());
                                        topViewHolder.tvMcAddress.setText(mcBean.getAddress());


                                        if (mcDisRespList.size() > 0) {
                                            // 用一个集合来存储剩下的门店
                                            bottomMcList.clear();
                                            bottomMcList.addAll(mcDisRespList);
                                            if (bottomMcAdapter != null) {
                                                bottomMcAdapter.notifyDataSetChanged();
                                            }
                                        }

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

    private void requestPayListData() {

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "aadf1479-a8a0-474d-ad7e-b9aff3f4c23d");
        params.put("uid", "-1");
        params.put("mc_id", "28361");
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

    private void testRxJava() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("First");

                SystemClock.sleep(1000);
                e.onNext("1s后  Second");

                SystemClock.sleep(2000);
                e.onNext("再2s后  Third");

                SystemClock.sleep(3000);
                e.onNext("再3s后  Forth");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    // TODO:测试RxRestService
    private void getRxServiceData() {

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "1");
        params.put("uid", "-1");
        params.put("mc_id", "28361");
        params.put("isNear", 1);
        params.put("point_x", 121.5099f);
        params.put("point_y", 31.2289f);
        params.put("category_id", "-1");
        params.put("band_id", "-1");
        RxRestClient.builder()
                .params(params)
                .url("ccfin-business-service-1.0/api/v1/business/getBandAndDiscountDetailV2")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Gson gson = new Gson();
                        CheapDetailResp detailResp = gson.fromJson(s, CheapDetailResp.class);
                        String code = detailResp.getCode();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: Success");
                    }
                });
    }

    class DetailBottomViewHolder {

        @BindView(R.id.tv_all_mc_count)
        TextView tvAllMcCount;
        @BindView(R.id.rl_all_cheap_mc)
        RelativeLayout rlAllCheapMc;
        @BindView(R.id.lv_other_mc)
        ScrollListView lvOtherMc;
        @BindView(R.id.rl_cheap_wrong)
        RelativeLayout rlCheapWrong;
        @BindView(R.id.bt_pay)
        Button btPay;

        DetailBottomViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class DetailTopViewHolder {

        @BindView(R.id.iv_mc_logo)
        ImageView ivMcLogo;
        @BindView(R.id.tv_mc_name)
        TextView tvMcName;
        @BindView(R.id.iv_tag1_discount)
        ImageView ivTag1Discount;
        @BindView(R.id.iv_tag2_special_cheap)
        ImageView ivTag2SpecialCheap;
        @BindView(R.id.iv_tag3_exchange)
        ImageView ivTag3Exchange;
        @BindView(R.id.iv_tag4_reduce)
        ImageView ivTag4Reduce;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.mc_star)
        MyStarBar mcStar;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_cate_name)
        TextView tvCateName;
        @BindView(R.id.tv_mc_address)
        TextView tvMcAddress;
        @BindView(R.id.iv_mc_phone)
        ImageView ivMcPhone;
        @BindView(R.id.tablayout)
        TabLayout tablayout;

        DetailTopViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
