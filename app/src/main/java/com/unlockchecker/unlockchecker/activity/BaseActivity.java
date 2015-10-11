package com.unlockchecker.unlockchecker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

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

    protected abstract @LayoutRes int layout();

    protected abstract void init();

    protected abstract void populate();
}
