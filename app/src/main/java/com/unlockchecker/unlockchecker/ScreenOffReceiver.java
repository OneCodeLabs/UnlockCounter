package com.unlockchecker.unlockchecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenOffReceiver extends BroadcastReceiver {

    private static final String TAG = "BROADCASTRECEIVERTEST";

    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
//            screenOff = true;
//            // Log.i("via Receiver","Normal ScreenOFF" );
//        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
//            screenOff = false;
//        } else if(intent.getAction().equals(Intent.ACTION_ANSWER)) {
//
//        }
//
        Log.w(DBHelper.TAG, "received screenOff");
        Intent i = new Intent(context, LockService.class);
        i.putExtra("screen_state", false);
        context.startService(i);
    }
}
