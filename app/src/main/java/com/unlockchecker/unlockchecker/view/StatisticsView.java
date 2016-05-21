package com.unlockchecker.unlockchecker.view;

import java.util.List;

public interface StatisticsView {

    /**
     * Shows on the screen the average session duration in the last few days.
     */
    void setAverage(double average);

    /**
     * Shows on the screen the amount of sessions in the last few days.
     */
    void setStats(List<Integer> statsByDay);

}
