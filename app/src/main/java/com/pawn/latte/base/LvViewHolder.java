package com.pawn.latte.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pawn on 2017/8/18 16.
 */

public class LvViewHolder {

    private final SparseArray<View> mViews;
    private View mConvertview;

    private LvViewHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<>();
        mConvertview = inflater.inflate(layoutId, parent, false);
        mConvertview.setTag(this);
    }

    public static LvViewHolder get(LayoutInflater inflater, View convertview, ViewGroup parent, int layoutId) {
        if (convertview == null) {
            return new LvViewHolder(inflater, parent, layoutId);
        } else {
            return (LvViewHolder) convertview.getTag();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertview.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getCovertview() {
        return mConvertview;
    }

}
