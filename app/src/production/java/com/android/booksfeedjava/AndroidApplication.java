package com.android.booksfeedjava;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

@SuppressWarnings("ALL")
public class AndroidApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    public static Context getAppContext() {
        return AndroidApplication.sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (AndroidApplication.class) {
            sContext = getApplicationContext();
        }
    }

}
