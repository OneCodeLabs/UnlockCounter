package com.unlockchecker.unlockchecker.fragment;


import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.presenter.SettingsPresenter;
import com.unlockchecker.unlockchecker.presenter.impl.SettingsPresenterImpl;
import com.unlockchecker.unlockchecker.view.SettingsView;

public class SettingsFragment extends BaseFragment implements SettingsView {

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

    }
}
