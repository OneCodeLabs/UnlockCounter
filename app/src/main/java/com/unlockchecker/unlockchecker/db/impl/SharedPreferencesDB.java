package com.unlockchecker.unlockchecker.db.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.model.Session;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesDB implements UnlockCounterDB {

    public static final String TAG = "DBHelper";
    public static final String SESSIONS_LIST = "SESSIONS_LIST";
    public static final String TIMESTAMP = "TIMESTAMP";

    public SharedPreferencesDB() {
    }

    public void logAllSessions() {
        for (Session session: getAllSessions()) {
            Log.w(TAG, session.toString());
        }
    }

    private void storeSessions(List<Session> sessions) {
        Gson gson = new GsonBuilder().create();
        SharedPreferencesUtils.save(SESSIONS_LIST, gson.toJson(sessions));
    }

    @Override
    public void storeTimestamp(long timestamp) {
        SharedPreferencesUtils.save(TIMESTAMP, timestamp);
    }

    @Override
    public long getTimestamp() {
        return SharedPreferencesUtils.getLong(TIMESTAMP);
    }

    @Override
    public void onSessionCompleted(Session session) {
        List<Session> sessions = getAllSessions();
        sessions.add(session);
        storeSessions(sessions);
        logAllSessions();
    }

    @Override
    public List<Session> getAllSessions() {
        String sessionsJson = SharedPreferencesUtils.getString(SESSIONS_LIST);
        if (sessionsJson == null) return new ArrayList<Session>();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Session>>() {}.getType();
        List<Session> sessions = gson.fromJson(sessionsJson, listType);
        return sessions;
    }

    @Override
    public double getAverageDuration(int days) {
        // TODO(gnardini): Implement or delete class.
        return 0;
    }

    @Override
    public Integer[] getSessionsCountByDay(int days) {
        // TODO(gnardini): Implement or delete class.
        return new Integer[0];
    }

    @Override
    public void onDayChanged() {
        // TODO(gnardini): Implement or delete class.
    }

    @Override
    public void checkDayChange() {
        // TODO(gnardini): Implement or delete class.
    }

}
