package com.unlockchecker.unlockchecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOnReceiver extends BroadcastReceiver {
    public ScreenOnReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LockService.class);
        i.putExtra("screen_state", true);
        context.startService(i);
    }
}
