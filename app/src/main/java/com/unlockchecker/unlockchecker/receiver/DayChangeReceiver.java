package com.unlockchecker.unlockchecker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.unlockchecker.unlockchecker.helper.AlarmScheduler;
import com.unlockchecker.unlockchecker.helper.ReportManager;

/**
 * Receiver called when the day changes. It's set up on the {@link AlarmScheduler} class.
 */
public class DayChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ReportManager.generateReport(true);
        AlarmScheduler.updateAlarm(context, true);
    }

}
