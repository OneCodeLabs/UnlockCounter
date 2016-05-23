package com.unlockchecker.unlockchecker.ui.base;

import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.helper.NavigationHelper;
import com.unlockchecker.unlockchecker.ui.settings.SettingsView;
import com.unlockchecker.unlockchecker.ui.statistics.StatisticsView;

public class BaseActivity extends AppCompatActivity {

    private NavigationHelper navigationHelper;
    private View statisticsView;
    private View settingsView;

    private Toolbar toolbar;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        navigationHelper = new NavigationHelper(
                this,
                (ViewGroup) findViewById(R.id.activity_container));
        navigationHelper.goTo(getStatisticsView());
        toolbar = new Toolbar(this);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener((v) -> navigationHelper.goBack());
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.statistics_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                navigationHelper.goTo(getSettingsView());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public View getStatisticsView() {
        if (statisticsView == null) {
            statisticsView = new StatisticsView(this);
        }
        return statisticsView;
    }

    public View getSettingsView() {
        if (settingsView == null) {
            settingsView = new SettingsView(this);
        }
        return settingsView;
    }

    public void refreshMenu(@MenuRes int menuRes) {
        if (menu != null) {
            menu.clear();
            getMenuInflater().inflate(menuRes, menu);
        }
    }

    @Override
    public void onBackPressed() {
        navigationHelper.goBack();
    }

}
