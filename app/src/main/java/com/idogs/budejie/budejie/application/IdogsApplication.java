package com.idogs.budejie.budejie.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 * Created by Idogs on 2017/9/16 0016.
 */

public class IdogsApplication extends Application {
    private static IdogsApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
