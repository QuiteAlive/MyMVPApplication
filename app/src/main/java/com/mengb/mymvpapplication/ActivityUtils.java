package com.mengb.mymvpapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Activity 工具类
 */
public class ActivityUtils {

    private static long exitTime = 0;

    /**
     * 从这个页面跳到另一个页面
     * @param newcontext
     * @param oldcontext
     */
    public static void StartOtherIntent(Context oldcontext,Class newcontext,String url){
        Intent intent = new Intent(oldcontext,newcontext);
        intent.putExtra("url",url);
        oldcontext.startActivity(intent);

       // oldcontext.startActivity(new Intent(oldcontext,newcontext));

    }

    /**
     *  跳转到指定网址
     * @param context
     * @param url
     */
    public static void StartURLIntent(Context context,String url){
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
    /**
     * 作用如：2s内双击两次返回则退出程序
     *
     * @return 是否退出程序
     */
    public static boolean exitBy2Click() {
        return exitBy2Click(2000);
    }

    /**
     * 在某个时间段内双击两次
     *
     * @param space 两次点击最大时间间隔
     * @return 是否退出
     */
    public static boolean exitBy2Click(int space) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }

}
