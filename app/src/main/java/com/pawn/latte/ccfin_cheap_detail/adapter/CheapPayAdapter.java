package com.pawn.latte.ccfin_cheap_detail.adapter;

import android.content.Context;

import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;

import java.util.List;

/**
 * Created by Pawn on 2017/12/7 14.
 */

public class CheapPayAdapter extends BaseLvAdapter<String> {

    public CheapPayAdapter(Context context, List<String> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(BaseLvViewHolder viewHolder, String item, int position) {

    }
}
