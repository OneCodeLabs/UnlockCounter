package com.unlockchecker.unlockchecker.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.unlockchecker.unlockchecker.dispatcher.UnlockCounterEventDispatcher;
import com.unlockchecker.unlockchecker.dispatcher.impl.UnlockCounterEventDispatcherImpl;
import com.unlockchecker.unlockchecker.receiver.ScreenOffReceiver;

public class UnlockCounterService extends Service {

    public static final String TAG = "LockService";

    private UnlockCounterEventDispatcher unlockCounterEventDispatcher;
    private BroadcastReceiver mReceiver;
    private boolean initialized;
    private boolean isUnlock;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");
        initialize();
    }

    private void initialize() {
        if (initialized) return;
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        mReceiver = new ScreenOffReceiver();
        registerReceiver(mReceiver, filter);
        unlockCounterEventDispatcher = new UnlockCounterEventDispatcherImpl();
        initialized = true;
        isUnlock = true;
        Log.i(TAG, "Started");
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        Log.i(TAG, "onDestoy()");
        super.onDestroy();
        sendBroadcast(new Intent("YouWillNeverKillMe"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null || !intent.hasExtra("screen_state")) return START_STICKY;
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (screenOn) {
            unlockCounterEventDispatcher.onUnlock();
            isUnlock = true;
        } else {
            if (!isUnlock) return START_STICKY;
            unlockCounterEventDispatcher.onLock();
            isUnlock = false;
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
