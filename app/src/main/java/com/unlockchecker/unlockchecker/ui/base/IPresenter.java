package com.unlockchecker.unlockchecker.ui.base;

public interface IPresenter<T extends BaseView> {

    void attachView(T view);

    void dettachView();

}
