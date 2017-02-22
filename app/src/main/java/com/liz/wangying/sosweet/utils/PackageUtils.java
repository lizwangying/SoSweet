package com.liz.wangying.sosweet.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * desc:
 * Created by Liz on 2017/2/21.
 * github: https://github.com/lizwangying
 */

public class PackageUtils {

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            PackageManager pm = context.getPackageManager();
            packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static String getAppVersionName(Context context) {
        if(context != null) {
            PackageManager pm = context.getPackageManager();
            if(pm != null) {
                try {
                    PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                    if(pi != null) {
                        return pi.versionName;
                    }
                } catch (PackageManager.NameNotFoundException var4) {
                    var4.printStackTrace();
                }
            }
        }

        return "";
    }

}
