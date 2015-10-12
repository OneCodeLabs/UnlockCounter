package com.unlockchecker.unlockchecker.activity;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.fragment.SettingsFragment;
import com.unlockchecker.unlockchecker.view.SettingsView;

public class SettingsActivity extends BaseActivity {
    @Override
    protected int layout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_settings_fragment_container, SettingsFragment.instance());
    }

    @Override
    protected void populate() {

    }
}
