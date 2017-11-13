package com.pawn.latte.ccfin_cheap_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pawn.latte.R;
import com.pawn.latte.base.BaseLvAdapter;
import com.pawn.latte.base.LvViewHolder;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;
import com.pawn.latte.ccfin_cheap_detail.view.CustomDialog;
import com.pawn.latte.ccfin_cheap_detail.view.ExpandTextView;
import com.pawn.latte.ccfin_cheap_detail.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pawn on 2017/11/3 17.
 */

public class DetailFragment extends Fragment {

    private ListView lvCheap;
    private View bottomView;

    private List<McDisRespBean> mcList = new ArrayList<>();
    private List<DiscountListBean> cheapList = new ArrayList<>();
    private BaseLvAdapter<DiscountListBean> adapter;

    private BottomViewHolder bottomViewHolder;
    private BaseLvAdapter<McDisRespBean> bottomMcAdapter;

    public void setCheapDetailsList(List<McDisRespBean> mcDisRespList, List<DiscountListBean> discountList) {
        if (discountList != null) {
            cheapList.clear();
            cheapList.addAll(discountList);
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
            Log.e("DetailFragment", "CheapList: " + cheapList.size());
        }
        if (mcDisRespList != null) {
            mcList.clear();
            mcList.addAll(mcDisRespList);
            if (bottomMcAdapter != null) {
                bottomMcAdapter.notifyDataSetChanged();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cheap_detail, container, false);
        bottomView = inflater.inflate(R.layout.cheap_detail_bottom, null);
        bottomViewHolder = new BottomViewHolder(bottomView);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvCheap = view.findViewById(R.id.lv_detail);
        lvCheap.addFooterView(bottomView);

        adapter = new BaseLvAdapter<DiscountListBean>(getContext(), cheapList, R.layout.item_cheap_detail) {

            @Override
            public void convert(LvViewHolder viewHolder, final DiscountListBean item, int position) {
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
        lvCheap.setAdapter(adapter);

        bottomMcAdapter = new BaseLvAdapter<McDisRespBean>(getContext(), mcList, R.layout.item_cheap_mc) {

            @Override
            public void convert(LvViewHolder viewHolder, McDisRespBean item, int position) {

            }
        };
        bottomViewHolder.lvOtherMc.setAdapter(bottomMcAdapter);
        bottomViewHolder.lvOtherMc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bottomViewHolder.rlCheapWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDialog dialog = new CustomDialog.Build(getContext())
                        .cancelTouchOut(false)
                        .style(R.style.CustomDialog)
                        .view(R.layout.dialog_cheap_wrong_info)
                        .heightDp(340)
                        .widthDp(270)
                        .build();
                dialog.show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    class BottomViewHolder {

        @BindView(R.id.tv_all_mc_count)
        TextView tvAllMcCount;
        @BindView(R.id.rl_all_cheap_mc)
        RelativeLayout rlAllCheapMc;
        @BindView(R.id.lv_other_mc)
        MyListView lvOtherMc;
        @BindView(R.id.rl_cheap_wrong)
        RelativeLayout rlCheapWrong;
        @BindView(R.id.bt_pay)
        Button btPay;

        BottomViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
