package org.goldian.ccfin_core.net;

import org.goldian.ccfin_core.app.ConfigKeys;
import org.goldian.ccfin_core.app.Latte;
import org.goldian.ccfin_core.net.rx.RxRestService;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Pawn on 2017/9/27 10.
 */

public final class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    /**
     * Header容器
     */
    private static final class HeadersHolder {
        private static final WeakHashMap<String, String> HEADERS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,String> getHeaders() {
        return HeadersHolder.HEADERS;
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
//        private static final List<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addIntercetor() {
//            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
//                for (Interceptor interceptor : INTERCEPTORS) {
//                    BUILDER.addInterceptor(interceptor);
//                }
//            }
            return BUILDER;
        }

        private static final OkHttpClient OKHTTP_CLIENT = addIntercetor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .build();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OKHTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService RX_REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.RX_REST_SERVICE;
    }

}
