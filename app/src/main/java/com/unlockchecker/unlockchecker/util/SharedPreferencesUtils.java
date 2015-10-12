package com.unlockchecker.unlockchecker.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.unlockchecker.unlockchecker.Configuration;
import com.unlockchecker.unlockchecker.UnlockCounterApplication;

public class SharedPreferencesUtils {

    public static void save(String key, boolean value) {
        edit().putBoolean(key, value).apply();
    }

    public static void save(String key, String value) {
        edit().putString(key, value).apply();
    }

    public static void save(String key, long value) {
        edit().putLong(key, value).apply();
    }

    public static String getString(String key) {
        return getPreferences().getString(key, null);
    }

    public static boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, true);
    }

    public static long getLong(String key) {
        return getPreferences().getLong(key, 0);
    }

    private static SharedPreferences.Editor edit() {
        return getPreferences().edit();
    }

    private static Context context() {
        return UnlockCounterApplication.getAppContext();
    }

    private static SharedPreferences getPreferences() {
        return context().getSharedPreferences(Configuration.SHARED_PREFERENCES,
                Activity.MODE_PRIVATE);
    }
}

