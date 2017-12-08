package com.pawn.latte;

/**
 * Created by Pawn on 2017/11/3 17.
 */

public interface IBasePresenter {

    void subscribe(IBaseView view);

    void unSubscribe();

}
