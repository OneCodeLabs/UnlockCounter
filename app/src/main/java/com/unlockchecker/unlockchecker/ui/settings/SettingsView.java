package com.unlockchecker.unlockchecker.ui.settings;

import android.content.Context;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.ui.base.BaseFL;
import com.unlockchecker.unlockchecker.ui.base.IPresenter;

public class SettingsView extends BaseFL<SettingsContract.Presenter>
        implements SettingsContract.View {

    public SettingsView(Context context) {
        super(context);
    }

    @Override
    protected int layout() {
        return R.layout.settings;
    }

    @Override
    protected IPresenter createPresenter() {
        return new SettingsPresenter(this);
    }

    @Override
    public void setDailySwitch(boolean value) {

    }

    @Override
    public void setWeeklySwitch(boolean value) {

    }

    @Override
    public void setMonthlySwitch(boolean value) {

    }

}
