package com.unlockchecker.unlockchecker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.unlockchecker.unlockchecker.service.UnlockCounterService;

public class ScreenUnlockReceiver extends BroadcastReceiver {
    public ScreenUnlockReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, UnlockCounterService.class);
        i.putExtra("screen_state", true);
        context.startService(i);
    }
}
