package com.pawn.latte.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Pawn on 2017/8/18 16.
 */

public abstract class BaseLvAdapter<T> extends BaseAdapter {

    private List<T> data;
    private final int itemLayoutId;
    private LayoutInflater mInflater;

    public BaseLvAdapter(Context context, List<T> data, int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvViewHolder viewHolder = getViewHolder(convertView, parent);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getCovertview();
    }

    public abstract void convert(LvViewHolder viewHolder, T item, int position);

    private LvViewHolder getViewHolder(View convertView, ViewGroup parent) {

        return LvViewHolder.get(mInflater, convertView, parent, itemLayoutId);
    }

}
