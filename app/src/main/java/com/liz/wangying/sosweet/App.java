package com.fxiaodi.and;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.liz.wangying.sosweet.CrashHandler;
import com.liz.wangying.sosweet.data.ConstantData;
import com.liz.wangying.sosweet.data.Constants;
import com.liz.wangying.sosweet.utils.PackageUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 自定义application入口
 */

public class App extends Application {
    private static App instance;
    private static List<Activity> activitys = null; //activity实例集合
    private Map<String, ConstantData> constantDataMap; // 已key为键保存起来的常量数据集合

    public App() {
        activitys = new LinkedList<>();
    }

    // 单例模式中获取唯一的MyApplication实例
    public static App getInstance() {
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        instance = this;
        activitys = new LinkedList<>();
        initJpush();
        initTCAgent();
    }

    private void initJpush() {
//        JPushInterface.init(this);
//        JPushInterface.setDebugMode(BuildConfig.DEBUG);
    }

    private void initTCAgent() {
//        TCAgent.LOG_ON = true;
//        TCAgent.init(this);
    }


    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null) {
            if (activitys.isEmpty()) {
                activitys.add(activity);
            } else if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        }
    }

    // 移除Activity到容器中
    public void removeActivity(Activity activity) {
        if (activitys != null && activitys.contains(activity)) {
            activitys.remove(activity);
        }
    }


    public Map<String, ConstantData> getConstantDataMap() {
        return constantDataMap;
    }

    public void setConstantDataMap(Map<String, ConstantData> constantDataMap) {
        this.constantDataMap = constantDataMap;
    }

    /**
     * 完全退出
     */
    public void exit() {
        //activity组件finish
        if (activitys != null && !activitys.isEmpty()) {
            for (Activity activity : activitys) {
                if (activity != null) {
                    activity.finish();
                }
            }
        }
        //完全退出
        System.exit(0);
    }

    /**
     * 是否版本升级
     *
     * @param mContext
     * @return true表示升级，false表示没有升级
     */
//    public boolean isUpgrade(Context mContext) {
//        String versionPre = Preferenceutils.getString(mContext, Constants.SP_VERSION, null);
//        String versionNow = PackageUtils.getAppVersionName(mContext);
//        return !versionNow.equals(versionPre);
//    }
}
