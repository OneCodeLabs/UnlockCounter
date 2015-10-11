package com.unlockchecker.unlockchecker;

import android.app.Application;
import android.content.Context;

public class UnlockCounterApplication extends Application {

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
