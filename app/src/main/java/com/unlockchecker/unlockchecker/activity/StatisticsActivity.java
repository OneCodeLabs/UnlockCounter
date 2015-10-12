package com.unlockchecker.unlockchecker.activity;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.fragment.StatisticsFragment;
import com.unlockchecker.unlockchecker.presenter.StatisticsPresenter;
import com.unlockchecker.unlockchecker.view.StatisticsView;

public class StatisticsActivity extends BaseActivity {

    @Override
    protected int layout() {
        return R.layout.activity_statistics;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_statistics_fragment_container, StatisticsFragment.instance());
    }

    @Override
    protected void populate() {

    }
}
