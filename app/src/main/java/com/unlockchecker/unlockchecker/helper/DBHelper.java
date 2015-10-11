package com.unlockchecker.unlockchecker.helper;

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

public class DBHelper {

    public static final String TAG = "DBHelper";
    public static final String SESSIONS_LIST = "SESSIONS_LIST";

    private Context mContext;

    public DBHelper(Context context) {
        this.mContext = context;
    }

    public void onSessionCompleted(long duration) {
        long startedAt = System.currentTimeMillis() - duration;
        Session session = new Session(duration, startedAt);
        List<Session> sessions = getSessions();
        sessions.add(session);
        storeSessions(sessions);
        Log.w(TAG, "Session completed!");
        logAllSessions();
    }

    public void logAllSessions() {
        for (Session session: getSessions()) {
            Log.w(TAG, session.toString());
        }
    }

    public SharedPreferences getSettings() {
        return mContext.getSharedPreferences(Configuration.SHARED_PREFERENCES,
                Activity.MODE_PRIVATE);
    }

    private List<Session> getSessions() {
        String sessionsJson = getSettings().getString(SESSIONS_LIST, null);
        if (sessionsJson == null) return new ArrayList<Session>();
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Session>>() {}.getType();
        List<Session> sessions = gson.fromJson(sessionsJson, listType);
        return sessions;
    }

    private void storeSessions(List<Session> smokeInfo) {
        Gson gson = new GsonBuilder().create();
        getSettings()
                .edit()
                .putString(SESSIONS_LIST, gson.toJson(smokeInfo))
                .apply();
    }
}
