package com.cpst.news.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

import sunxl8.myutils.Utils;

/**
 * Created by wy on 2017/9/26.
 *   Application
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //X5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。

            }

            @Override
            public void onViewInitFinished(boolean b) {
                //X5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("wy","加载内核是否成功:"+b);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
