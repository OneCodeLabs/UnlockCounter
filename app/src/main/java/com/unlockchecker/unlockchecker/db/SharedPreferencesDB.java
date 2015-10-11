package com.unlockchecker.unlockchecker.db;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.unlockchecker.unlockchecker.Configuration;
import com.unlockchecker.unlockchecker.model.Session;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesDB implements UnlockCounterDB {

    public static final String TAG = "DBHelper";
    public static final String SESSIONS_LIST = "SESSIONS_LIST";
    public static final String TIMESTAMP = "TIMESTAMP";

    private Context mContext;

    public SharedPreferencesDB(Context context) {
        this.mContext = context;
    }

    public void logAllSessions() {
        for (Session session: getAllSessions()) {
            Log.w(TAG, session.toString());
        }
    }

    public SharedPreferences getSettings() {
        return mContext.getSharedPreferences(Configuration.SHARED_PREFERENCES,
                Activity.MODE_PRIVATE);
    }

    private void storeSessions(List<Session> smokeInfo) {
        Gson gson = new GsonBuilder().create();
        getSettings()
                .edit()
                .putString(SESSIONS_LIST, gson.toJson(smokeInfo))
                .apply();
    }

    @Override
    public void storeTimestamp(long timestamp) {
        getSettings()
                .edit()
                .putLong(TIMESTAMP, timestamp)
                .apply();
    }

    @Override
    public long getTimestamp() {
        return getSettings().getLong(TIMESTAMP, 0);
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
        String sessionsJson = getSettings().getString(SESSIONS_LIST, null);
        if (sessionsJson == null) return new ArrayList<Session>();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Session>>() {}.getType();
        List<Session> sessions = gson.fromJson(sessionsJson, listType);
        return sessions;
    }
}
