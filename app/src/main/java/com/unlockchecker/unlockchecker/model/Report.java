package com.unlockchecker.unlockchecker.model;

import com.orm.SugarRecord;

public class Report extends SugarRecord {

    // Note that the name of this field, 'date' is being used to reference it on the SugarDB class.
    // If it's changed here, remember to change it there as well.
    private long date;
    private int count;
    private long totalDuration;

    public Report() {
    }

    public Report(long date, int count, long totalDuration) {
        this.date = date;
        this.count = count;
        this.totalDuration = totalDuration;
    }

    public long getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public long getTotalDuration() {
        return totalDuration;
    }
}
