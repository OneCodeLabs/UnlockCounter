package com.unlockchecker.unlockchecker.presenter.impl;

import com.unlockchecker.unlockchecker.presenter.SettingsPresenter;
import com.unlockchecker.unlockchecker.util.SharedPreferencesUtils;
import com.unlockchecker.unlockchecker.view.SettingsView;

public class SettingsPresenterImpl implements SettingsPresenter {

    private static final String SWITCH_STATE_DAILY = "SWITCH_STATE_DAILY";
    private static final String SWITCH_STATE_WEEKLY = "SWITCH_STATE_WEEKLY";
    private static final String SWITCH_STATE_MONTHLY = "SWITCH_STATE_MONTHLY";

    private SettingsView settingsView;

    public SettingsPresenterImpl(SettingsView settingsView) {
        this.settingsView = settingsView;
    }

    @Override
    public void populate() {
        settingsView.setDailySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_DAILY));
        settingsView.setWeeklySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_WEEKLY));
        settingsView.setMonthlySwitch(SharedPreferencesUtils.getBoolean(SWITCH_STATE_MONTHLY));
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
