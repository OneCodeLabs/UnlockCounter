package com.unlockchecker.unlockchecker.model;

import java.io.Serializable;

public class Session implements Serializable {

    private long duration;
    private long startedAt;

    public Session(long duration, long startedAt) {
        this.duration = duration;
        this.startedAt = startedAt;
    }

    public long getDuration() {
        return duration;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public String toString() {
        return new StringBuilder()
                .append("SESSION:\n")
                .append("startedAt: " + startedAt + "\n")
                .append("duration: " + duration/1000 + "\n")
                .toString();
    }
}
