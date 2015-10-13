package com.unlockchecker.unlockchecker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.unlockchecker.unlockchecker.helper.ReportManager;
import com.unlockchecker.unlockchecker.service.UnlockCounterService;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent unlockIntent = new Intent(context, UnlockCounterService.class);
        context.startService(unlockIntent);
        ReportManager.generateReport(false);
    }

}
