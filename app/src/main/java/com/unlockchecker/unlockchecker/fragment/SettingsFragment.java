package com.unlockchecker.unlockchecker.fragment;


import android.widget.Switch;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.presenter.SettingsPresenter;
import com.unlockchecker.unlockchecker.presenter.impl.SettingsPresenterImpl;
import com.unlockchecker.unlockchecker.view.SettingsView;

import butterknife.Bind;
import butterknife.OnCheckedChanged;

public class SettingsFragment extends BaseFragment implements SettingsView {

    @Bind(R.id.fragment_settings_daily_switch)
    Switch mDailySwitch;
    @Bind(R.id.fragment_settings_weekly_switch)
    Switch mWeeklySwitch;
    @Bind(R.id.fragment_settings_monthly_switch)
    Switch mMonthlySwitch;

    private SettingsPresenter settingsPresenter;

    public static SettingsFragment instance() {
        SettingsFragment f = new SettingsFragment();
        return f;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void init() {
        settingsPresenter = new SettingsPresenterImpl(this);
    }

    @Override
    protected void populate() {
        settingsPresenter.populate();
    }

    @OnCheckedChanged(R.id.fragment_settings_daily_switch)
    public void onDailySwitchChecked(boolean checked) {
        settingsPresenter.onDailySwitchChecked(checked);
    }

    @OnCheckedChanged(R.id.fragment_settings_weekly_switch)
    public void onWeeklySwitchChecked(boolean checked) {
        settingsPresenter.onWeeklySwitchChecked(checked);
    }

    @OnCheckedChanged(R.id.fragment_settings_monthly_switch)
    public void onMonthlySwitchChecked(boolean checked) {
        settingsPresenter.onMonthlySwitchChecked(checked);
    }
    @Override
    public void setDailySwitch(boolean value) {
        mDailySwitch.setChecked(value);
    }

    @Override
    public void setWeeklySwitch(boolean value) {
        mWeeklySwitch.setChecked(value);
    }

    @Override
    public void setMonthlySwitch(boolean value) {
        mMonthlySwitch.setChecked(value);
    }
}
