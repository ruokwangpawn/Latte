package com.pawn.latte;

import android.app.Application;

import org.goldian.ccfin_core.app.Latte;

/**
 * Created by Pawn on 2017/11/3 15.
 */

public class LatteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost("http://wwwtest.99love.com/")
                .withLoaderDelayed(1000)
                .configure();
    }
}
