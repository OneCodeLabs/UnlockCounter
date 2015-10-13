package com.unlockchecker.unlockchecker.db;

import com.unlockchecker.unlockchecker.model.Session;

import java.util.List;

/**
 * Handles and saves all events involving phone lock and unlock events.
 */
public interface UnlockCounterDB {

    // Stores the time when the phone was last unlocked.
    void storeTimestamp(long timestamp);
    long getTimestamp();

    // Stores a completed session. Called when the phone is locked.
    void onSessionCompleted(Session session);

    // Returns a list of all of the current days sessions.
    List<Session> getAllSessions();

    // Returns the average session duration in the last |days| days.
    double getAverageDuration(int days);

    // Returns an ordered array with the number of sessions each day for the last |days| days.
    Integer[] getSessionsCountByDay(int days);

    // Generates and saves a report based on all of the current day's sessions and deletes them.
    void onDayChanged();

    // Checks if the day has changed since the last stored session and calls |onDayChanged| if
    // that's the case.
    void checkDayChange();

}
