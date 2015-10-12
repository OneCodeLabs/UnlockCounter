package com.unlockchecker.unlockchecker.activity;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_home_fragment_container, HomeFragment.instance());
    }

    @Override
    protected void populate() {

    }
}
