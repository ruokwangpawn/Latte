package com.pawn.latte.base.rv;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Pawn on 2017/11/14 09.
 */

public class BaseRecyViewHolder extends RecyclerView.ViewHolder {

    public BaseRecyViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = itemView.findViewById(viewId);
        return (T) view;
    }
}
