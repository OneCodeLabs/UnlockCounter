package com.unlockchecker.unlockchecker.db.impl;

import android.content.Context;
import android.util.Log;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.model.Session;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;

import java.util.List;

public class SugarDB implements UnlockCounterDB {

    public static final String TIMESTAMP = "TIMESTAMP";

    private Context mContext;

    public SugarDB(Context context) {
        mContext = context;
    }

    public void storeTimestamp(long timestamp) {
        SharedPreferencesUtils.getPreferences(mContext)
                .edit()
                .putLong(TIMESTAMP, timestamp)
                .apply();
    }

    public long getTimestamp() {
        return SharedPreferencesUtils.getPreferences(mContext).getLong(TIMESTAMP, 0);
    }

    public void onSessionCompleted(Session session) {
        session.save();
        logAllSessions();
    }

    public List<Session> getAllSessions() {
        return Session.listAll(Session.class);
    }

    public void logAllSessions() {
        for (Session session: getAllSessions()) {
            Log.w(SharedPreferencesDB.TAG, session.toString());
        }
    }
}
