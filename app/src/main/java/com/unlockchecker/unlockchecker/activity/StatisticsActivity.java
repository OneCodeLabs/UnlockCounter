package com.unlockchecker.unlockchecker.activity;

import android.widget.TextView;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.presenter.StatisticsPresenter;
import com.unlockchecker.unlockchecker.presenter.impl.StatisticsPresenterImpl;
import com.unlockchecker.unlockchecker.ui.BarChart;
import com.unlockchecker.unlockchecker.view.StatisticsView;

import java.util.List;

import butterknife.Bind;

/**
 * This Activity shows the statistics of the last 7 days.
 * TODO(gnardini):
 *  - Consider using a charting library rather than the home-made BarChart.
 *  - Show the seconds in a nice manner rather than just seconds (ie, 100 seconds should show 1
 *      minute 40 secs, not 100 seconds).
 */
public class StatisticsActivity extends BaseActivity implements StatisticsView {

    private static final String AVERAGE = "Average: %.02f";

    @Bind(R.id.statistics_average)
    TextView averageDurationTv;

    @Bind(R.id.statistics_bar_chart)
    BarChart barChart;

    private StatisticsPresenter statisticsPresenter;

    @Override
    protected int layout() {
        return R.layout.statistics_activity;
    }

    @Override
    protected void init() {
        statisticsPresenter = new StatisticsPresenterImpl(this);
    }

    @Override
    protected void populate() {
        statisticsPresenter.populate();
    }

    @Override
    public void setAverage(double average) {
        averageDurationTv.setText(String.format(AVERAGE, average));
    }

    @Override
    public void setStats(List<Integer> statsByDay) {
        barChart.setBarData(statsByDay);
    }

    @Override
    protected void onDestroy() {
        statisticsPresenter.dettachView();
        super.onDestroy();
    }
}
