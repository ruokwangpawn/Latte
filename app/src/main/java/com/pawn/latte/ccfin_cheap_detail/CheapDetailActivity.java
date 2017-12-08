package com.pawn.latte.ccfin_cheap_detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pawn.latte.R;
import com.pawn.latte.ccfin_cheap_detail.adapter.BottomMcAdapter;
import com.pawn.latte.ccfin_cheap_detail.adapter.CheapDetailAdapter;
import com.pawn.latte.ccfin_cheap_detail.adapter.CheapPayAdapter;
import com.pawn.latte.ccfin_cheap_detail.adapter.PriceCompareAdapter;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;
import com.pawn.latte.ccfin_cheap_detail.bean.PayBankListResp.RedPacketResponseListBean;
import com.pawn.latte.ccfin_cheap_detail.contract.CheapDetailContract;
import com.pawn.latte.ccfin_cheap_detail.presenter.CheapDetailPresenter;
import com.pawn.latte.ccfin_cheap_detail.widget.CustomDialog;
import com.pawn.latte.ccfin_cheap_detail.widget.MyStarBar;
import com.pawn.latte.ccfin_cheap_detail.widget.ScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheapDetailActivity extends AppCompatActivity implements CheapDetailContract.View, View.OnClickListener {

    private static final String TAG = CheapDetailActivity.class.getSimpleName();

    @BindView(R.id.lv_detail)
    ListView lvDetail;
    @BindView(R.id.ib_left)
    ImageButton ibLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_collection)
    ImageButton ibCollection;
    @BindView(R.id.ib_share)
    ImageButton ibShare;

    private CheapDetailContract.Presenter presenter = null;

    private Context context;

    private List<DiscountListBean> cheapDetailList = new ArrayList<>();
    private List<McDisRespBean> bottomMcList = new ArrayList<>();
    private List<Object> priceCompareList = new ArrayList<>();

    private CheapDetailAdapter detailAdapter;
    private PriceCompareAdapter priceAdapter;
    private BottomMcAdapter bottomMcAdapter;
    private View detailBottomView;
    private DetailBottomViewHolder bottomViewHolder;
    private View detailTopView;
    private DetailTopViewHolder topViewHolder;
    private View payTypeListView;
    private CheapPayAdapter payListAdapter;
    private PayViewHolder payViewHolder;

    private CustomDialog dialog;
    private List<String> payList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheap_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ButterKnife.bind(this);
        context = this;

        presenter = new CheapDetailPresenter();
        presenter.subscribe(this);

        tvTitle.setText("详情");

        initView();

        initData();

        requestMcData();
        requestPayListData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @SuppressLint("InflateParams")
    private void initView() {

        detailTopView = LayoutInflater.from(this).inflate(R.layout.cheap_detail_top, null);
        topViewHolder = new DetailTopViewHolder(detailTopView);
        detailBottomView = LayoutInflater.from(this).inflate(R.layout.cheap_detail_bottom, null);
        bottomViewHolder = new DetailBottomViewHolder(detailBottomView);
        payTypeListView = LayoutInflater.from(this).inflate(R.layout.dialog_pay, null);
        payViewHolder = new PayViewHolder(payTypeListView);

    }

    private void initData() {

        // 优惠详情列表的adapter
        detailAdapter = new CheapDetailAdapter(this, cheapDetailList, R.layout.item_cheap_detail);

        for (int i = 0; i < 7; i++) {
            priceCompareList.add("");
        }
        // 比价的adapter
        priceAdapter = new PriceCompareAdapter(this, priceCompareList, R.layout.item_cheap_price_compare);

        // ListView增加头尾布局
        lvDetail.addHeaderView(detailTopView);
        lvDetail.addFooterView(detailBottomView);
        lvDetail.setAdapter(detailAdapter);

        // 该商户下本门店周边的其他门店(最多显示两家)
        bottomMcAdapter = new BottomMcAdapter(this, bottomMcList, R.layout.item_cheap_mc);
        bottomViewHolder.lvOtherMc.setAdapter(bottomMcAdapter);
        bottomViewHolder.lvOtherMc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "跳转H5导航" + position, Toast.LENGTH_SHORT).show();
            }
        });

        // 两个标签Tab
        topViewHolder.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                String tabTitle = tab.getText().toString();
                if ("优惠详情".equals(tabTitle)) {

                    lvDetail.addFooterView(detailBottomView);
                    lvDetail.setAdapter(detailAdapter);

                    topViewHolder.llComparePrice.setVisibility(View.GONE);
                    bottomViewHolder.btPay.setVisibility(View.VISIBLE);

                } else if ("买单比价".equals(tabTitle)) {

                    lvDetail.removeFooterView(detailBottomView);
                    lvDetail.setAdapter(priceAdapter);

                    topViewHolder.llComparePrice.setVisibility(View.VISIBLE);
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

        // 支付列表
        payList = new ArrayList<>();
        payList.add("");
        payList.add("");
        payListAdapter = new CheapPayAdapter(this, payList, R.layout.item_pay_for_cheap);
        payViewHolder.lvPayType.setAdapter(payListAdapter);

        // 支付Dialog
        dialog = new CustomDialog.Build(context)
                .cancelTouchOut(false)
                .style(R.style.CustomDialog)
                .view(payTypeListView)
                .widthDp(270)
                .heightDp(300)
                .build();

        // 点击事件
        // 返回
        ibLeft.setOnClickListener(this);
        // 收藏
        ibCollection.setOnClickListener(this);
        // 分享
        ibShare.setOnClickListener(this);
        // 当前门店导航H5
        topViewHolder.tvMcAddress.setOnClickListener(this);
        // 当前门店电话
        topViewHolder.ivMcPhone.setOnClickListener(this);
        // 比价
        topViewHolder.btPriceCompare.setOnClickListener(this);
        // 全部门店
        bottomViewHolder.rlAllCheapMc.setOnClickListener(this);
        // 优惠报错
        bottomViewHolder.rlCheapWrong.setOnClickListener(this);
        // 支付
        bottomViewHolder.btPay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_left:
                finish();
                break;
            case R.id.ib_collection:
                Toast.makeText(CheapDetailActivity.this, "收藏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ib_share:
                Toast.makeText(CheapDetailActivity.this, "分享Dialog", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_pay:
                payListAdapter.notifyDataSetChanged();
                dialog.show();
                payList.add("");
                break;
            case R.id.rl_cheap_wrong:
                Toast.makeText(CheapDetailActivity.this, "优惠报错", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_mc_address:
                Toast.makeText(CheapDetailActivity.this, "当前门店地图导航", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_mc_phone:
                Toast.makeText(CheapDetailActivity.this, "当前门店电话", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_price_compare:
                Toast.makeText(CheapDetailActivity.this, "买单比价", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_all_cheap_mc:
                Toast.makeText(CheapDetailActivity.this, "所有门店地图", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void requestMcData() {

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "c43838a7-5965-45ea-836a-2fab372281a2");
        params.put("uid", "-1");
        params.put("mc_id", "28368");
        params.put("isNear", 0);
        params.put("point_x", 121.5099f);
        params.put("point_y", 31.2289f);
        params.put("category_id", "-1");
        params.put("band_id", "-1");
        presenter.loadDataTask(params);
    }

    private void requestPayListData() {

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("ticket", "c43838a7-5965-45ea-836a-2fab372281a2");
        params.put("uid", "-1");
        params.put("mc_id", "28361");
        presenter.loadPayListTask(params);
    }

    @Override
    public void onPageStart() {

    }

    @Override
    public void showSharePreferencesValues() {

    }

    @Override
    public void onNewIntent() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showNoNetworkTask() {

    }

    @Override
    public void showRequestErrorTask() {

    }

    @Override
    public void onDetailRequestSuccess(CheapDetailResp detailResp) {

        // 是否是线上门店
        int isSpecial = detailResp.getIsSpecial();
        // 是否收藏
        int isCollect = detailResp.getIsCollect();
        // 是否是特约商家
        int isPreference = detailResp.getIs_preference();
        // 商家Logo
        String logoImageUrl = detailResp.getBand_logo_image_url();
        // 商家名
        String bandName = detailResp.getBand_name();
        // 人均消费
        int avgConsume = detailResp.getAvg_consumption();
        topViewHolder.tvPrice.setText(new StringBuilder("¥").append(avgConsume).append("/人"));
        // 星级
        int starCount = detailResp.getPraise_point();
        topViewHolder.mcStar.setStarMark((float) (starCount) / 2);
        // 分类名
        String catName = detailResp.getCat_name();
        topViewHolder.tvCateName.setText(catName);
        // 当前区域总共有多少门店
        int totalMcCount = detailResp.getTotal_merchant_count();
        // 城市
        String city = detailResp.getCity();
        bottomViewHolder.tvAllMcCount.setText(
                new StringBuilder()
                        .append(city)
                        .append("  所有优惠门店（")
                        .append(totalMcCount)
                        .append("）"));


        // 满减、折扣等优惠的集合
        List<String> disTypes = detailResp.getDis_typeArray();


        // 不同银行所对应的优惠
        List<DiscountListBean> discountList = detailResp.getDiscountList();
        if (discountList != null) {
            cheapDetailList.clear();
            if (discountList.size() < 2) {

                cheapDetailList.add(new DiscountListBean());
                cheapDetailList.add(new DiscountListBean());
                cheapDetailList.add(new DiscountListBean());
                cheapDetailList.add(new DiscountListBean());
            }
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
                String mcName = mcBean.getMc_name();
                topViewHolder.tvMcName.setText(mcName);
                // 设置当前门店地址
                String address = mcBean.getAddress();
                topViewHolder.tvMcAddress.setText(address);
                // 是否是海外门店
                int overSeas = mcBean.getOverSeas();
                // 导航地图H5地址
                String mapUrl = mcBean.getMapUrl();
                // 商家电话
                String phone = mcBean.getPhone();
                // 商家距离当前位置的距离
                String distance = mcBean.getDistance();
                // mc_id
                String mc_id = mcBean.getMc_id();


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

    @Override
    public void onDetailRequestFail() {

    }

    @Override
    public void onPayListRequestSuccess(List<RedPacketResponseListBean> payList) {

    }

    @Override
    public void onPayListRequestFail() {

    }

    @Override
    public void onCollection() {

    }

    @Override
    public void onUnCollection() {

    }

    @Override
    public void onShare() {

    }

    @Override
    public void onCheapWrongInfo() {

    }

    @Override
    public void onPay() {

    }

    @Override
    public void onPayItem() {

    }

    @Override
    public void onPriceCompare() {

    }

    @Override
    public void onCurrentMcCall() {

    }

    @Override
    public void onCurrentMcMapGuide() {

    }

    @Override
    public void onAllMcMap() {

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
        @BindView(R.id.ll_compare_price)
        LinearLayout llComparePrice;
        @BindView(R.id.bt_price_compare)
        Button btPriceCompare;
        @BindView(R.id.et_price_compare)
        EditText etPriceCompare;

        DetailTopViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class PayViewHolder {

        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.lv_pay_type)
        ListView lvPayType;

        PayViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
