package com.unlockchecker.unlockchecker.presenter.impl;

import com.unlockchecker.unlockchecker.presenter.StatisticsPresenter;
import com.unlockchecker.unlockchecker.view.StatisticsView;

public class StatisticsPresenterImpl implements StatisticsPresenter {

    private StatisticsView statisticsView;

    public StatisticsPresenterImpl(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
    }
}
