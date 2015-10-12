package com.unlockchecker.unlockchecker.presenter.impl;

import com.unlockchecker.unlockchecker.presenter.SettingsPresenter;
import com.unlockchecker.unlockchecker.view.SettingsView;

public class SettingsPresenterImpl implements SettingsPresenter {

    private SettingsView settingsView;

    public SettingsPresenterImpl(SettingsView settingsView) {
        this.settingsView = settingsView;
    }
}
