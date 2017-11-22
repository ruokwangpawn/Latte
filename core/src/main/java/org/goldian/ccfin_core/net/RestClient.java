package org.goldian.ccfin_core.net;

import android.content.Context;

import org.goldian.ccfin_core.net.callback.IError;
import org.goldian.ccfin_core.net.callback.IFailure;
import org.goldian.ccfin_core.net.callback.IRequest;
import org.goldian.ccfin_core.net.callback.ISuccess;
import org.goldian.ccfin_core.net.callback.RequestCallback;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Pawn on 2017/11/7 14.
 */

public final class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private static final WeakHashMap<String, String> HEADERS = RestCreator.getHeaders();
    private final String URL;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String url, Map<String, Object> params, Map<String, String> headers,
               String downloadDir, String extension, String name, File file,
               IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError,
               RequestBody body, Context context) {
        this.URL = url;
        HEADERS.putAll(headers);
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.FILE = file;
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.BODY = body;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS, HEADERS);
                break;
            case POST:
                call = service.post(URL, PARAMS, HEADERS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY, HEADERS);
                break;
            case PUT:
                call = service.put(URL, PARAMS, HEADERS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY, HEADERS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS, HEADERS);
                break;
            case UPLOAD:

                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallback(REQUEST, SUCCESS, FAILURE, ERROR);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY != null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

}
