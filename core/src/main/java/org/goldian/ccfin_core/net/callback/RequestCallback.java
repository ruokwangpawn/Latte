package org.goldian.ccfin_core.net.callback;

import android.os.Handler;
import android.support.annotation.NonNull;

import org.goldian.ccfin_core.app.ConfigKeys;
import org.goldian.ccfin_core.app.Latte;
import org.goldian.ccfin_core.net.RestCreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawn on 2017/11/7 15.
 */

public final class RequestCallback implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private static final Handler HANDLER = Latte.getHandler();

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure(t);
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    /**
     * 请求结束后，将params清空
     */
    private void onRequestFinish() {
        final long delayed = Latte.getConfiguration(ConfigKeys.LOADER_DELAYED);
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                RestCreator.getParams().clear();
                RestCreator.getHeaders().clear();
            }
        }, delayed);
    }
}
