package com.mengb.mymvpapplication.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by dingchao
 */

public class AppApplication extends Application {
    private static AppApplication application;
    private static Context mContext;

    private String cardNum;
    ArrayList<Activity> list = new ArrayList<Activity>();

//    private SignBean signBean;

    private String versionName;

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * 初始化配置
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static AppApplication getApp() {
        if (application != null && application instanceof AppApplication) {
            return application;
        } else {
            application = new AppApplication();
            application.onCreate();
            return application;
        }
    }


    public static Context getmContext() {
        return mContext;
    }

}