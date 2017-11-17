package com.pawn.latte.ccfin_cheap_detail;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pawn.latte.R;
import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;
import com.pawn.latte.ccfin_cheap_detail.view.CustomDialog;
import com.pawn.latte.ccfin_cheap_detail.view.ExpandTextView;
import com.pawn.latte.ccfin_cheap_detail.view.ScrollListView;

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
import io.reactivex.schedulers.Schedulers;

public class CheapDetailActivity extends AppCompatActivity {

    private static final String TAG = CheapDetailActivity.class.getSimpleName();
    private Context context;

    private ScrollView scrollView;
    private TabLayout tabLayout;
    private ScrollListView lvDetail;
    private ScrollListView lvPriceCompare;

    private List<DiscountListBean> cheapDetailList = new ArrayList<>();
    private List<McDisRespBean> bottomMcList = new ArrayList<>();
    private List<Object> priceCompareList = new ArrayList<>();

    private BaseLvAdapter<McDisRespBean> bottomMcAdapter;
    private BaseLvAdapter<DiscountListBean> detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheap_detail);
        context = this;

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        lvDetail = (ScrollListView) findViewById(R.id.lv_detail);
        lvPriceCompare = (ScrollListView) findViewById(R.id.lv_price_compare);
        View detailBottomView = LayoutInflater.from(this).inflate(R.layout.cheap_detail_bottom, null);
        DetailBottomViewHolder bottomViewHolder = new DetailBottomViewHolder(detailBottomView);

        scrollView.smoothScrollTo(0, 0);

        lvDetail.addFooterView(detailBottomView);
        detailAdapter = new BaseLvAdapter<DiscountListBean>(this, cheapDetailList, R.layout.item_cheap_detail) {
            @Override
            public void convert(BaseLvViewHolder viewHolder, DiscountListBean item, int position) {
                ExpandTextView detailContent = viewHolder.getView(R.id.detail_content);
                detailContent.setText("告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球" +
                        "告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球" +
                        "告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球" +
                        "告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球" +
                        "告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球" +
                        "告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球告白气球", true);

                detailContent.setListener(new ExpandTextView.OnExpandStateChangeListener() {
                    @Override
                    public void onExpandStateChanged(boolean isExpanded) {

                    }
                });
            }
        };
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
        bottomViewHolder.rlCheapWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDialog dialog = new CustomDialog.Build(context)
                        .cancelTouchOut(false)
                        .style(R.style.CustomDialog)
                        .view(R.layout.dialog_cheap_wrong_info)
                        .heightDp(340)
                        .widthDp(270)
                        .build();
                dialog.show();
            }
        });

        for (int i = 0; i < 7; i++) {
            priceCompareList.add("");
        }

        lvPriceCompare.setAdapter(new BaseLvAdapter<Object>(this, priceCompareList, R.layout.item_cheap_price_compare) {

            @Override
            public void convert(BaseLvViewHolder viewHolder, Object item, int position) {
                TextView tvPreMoney = viewHolder.getView(R.id.tv_pre_money);
                tvPreMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int scrollX = scrollView.getScrollX();
                int scrollY = scrollView.getScrollY();

                String tabTitle = tab.getText().toString();
                if ("优惠详情".equals(tabTitle)) {
                    lvDetail.setVisibility(View.VISIBLE);
                    lvPriceCompare.setVisibility(View.GONE);

                } else if ("买单比价".equals(tabTitle)) {
                    lvDetail.setVisibility(View.GONE);
                    lvPriceCompare.setVisibility(View.VISIBLE);
                }
                scrollView.smoothScrollTo(0, 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "1");
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
                        Gson gson = new Gson();
                        CheapDetailResp detailResp = gson.fromJson(response, CheapDetailResp.class);
                        String code = detailResp.getCode();
                        if ("200".equals(code)) {
                            // 不同银行所对应的优惠
                            List<DiscountListBean> discountList = detailResp.getDiscountList();
                            if (discountList != null) {
                                cheapDetailList.clear();
                                cheapDetailList.add(new DiscountListBean());
                                cheapDetailList.add(new DiscountListBean());
                                cheapDetailList.add(new DiscountListBean());
                                cheapDetailList.add(new DiscountListBean());
                                cheapDetailList.add(new DiscountListBean());
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

        // TODO:测试RxRestService
        getRxServiceData();

        // TODO:测试RxJava
        testRxJava();
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
}
