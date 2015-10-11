package com.unlockchecker.unlockchecker.activity;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
        ButterKnife.bind(this);
        init();
        populate();
    }

    protected abstract int layout();

    protected abstract void init();

    protected abstract void populate();
}
