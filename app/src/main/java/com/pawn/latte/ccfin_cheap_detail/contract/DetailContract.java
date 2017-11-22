package com.pawn.latte.ccfin_cheap_detail.contract;

import com.pawn.latte.IBasePresenter;
import com.pawn.latte.IBaseView;

/**
 * Created by Pawn on 2017/11/20 11.
 */

public interface DetailContract {

    interface View extends IBaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void changeCollectionIcon();

        void showShareDialog();

        void onPageStart();

        void onPageSuccess();

        void onPageError();

        boolean isActive();
    }

    interface Presenter extends IBasePresenter {

        void loadDataTask();

        void loadPayListTask();

        void collectionTask();

        void uncollectionTask();
    }
}

