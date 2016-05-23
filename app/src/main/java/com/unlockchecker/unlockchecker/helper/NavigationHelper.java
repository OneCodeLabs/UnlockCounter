package com.unlockchecker.unlockchecker.helper;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.unlockchecker.unlockchecker.R;
import com.unlockchecker.unlockchecker.ui.base.BaseActivity;
import com.unlockchecker.unlockchecker.ui.base.BaseFL;
import com.unlockchecker.unlockchecker.util.TransitionListenerWrapper;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NavigationHelper {

    public static final int VIEW_TAG = R.id.navigation_id;

    private BaseActivity activity;
    private ViewGroup rootView;
    private final Deque<String> views;
    private final Map<String, View> viewTags;

    public NavigationHelper(BaseActivity activity, ViewGroup rootView) {
        this.activity = activity;
        this.rootView = rootView;
        views = new LinkedList<>();
        viewTags = new HashMap<>();
    }

    public void goBack() {
        views.pop();
        if (views.isEmpty()) {
            rootView = null;
            activity.finish();
            activity = null;
        } else {
            String tag = views.peek();
            setCurrentView(viewTags.get(tag), false);
        }
    }

    public void goTo(View view) {
        Object objectTag = view.getTag(VIEW_TAG);
        if (!(objectTag instanceof String)) {
            throw new IllegalStateException("All views used by the NavigationManager must have a "
                    + "tag with id NavigationManager.VIEW_TAG");
        }
        String viewTag = (String) objectTag;
        if (!views.isEmpty() && views.peek().equals(viewTag)) {
            return;
        }
        viewTags.put(viewTag, view);
        views.push(viewTag);
        setCurrentView(view, true);
    }

    private void setCurrentView(final View view, boolean goingForward) {
        updateMenu(view);
        if (rootView.getChildCount() == 0) {
            rootView.addView(view);
            return;
        }
        final int exitDirection = goingForward ? Gravity.LEFT : Gravity.RIGHT;
        final int enterDirection = goingForward ? Gravity.RIGHT : Gravity.LEFT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide exitSlide = new Slide(exitDirection);
            exitSlide.addListener(new TransitionListenerWrapper() {

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    TransitionManager.beginDelayedTransition(rootView, new Slide(enterDirection));
                    rootView.addView(view);
                }
            });
            TransitionManager.beginDelayedTransition(rootView, exitSlide);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(rootView);
        }
        rootView.removeView(rootView.getChildAt(0));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            rootView.addView(view);
        }
    }

    private void updateMenu(View view) {
        if (view instanceof BaseFL) {
            activity.refreshMenu(((BaseFL) view).getMenuRes());
        }
    }

}
