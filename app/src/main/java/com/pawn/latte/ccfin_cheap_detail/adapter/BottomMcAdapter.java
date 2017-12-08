package com.pawn.latte.ccfin_cheap_detail.adapter;

import android.content.Context;

import com.pawn.latte.base.lv.BaseLvAdapter;
import com.pawn.latte.base.lv.BaseLvViewHolder;
import com.pawn.latte.ccfin_cheap_detail.bean.CheapDetailResp.McDisRespBean;

import java.util.List;

/**
 * Created by Pawn on 2017/12/7 14.
 */

public class BottomMcAdapter extends BaseLvAdapter<McDisRespBean> {

    public BottomMcAdapter(Context context, List<McDisRespBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(BaseLvViewHolder viewHolder, McDisRespBean item, int position) {

    }
}
