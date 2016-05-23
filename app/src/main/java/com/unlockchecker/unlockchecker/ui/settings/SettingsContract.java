package com.unlockchecker.unlockchecker.ui.settings;

import com.unlockchecker.unlockchecker.ui.base.BaseView;
import com.unlockchecker.unlockchecker.ui.base.IPresenter;

public class SettingsContract {

    interface View extends BaseView {

        void setDailySwitch(boolean value);
        void setWeeklySwitch(boolean value);
        void setMonthlySwitch(boolean value);

    }

    interface Presenter extends IPresenter<View> {

        void onDailySwitchChecked(boolean checked);
        void onWeeklySwitchChecked(boolean checked);
        void onMonthlySwitchChecked(boolean checked);

    }

}
