package com.unlockchecker.unlockchecker.ui.statistics;


import android.content.Context;
import android.widget.TextView;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.ui.BarChart;
import com.unlockchecker.unlockchecker.ui.base.BaseFL;
import com.unlockchecker.unlockchecker.ui.base.IPresenter;

import java.util.List;

/**
 * This View shows the statistics of the last 7 days.
 * TODO(gnardini):
 *  - Consider using a charting library rather than the home-made BarChart.
 *  - Show the seconds in a nice manner rather than just seconds (ie, 100 seconds should show 1
 *      minute 40 secs, not 100 seconds).
 */
public class StatisticsView extends BaseFL<StatisticsContract.Presenter>
        implements StatisticsContract.View {

    private static final String AVERAGE = "Average: %.02f";

    private TextView averageDurationTv;
    private BarChart barChart;

    public StatisticsView(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.statistics;
    }

    @Override
    protected IPresenter createPresenter() {
        return new StatisticsPresenter(this);
    }

    @Override
    protected void setUi() {
        super.setUi();
        averageDurationTv = (TextView) findViewById(R.id.statistics_average);
        barChart = (BarChart) findViewById(R.id.statistics_bar_chart);
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
    public int getMenuRes() {
        return R.menu.statistics_menu;
    }

}
