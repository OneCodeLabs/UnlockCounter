package com.unlockchecker.unlockchecker.ui.settings;

import com.unlockchecker.unlockchecker.ui.base.BasePresenter;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;

public class SettingsPresenter
        extends BasePresenter<SettingsContract.View>
        implements SettingsContract.Presenter {

    private static final String SWITCH_STATE_DAILY = "SWITCH_STATE_DAILY";
    private static final String SWITCH_STATE_WEEKLY = "SWITCH_STATE_WEEKLY";
    private static final String SWITCH_STATE_MONTHLY = "SWITCH_STATE_MONTHLY";

    public SettingsPresenter(SettingsContract.View settingsView) {
        super(settingsView);
        populate();
    }

    private void populate() {
        getView().setDailySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_DAILY));
        getView().setWeeklySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_WEEKLY));
        getView().setMonthlySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_MONTHLY));
    }

    @Override
    public void onDailySwitchChecked(boolean checked) {
        SharedPreferencesUtils.save(SWITCH_STATE_DAILY, checked);
    }

    @Override
    public void onWeeklySwitchChecked(boolean checked) {
        SharedPreferencesUtils.save(SWITCH_STATE_WEEKLY, checked);
    }

    @Override
    public void onMonthlySwitchChecked(boolean checked) {
        SharedPreferencesUtils.save(SWITCH_STATE_MONTHLY, checked);
    }

}
