package com.pawn.latte.ccfin_cheap_detail;

import android.os.Handler;

import org.goldian.ccfin_core.app.Latte;
import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.IRequest;
import org.goldian.ccfin_core.net.callback.ISuccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawn on 2017/11/29 17.
 */

public final class CCFinRequestCallback implements Callback {

    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IFailure mFailure;
    private final IError mError;
    private static final Handler HANDLER = Latte.getHandler();

    public CCFinRequestCallback(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.mRequest = request;
        this.mSuccess = success;
        this.mFailure = failure;
        this.mError = error;
    }

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}
