package com.pawn.latte.ccfin_cheap_detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.Gson;
import com.pawn.latte.R;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;

import org.goldian.ccfin_core.net.RestClient;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class CheapDetailActivity extends AppCompatActivity {

    private static final String TAG = CheapDetailActivity.class.getSimpleName();

    private ViewPager container;
    private TabLayout tabLayout;
    private Button btPay;

    private MyFragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;

    private List<String> titles;

    private DetailFragment detailFragment;
    private PriceComparisonFragment priceComparisonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheap_detail);
        container = (ViewPager) findViewById(R.id.content);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        btPay = (Button) findViewById(R.id.bt_pay);

        titles = new ArrayList<>();
        titles.add("优惠详情");
        titles.add("买单比价");

        fragmentList = new ArrayList<>();
        detailFragment = new DetailFragment();
        fragmentList.add(detailFragment);
        priceComparisonFragment = new PriceComparisonFragment();
        fragmentList.add(priceComparisonFragment);

        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        container.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(container);

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
                            // 该品牌所对应的最近的几个门店(最多三条)---其中第一条显示到top，剩下的显示到bottom
                            List<McDisRespBean> mcDisRespList = detailResp.getMcDisRespList();
                            if (mcDisRespList != null) {
                                if (mcDisRespList.size() >= 1) {
                                    // 拿到第一条数据显示到top
                                    McDisRespBean mcBean = mcDisRespList.remove(0);


                                    if (mcDisRespList.size() > 0) {
                                        // 用一个集合来存储剩下的门店
                                        List<McDisRespBean> bottomMcList = new ArrayList<>();
                                        bottomMcList.addAll(mcDisRespList);
                                        // 传递给Fragment
                                        detailFragment.setCheapDetailsList(bottomMcList, discountList);
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
                .build()
                .get();

    }
}
