package com.unlockchecker.unlockchecker.ui.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.helper.NavigationHelper;

public abstract class BaseFL<T extends IPresenter> extends FrameLayout implements BaseView {

    private IPresenter presenter;

    public BaseFL(Context context) {
        super(context);
        initialize();
    }

    public BaseFL(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public BaseFL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public BaseFL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), layout(), this);
        setUi();
        presenter = createPresenter();
        setTag(NavigationHelper.VIEW_TAG, getTagName());
        init();
    }

    protected abstract @LayoutRes int layout();

    protected void setUi() {
        // Empty default implementation.
    }

    protected abstract IPresenter createPresenter();

    private String getTagName() {
        return getClass().getName();
    }

    protected void init() {
        // Empty default implementation.
    }

    public IPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dettachView();
        super.onDetachedFromWindow();
    }

    public @MenuRes int getMenuRes() {
        return R.menu.empty_menu;
    }

}
