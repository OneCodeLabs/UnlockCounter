package com.unlockchecker.unlockchecker.ui.statistics;

import com.unlockchecker.unlockchecker.ui.base.BaseView;
import com.unlockchecker.unlockchecker.ui.base.IPresenter;

import java.util.List;

public interface StatisticsContract {

    interface View extends BaseView {

        /**
         * Shows on the screen the average session duration in the last few days.
         */
        void setAverage(double average);

        /**
         * Shows on the screen the amount of sessions in the last few days.
         */
        void setStats(List<Integer> statsByDay);

    }

    interface Presenter extends IPresenter<View> {

    }

}
