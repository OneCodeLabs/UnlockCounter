package com.unlockchecker.unlockchecker.db.impl;

import android.util.Log;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.model.Session;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;

import java.util.List;

public class SugarDB implements UnlockCounterDB {

    public static final String TIMESTAMP = "TIMESTAMP";

    public SugarDB() {
    }

    public void storeTimestamp(long timestamp) {
        SharedPreferencesUtils.save(TIMESTAMP, timestamp);
    }

    public long getTimestamp() {
        return SharedPreferencesUtils.getLong(TIMESTAMP);
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
