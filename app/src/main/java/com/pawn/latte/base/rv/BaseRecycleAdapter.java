package com.pawn.latte.base.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Pawn on 2017/11/14 09.
 */

public abstract class BaseRecycleAdapter extends RecyclerView.Adapter<BaseRecyViewHolder> {

    private int layoutId;
    private List<Object> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public BaseRecycleAdapter(Context context, int layoutId, List<Object> data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @Override
    public BaseRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseRecyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });
        convert(holder, data.get(position));
    }

    public abstract void convert(BaseRecyViewHolder holder, Object bean);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

}
