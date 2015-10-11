package com.unlockchecker.unlockchecker.activity;

import android.content.Intent;

import com.unlockchecker.unlockchecker.service.UnlockCounterService;
import com.unlockchecker.unlockchecker.R;


public class MainActivity extends BaseActivity {

    @Override
    protected int layout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        startService(new Intent(MainActivity.this, UnlockCounterService.class));
    }

    @Override
    protected void populate() {

    }
}
