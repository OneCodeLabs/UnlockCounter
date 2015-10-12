package com.unlockchecker.unlockchecker.activity;

import android.app.Activity;
import android.app.Fragment;
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

    protected void replaceFragment(int resId, Fragment f) {
        getFragmentManager()
                .beginTransaction()
                .replace(resId, f)
                .commit();
    }

    protected void replaceFragment(int resId, Fragment f, String tag) {
        getFragmentManager()
                .beginTransaction()
                .replace(resId, f, tag)
                .commit();
    }
}
