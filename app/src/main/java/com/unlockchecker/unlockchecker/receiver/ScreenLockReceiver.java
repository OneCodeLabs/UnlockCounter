package com.unlockchecker.unlockchecker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.unlockchecker.unlockchecker.service.UnlockCounterService;

public class ScreenLockReceiver extends BroadcastReceiver {
    public ScreenLockReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, UnlockCounterService.class);
        i.putExtra("screen_state", true);
        context.startService(i);
    }
}
