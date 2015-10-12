package com.unlockchecker.unlockchecker.presenter;

public interface SettingsPresenter extends Presenter {

    public void onDailySwitchChecked(boolean checked);
    public void onWeeklySwitchChecked(boolean checked);
    public void onMonthlySwitchChecked(boolean checked);
}
