package com.unlockchecker.unlockchecker.fragment;


import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.presenter.StatisticsPresenter;
import com.unlockchecker.unlockchecker.presenter.impl.StatisticsPresenterImpl;
import com.unlockchecker.unlockchecker.view.StatisticsView;

public class StatisticsFragment extends BaseFragment implements StatisticsView {

    private StatisticsPresenter statisticsPresenter;

    public static StatisticsFragment instance() {
        StatisticsFragment f = new StatisticsFragment();
        return f;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void init() {
        statisticsPresenter = new StatisticsPresenterImpl(this);
    }

    @Override
    protected void populate() {

    }
}
