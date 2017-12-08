package com.pawn.latte.ccfin_cheap_detail.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.pawn.latte.R;

import org.goldian.ccfin_core.utils.DisplayUtil;

/**
 * Created by Pawn on 2017/11/9 14.
 */

public class CustomDialog extends Dialog {

    private int height, width;
    private boolean cancelTouchOut;
    private boolean cancelable;
    private View contentView;

    private CustomDialog(Build build) {
        super(build.context, R.style.CustomDialog);
        height = build.height;
        width = build.width;
        cancelTouchOut = build.cancelTouchOut;
        cancelable = build.cancelable;
        contentView = build.view;
    }

    private CustomDialog(Build build, int style) {
        super(build.context, style);
        height = build.height;
        width = build.width;
        cancelTouchOut = build.cancelTouchOut;
        cancelable = build.cancelable;
        contentView = build.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView);
        setCanceledOnTouchOutside(cancelTouchOut);
        setCancelable(cancelable);

        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            params.height = height;
            params.width = width;
            window.setAttributes(params);
        }
    }

    public static final class Build {

        private Context context;
        private int height, width;
        private boolean cancelTouchOut;
        /**
         * 默认按返回键可以dismiss
         */
        private boolean cancelable = true;
        private View view;
        private int resStyle = -1;

        public Build(Context context) {
            this.context = context;
        }

        public Build view(int resViewId) {
            this.view = LayoutInflater.from(context).inflate(resViewId, null);
            return this;
        }

        public Build view(View view) {
            this.view = view;
            return this;
        }

        public Build heightDp(int heightDp) {
            this.height = DisplayUtil.dp2px(context, heightDp);
            return this;
        }

        public Build heightPx(int heightPx) {
            this.height = heightPx;
            return this;
        }

        public Build heightDimenRes(int heightDimenResId) {
            height = context.getResources().getDimensionPixelOffset(heightDimenResId);
            return this;
        }

        public Build widthDp(int widthDp) {
            this.width = DisplayUtil.dp2px(context, widthDp);
            return this;
        }

        public Build widthPx(int widthPx) {
            this.width = widthPx;
            return this;
        }

        public Build widthDimenRes(int widthDimenResId) {
            this.width = context.getResources().getDimensionPixelOffset(widthDimenResId);
            return this;
        }

        public Build style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Build cancelTouchOut(boolean cancelTouchOut) {
            this.cancelTouchOut = cancelTouchOut;
            return this;
        }

        public Build cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Build addViewOnClick(int viewResId, View.OnClickListener listener) {
            view.findViewById(viewResId).setOnClickListener(listener);
            return this;
        }

        public CustomDialog build() {
            if (resStyle == -1) {
                return new CustomDialog(this);
            } else {
                return new CustomDialog(this, resStyle);
            }
        }

    }

}
