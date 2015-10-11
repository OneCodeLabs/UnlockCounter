package com.unlockchecker.unlockchecker.db;

import com.unlockchecker.unlockchecker.model.Session;

import java.util.List;

public interface UnlockCounterDB {

    public void storeTimestamp(long timestamp);
    public long getTimestamp();
    public void onSessionCompleted(Session session);
    public List<Session> getAllSessions();
}
