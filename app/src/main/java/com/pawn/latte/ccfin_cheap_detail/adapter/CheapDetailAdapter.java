package com.pawn.latte.ccfin_cheap_detail.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.pawn.latte.R;
import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean.DiscountTitleContent;
import com.pawn.latte.ccfin_cheap_detail.widget.ExpandTextView;

import java.util.List;

import static com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.DiscountListBean.DiscountTitleContent.*;

/**
 * Created by Pawn on 2017/12/7 14.
 */

public class CheapDetailAdapter extends BaseLvAdapter<DiscountListBean> {

    public CheapDetailAdapter(Context context, List<DiscountListBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(BaseLvViewHolder viewHolder, final DiscountListBean item, int position) {
        // 设置默认不展开
        item.setExpaned(true);

        ExpandTextView detailContent = viewHolder.getView(R.id.detail_content);
        TextView tvDisTitle = viewHolder.getView(R.id.tv_dis_title);

        String bankName = item.getBank_name();
        String bank_id = item.getBank_id();
        String bankLogoUrl = item.getBank_logo_url();
        if (TextUtils.isEmpty(bankName)) {
            bankName = "各大银行";
        }
        String disKeywords = item.getDis_keywords();
        tvDisTitle.setText(new StringBuilder(bankName).append("：").append(disKeywords));

        StringBuilder disContent = new StringBuilder();
        List<DiscountTitleContent> discountTitleContent = item.getDiscountTitleContent();
        if (discountTitleContent != null) {
            for (int i = 0; i < discountTitleContent.size(); i++) {
                DiscountTitleContent bean = discountTitleContent.get(i);
                String titleName = bean.getTitle_name();
                disContent.append(titleName).append("\n");

                List<TitleContentBean> contentList = bean.getTitle_content();
                for (int j = 0; j < contentList.size(); j++) {
                    TitleContentBean titleContentBean = contentList.get(j);
                    String content = titleContentBean.getContentName();
                    // 当两个索引同时为最后一个时说明已经到了最后，则不需要换行
                    if (i + 1 == discountTitleContent.size() && j + 1 == contentList.size()) {
                        disContent.append(content);
                    } else {
                        disContent.append(content).append("\n");
                    }
                }
            }
            detailContent.setText(disContent.toString(), item.isExpaned());
        }

        detailContent.setListener(new ExpandTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(boolean isExpanded) {
                item.setExpaned(isExpanded);
            }
        });
    }
}
