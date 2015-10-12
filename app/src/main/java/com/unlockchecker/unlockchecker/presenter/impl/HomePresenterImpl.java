package com.unlockchecker.unlockchecker.presenter.impl;

import com.unlockchecker.unlockchecker.presenter.HomePresenter;
import com.unlockchecker.unlockchecker.view.HomeView;

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onStatisticsPressed() {
        homeView.navigateToStatistics();
    }

    @Override
    public void onSettingsPressed() {
        homeView.navigateToSettings();
    }
}
