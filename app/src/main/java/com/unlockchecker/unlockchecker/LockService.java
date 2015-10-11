package com.unlockchecker.unlockchecker;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class LockService extends Service {

    private static final String LOCK_SERVICE_TEST = "LOCK_SERVICE_TEST";

    DBHelper dbHelper;
    BroadcastReceiver mReceiver;
    private int sessions;
    private long lastUnlock;

    private boolean isUnlocked;
    private boolean initialized;

    @Override
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic
        Log.i("UpdateService", "Started");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ScreenOffReceiver();
        registerReceiver(mReceiver, filter);

        dbHelper = new DBHelper(this);
        lastUnlock = System.currentTimeMillis();
        isUnlocked = true;
        initialized = true;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        Log.i("onDestroy Reciever", "Called");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null || !initialized || !intent.hasExtra("screen_state")) return START_STICKY;
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (screenOn) {
            isUnlocked = true;
            lastUnlock = System.currentTimeMillis();
        } else {
            if (!isUnlocked) return START_STICKY;
            sessions++;
            isUnlocked = false;
            dbHelper.onSessionCompleted(System.currentTimeMillis() - lastUnlock);
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private boolean isLocked() {
        KeyguardManager myKM = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        return myKM.inKeyguardRestrictedInputMode();
    }
}
