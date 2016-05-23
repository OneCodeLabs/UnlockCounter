package com.unlockchecker.unlockchecker.ui.base;

public abstract class BasePresenter<T extends BaseView> implements IPresenter<T> {

    private T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void dettachView() {
        view = null;
    }

}
