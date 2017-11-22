package com.pawn.latte.ccfin_cheap_detail.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import org.goldian.ccfin_core.utils.DisplayUtil;

/**
 * Created by Pawn on 2017/11/17 14.
 */

public class CustomPopupWindow extends PopupWindow {

    private Context context;
    private PopupWindow mPopupWindow;

    public CustomPopupWindow(Build build) {
        this.context = build.context;
        mPopupWindow = new PopupWindow(build.view, build.width, build.height, build.focusable);
        mPopupWindow.setAnimationStyle(build.animResId);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(build.canTouchOutside);
        mPopupWindow.setFocusable(build.focusable);

        mPopupWindow.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {

                setBackgroundAlpha(1.0f);
                setOnDismissListener(this);
            }
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mPopupWindow != null) {
            setBackgroundAlpha(1.0f);
            mPopupWindow.dismiss();
        }
    }

    private void setBackgroundAlpha(float alpha) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        window.setAttributes(layoutParams);

    }

    /**
     * 根据父布局，显示位置
     *
     * @param rootviewid
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public CustomPopupWindow showAtLocation(int rootviewid, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            View rootview = LayoutInflater.from(context).inflate(rootviewid, null);
            mPopupWindow.showAtLocation(rootview, gravity, x, y);
        }
        return this;
    }

    /**
     * 根据id获取view ，并显示在该view的位置
     *
     * @param targetviewId
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    public CustomPopupWindow showAsLaction(int targetviewId, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            View targetview = LayoutInflater.from(context).inflate(targetviewId, null);
            mPopupWindow.showAsDropDown(targetview, gravity, offx, offy);
        }
        return this;
    }

    /**
     * 显示在 targetview 的不同位置
     *
     * @param targetview
     * @param gravity
     * @param offx
     * @param offy
     * @return
     */
    public CustomPopupWindow showAsLaction(View targetview, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(targetview, gravity, offx, offy);
        }
        return this;
    }

    public CustomPopupWindow showAsLocation(View targetView, int gravity, int offx, int offy) {
        if (mPopupWindow != null) {
            setBackgroundAlpha(0.3f);
            mPopupWindow.showAsDropDown(targetView, offx, offy, gravity);
        }
        return this;
    }

    public static final class Build {

        private Context context;
        private View view;
        private int height, width;
        private int animResId;
        private boolean focusable;
        private boolean canTouchOutside;

        public Build(Context context) {
            this.context = context;
        }

        public Build setContentView(View contentView) {
            this.view = contentView;
            return this;
        }

        public Build setContentView(int viewResId) {
            this.view = LayoutInflater.from(context).inflate(viewResId, null);
            return this;
        }

        public Build setHeightAndWidthDp(int heightDp, int widthDp) {

            this.height = DisplayUtil.dp2px(context, heightDp);
            this.width = DisplayUtil.dp2px(context, widthDp);
            return this;
        }

        public Build setHeightAndWidthPx(int heightPx, int widthPx) {
            this.height = heightPx;
            this.width = widthPx;
            return this;
        }

        public Build setCanTouchOutside(boolean canTouchOutside) {
            this.canTouchOutside = canTouchOutside;
            return this;
        }

        public Build setFocusable(boolean focusable) {
            this.focusable = focusable;
            return this;
        }

        public Build setAnimStyle(int animResId) {
            this.animResId = animResId;
            return this;
        }

        public Build addViewClick(int viewResId, View.OnClickListener clickListener) {
            view.findViewById(viewResId).setOnClickListener(clickListener);
            return this;
        }

        public CustomPopupWindow build() {
            return new CustomPopupWindow(this);
        }
    }

}
