package com.unlockchecker.unlockchecker.presenter.impl;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.db.impl.SugarDB;
import com.unlockchecker.unlockchecker.model.event.UsageDataUpdateEvent;
import com.unlockchecker.unlockchecker.presenter.StatisticsPresenter;
import com.unlockchecker.unlockchecker.view.StatisticsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

public class StatisticsPresenterImpl implements StatisticsPresenter {

    private static final int STATS_DAYS = 7;

    private final EventBus eventBus;
    private final UnlockCounterDB unlockDb;
    private StatisticsView statisticsView;

    public StatisticsPresenterImpl(StatisticsView statisticsView) {
        this.statisticsView = statisticsView;
        this.unlockDb = new SugarDB();
        this.eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public void populate() {
        statisticsView.setAverage(unlockDb.getAverageDuration(STATS_DAYS));
        statisticsView.setStats(Arrays.asList(unlockDb.getSessionsCountByDay(STATS_DAYS)));
    }

    @Subscribe
    public void onDataUpdated(UsageDataUpdateEvent event) {
        populate();
    }

    @Override
    public void dettachView() {
        statisticsView = null;
        eventBus.unregister(this);
    }

}
