package com.pawn.latte.ccfin_cheap_detail.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.pawn.latte.R;
import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;

import java.util.List;

/**
 * Created by Pawn on 2017/12/7 14.
 */

public class PriceCompareAdapter extends BaseLvAdapter<Object> {

    public PriceCompareAdapter(Context context, List<Object> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(BaseLvViewHolder viewHolder, Object item, int position) {
        TextView tvPreMoney = viewHolder.getView(R.id.tv_pre_money);
        // TextView中间加横线
        tvPreMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}
