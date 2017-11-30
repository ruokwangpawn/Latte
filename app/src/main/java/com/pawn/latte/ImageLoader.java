package com.pawn.latte;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Pawn on 2017/11/28 15.
 */

public class ImageLoader {

    private Context context;

    public void get() {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        List list = new ArrayList();

        List list1 = new Stack();

        Vector vector = new Vector();

    }

    public void showVisibalData(int start, int end) {

    }

}
