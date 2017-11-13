package com.pawn.latte.ccfin_cheap_detail.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pawn on 2017/11/7 09.
 */

public class TestBean implements Parcelable {

    private String content;
    private boolean isCollapsed;

    public TestBean() {

    }

    public TestBean(String content, boolean isCollapsed) {
        this.content = content;
        this.isCollapsed = isCollapsed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeByte(isCollapsed ? (byte) 1 : (byte) 0);
    }

    protected TestBean(Parcel in) {
        this.content = in.readString();
        this.isCollapsed = in.readByte() != 0;
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            TestBean bean = new TestBean();
            bean.content = source.readString();
            bean.isCollapsed = source.readByte() == (byte) 1;
            return bean;
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
