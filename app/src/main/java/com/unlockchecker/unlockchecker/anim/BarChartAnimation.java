package com.unlockchecker.unlockchecker.anim;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

import com.unlockchecker.unlockchecker.ui.Bar;

import java.util.List;

/**
 * Plays an animation when showing the {@link com.unlockchecker.unlockchecker.ui.BarChart}, where
 * the progress goes from 0 to the target value.
 * TODO(gnardini): Consider either using a proper charting library or refactoring some of this logic
 * so that the {@link Bar} view doesn't have animation logic.
 */
public class BarChartAnimation extends Animation {

    private static final long DURATION = 1000;

    private List<Bar> mBars;

    public BarChartAnimation(List<Bar> bars) {
        mBars = bars;
        setDuration(DURATION);
        setInterpolator(new DecelerateInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        for (Bar bar : mBars) {
            bar.applyAnimTransformation(interpolatedTime);
        }
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}