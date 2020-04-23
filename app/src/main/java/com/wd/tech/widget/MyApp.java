package com.wd.tech.widget;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:全局类
 */
public class MyApp extends Application {
    public static Context mContext;
    public static IWXAPI mWxApi;
    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        registToWX();
    }
    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, "wx4c96b6b8da494224", true);
        // 将该app注册到微信
        mWxApi.registerApp("wx4c96b6b8da494224");
    }
}
