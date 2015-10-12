package com.unlockchecker.unlockchecker.dispatcher.impl;

import android.util.Log;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.db.impl.SharedPreferencesDB;
import com.unlockchecker.unlockchecker.db.impl.SugarDB;
import com.unlockchecker.unlockchecker.dispatcher.UnlockCounterEventDispatcher;
import com.unlockchecker.unlockchecker.model.Session;

public class UnlockCounterEventDispatcherImpl implements UnlockCounterEventDispatcher {

    private UnlockCounterDB unlockCounterDB;

    public UnlockCounterEventDispatcherImpl() {
        unlockCounterDB = new SugarDB();
        initialize();
    }

    @Override
    public void onUnlock() {
        if (unlockCounterDB.getTimestamp() != 0) Log.w(SharedPreferencesDB.TAG, "session lost");
        unlockCounterDB.storeTimestamp(System.currentTimeMillis());
    }

    public void initialize() {
        if (unlockCounterDB.getTimestamp() != 0) return;
        unlockCounterDB.storeTimestamp(System.currentTimeMillis());
    }

    @Override
    public void onLock() {
        if (unlockCounterDB.getTimestamp() == 0) return;
        long timestamp = unlockCounterDB.getTimestamp();
        unlockCounterDB.storeTimestamp(0);
        long duration = System.currentTimeMillis() - timestamp;
        Session session = new Session(duration, timestamp);
        unlockCounterDB.onSessionCompleted(session);
    }
}
