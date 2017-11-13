package org.goldian.ccfin_core.net;

import android.content.Context;

import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.IRequest;
import org.goldian.ccfin_core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by Pawn on 2017/9/27 18.
 */

public final class RestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder onSuccess(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder onFailure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder onError(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mDownloadDir, mExtension, mName, mFile,
                mIRequest, mISuccess, mIFailure, mIError,
                mBody, mContext);
    }

}
