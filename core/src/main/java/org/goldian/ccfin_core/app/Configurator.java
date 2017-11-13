package org.goldian.ccfin_core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Pawn on 2017/9/27 10.
 */

public final class Configurator {

    private static final HashMap<Object, Object> CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final List<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final HashMap<Object, Object> getConfigs() {
        return CONFIGS;
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptors(List<Interceptor> interceptors) {
        CONFIGS.put(ConfigKeys.INTERCEPTOR, interceptors);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withQQAppId(String appId) {
        CONFIGS.put(ConfigKeys.QQ_APP_ID, appId);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public final Configurator withJavascriptInterface(@NonNull String name) {
        CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " is null");
        }
        return (T) value;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
}
