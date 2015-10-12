package com.unlockchecker.unlockchecker.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.unlockchecker.unlockchecker.Configuration;

public class SharedPreferencesUtils {

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(Configuration.SHARED_PREFERENCES,
                Activity.MODE_PRIVATE);
    }
}

