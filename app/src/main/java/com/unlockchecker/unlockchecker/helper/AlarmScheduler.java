package com.unlockchecker.unlockchecker.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.unlockchecker.unlockchecker.Configuration;
import com.unlockchecker.unlockchecker.receiver.DayChangeReceiver;

import org.joda.time.LocalDate;

public class AlarmScheduler {

    private static final String KEY_ALARM_UPDATE = "UnlockChecked.KEY_ALARM_UPDATE";

    /**
     * Sets an alarm for the next midnight. If |forceUpdate| is true, sets an alarm no matter what.
     * Otherwise, it only sets it if it hasn't been set (ie. the {@link #KEY_ALARM_UPDATE} key
     * doesn't exist in SharedPreferences).
     */
    public static void updateAlarm(Context context, boolean forceUpdate) {
        Intent dayChangeIntent = new Intent(context, DayChangeReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, dayChangeIntent, 0);

        // Note that LocalDate rounds to the start of the day, so |updateMillis| would be at the
        // beginning of the following day.
        long updateMillis = LocalDate.now().plusDays(1).toDate().getTime();

        SharedPreferences preferences = context.getSharedPreferences(
                Configuration.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.contains(KEY_ALARM_UPDATE) && !forceUpdate) {
            return;
        }
        preferences.edit().putLong(KEY_ALARM_UPDATE, updateMillis).apply();

        // Set the alarm.
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, updateMillis, pendingIntent);
    }

}

