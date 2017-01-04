package cn.cyan.sample.base;

import android.app.Application;
import android.content.Context;

/**
 * User : Cyan(baocq@maritech.cn)
 * Date : 2017/1/4
 */
public class SampleApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
