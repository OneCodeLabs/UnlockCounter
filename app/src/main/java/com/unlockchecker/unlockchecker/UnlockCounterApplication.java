package com.unlockchecker.unlockchecker;

import android.content.Context;

import com.orm.SugarApp;
import com.unlockchecker.unlockchecker.helper.AlarmScheduler;

import net.danlew.android.joda.JodaTimeAndroid;

public class UnlockCounterApplication extends SugarApp {

    private static Context sContext;

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        JodaTimeAndroid.init(this);
        setupAlarm();
    }

    private void setupAlarm() {
        AlarmScheduler.updateAlarm(
                this,   // Context.
                false); // Force update. Passing in false means this won't create a new alarm if one
                        // has already been set.
    }

}
