package com.unlockchecker.unlockchecker.db.impl;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.model.Report;
import com.unlockchecker.unlockchecker.model.Session;
import com.unlockchecker.unlockchecker.model.event.UsageDataUpdateEvent;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Implementation of {@link UnlockCounterDB} that stores all lock and unlock events using SugarORM.
 */
public class SugarDB implements UnlockCounterDB {

    public static final String TIMESTAMP = "TIMESTAMP";

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
        session.save();
    }

    @Override
    public double getAverageDuration(int days) {
        // To calculate the average duration of a session, add the total count of sessions
        // (including those on reports) and their durations and divide one by the other.
        List<Session> sessions = Session.listAll(Session.class);
        int totalCount = sessions.size();
        long totalDuration = 0;
        for (Session session : sessions) {
            totalDuration += session.getDuration();
        }
        List<Report> reports = getReports(days);
        for (Report report : reports) {
            totalCount += report.getCount();
            totalDuration += report.getTotalDuration();
        }
        if (totalCount == 0) {
            return 0;
        }
        return (((double) totalDuration) / totalCount) / 1000;
    }

    @Override
    public Integer[] getSessionsCountByDay(int days) {
        // Initialize the sessions array.
        Integer[] sessionCount = new Integer[days];
        for (int i = 0 ; i < sessionCount.length ; i++) {
            sessionCount[i] = 0;
        }
        long currentTime = LocalDate.now().toDate().getTime();
        for (Report report: getReports(days)) {
            // Note that reports are saved at the end of a day, so
            // |(currentTime - report.getDate) / MILLIS_PER_DAY| will return 0 for the current day.
            // However, that value belongs to the previous day, hence the +1 at the end.
            // As an example, imagine it's 11 am on a day. |currentTime - report.getDate| will
            // return 11 hours in millis. That divided by 24 hours in millis returns 0. That +1 is
            // 1. That means that the report saved at midnight belongs to 1 day ago, which is
            // correct.
            int daysAgo = (int) ((currentTime - report.getDate())
                    / DateTimeConstants.MILLIS_PER_DAY) + 1;
            int reportIndex = days - 1 - daysAgo;
            // Paranoid check. Avoid unnecessary crashes.
            if (reportIndex >= 0) {
                sessionCount[reportIndex] = report.getCount();
            }
        }
        // The count on the last that is the count of sessions on the current day.
        sessionCount[days - 1] = getAllSessions().size();
        return sessionCount;
    }

    public List<Report> getReports(int days) {
        // Subtract one hour to avoid possible border case issues. It's probably unnecessary but
        // it doesn't hurt to be extra careful just in case.
        long targetTime = LocalDate.now().minusDays(days).minus(Hours.ONE).toDate().getTime();
        return Select.from(Report.class)
                .where(Condition.prop("date").gt(targetTime))
                .list();
    }


    public List<Session> getAllSessions() {
        return Session.listAll(Session.class);
    }

    @Override
    public void onDayChanged() {
        List<Session> sessions = Session.listAll(Session.class);
        long date = LocalDate.now().toDate().getTime();
        int sessionsCount = sessions.size();
        long sessionsTotalDuration = 0;
        for (Session session: sessions) {
            sessionsTotalDuration += session.getDuration();
        }
        // Create and save the new report.
        Report newReport = new Report(date, sessionsCount, sessionsTotalDuration);
        newReport.save();
        // Delete all sessions to avoid repeating them.
        Session.deleteAll(Session.class);
        // Post an event so that the UI can be updated if necessary.
        EventBus.getDefault().post(new UsageDataUpdateEvent());
    }

    @Override
    public void checkDayChange() {
        List<Session> sessions = getAllSessions();
        // If there are no sessions, there's no need to generate a report.
        if (sessions.size() == 0) {
            return;
        }
        Session someSession = sessions.get(0);
        // If the session belongs to a day different than the current one, generate a report with
        // all of the sessions.
        if (!isTodaysDate(someSession.getStartedAt() + someSession.getDuration())) {
            onDayChanged();
        }
    }

    /**
     * Returns true if |timeInMillis| represents a time in epoch milliseconds corresponding with
     * the current date.
     */
    private boolean isTodaysDate(long timeInMillis) {
        LocalDate timesDate = new LocalDate(timeInMillis);
        return timesDate.isEqual(LocalDate.now());
    }

    /**
     * Helper method that logs all of the sessions stored in the database.
     */
    public void logAllSessions() {
        for (Session session: getAllSessions()) {
            Log.w(SharedPreferencesDB.TAG, session.toString());
        }
    }

}
