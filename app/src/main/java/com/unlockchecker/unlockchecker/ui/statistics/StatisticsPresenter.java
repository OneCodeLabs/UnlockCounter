package com.unlockchecker.unlockchecker.ui.statistics;

import com.unlockchecker.unlockchecker.db.UnlockCounterDB;
import com.unlockchecker.unlockchecker.db.impl.SugarDB;
import com.unlockchecker.unlockchecker.model.event.UsageDataUpdateEvent;
import com.unlockchecker.unlockchecker.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

public class StatisticsPresenter
        extends BasePresenter<StatisticsContract.View>
        implements StatisticsContract.Presenter {

    private static final int STATS_DAYS = 7;

    private final EventBus eventBus;
    private final UnlockCounterDB unlockDb;

    public StatisticsPresenter(StatisticsContract.View view) {
        super(view);
        this.unlockDb = new SugarDB();
        this.eventBus = EventBus.getDefault();
        populate();
    }

    @Override
    public void attachView(StatisticsContract.View view) {
        super.attachView(view);
        eventBus.register(this);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        eventBus.unregister(this);
    }

    private void populate() {
        getView().setAverage(unlockDb.getAverageDuration(STATS_DAYS));
        getView().setStats(Arrays.asList(unlockDb.getSessionsCountByDay(STATS_DAYS)));
    }

    @Subscribe
    public void onDataUpdated(UsageDataUpdateEvent event) {
        populate();
    }

}
