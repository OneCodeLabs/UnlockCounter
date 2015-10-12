package com.unlockchecker.unlockchecker;

import android.content.Context;

import com.orm.SugarApp;

public class UnlockCounterApplication extends SugarApp {

    private static Context sContext;

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
