package com.unlockchecker.unlockchecker.dispatcher;

import android.content.Context;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.db.SharedPreferencesDB;
import com.unlockchecker.unlockchecker.model.Session;

public class UnlockCounterEventDispatcherImpl implements UnlockCounterEventDispatcher {

    private UnlockCounterDB unlockCounterDB;

    public UnlockCounterEventDispatcherImpl(Context context) {
        unlockCounterDB = new SharedPreferencesDB(context);
    }

    @Override
    public void onUnlock() {
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
