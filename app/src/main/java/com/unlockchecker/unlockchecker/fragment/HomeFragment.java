package com.unlockchecker.unlockchecker.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.activity.SettingsActivity;
import com.unlockchecker.unlockchecker.activity.StatisticsActivity;
import com.unlockchecker.unlockchecker.presenter.HomePresenter;
import com.unlockchecker.unlockchecker.presenter.impl.HomePresenterImpl;
import com.unlockchecker.unlockchecker.util.NavigationUtils;
import com.unlockchecker.unlockchecker.view.HomeView;

import butterknife.Bind;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements HomeView {

    private HomePresenter homePresenter;

    public static HomeFragment instance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_home;
    }
    @Override
    protected void init() {
        homePresenter = new HomePresenterImpl(this);
    }

    @Override
    protected void populate() {
    }

    @OnClick(R.id.fragment_home_statistics_button)
    public void onStatisticsPressed() {
        homePresenter.onStatisticsPressed();
    }

    @OnClick(R.id.fragment_home_settings_button)
    public void onSettingsPressed() {
        homePresenter.onSettingsPressed();
    }

    @Override
    public void navigateToStatistics() {
        NavigationUtils.jumpTo(getActivity(), StatisticsActivity.class);
    }

    @Override
    public void navigateToSettings() {
        NavigationUtils.jumpTo(getActivity(), SettingsActivity.class);
    }
}
