package com.unlockchecker.unlockchecker.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unlockchecker.unlockchecker.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Used with {@link BarChart}. See that class for details.
 */
public class Bar extends LinearLayout {

    private static final int MAX_AMOUNT = 20;

    @Bind(R.id.bar_background)
    View mBackground;

    @Bind(R.id.bar_foreground)
    View mForeGround;

    @Bind(R.id.bar_smokes)
    TextView mCount;

    private float mWeight;

    public Bar(Context context) {
        super(context);
        initView(context);
    }

    public Bar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Bar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bar, this);
        ButterKnife.bind(this, view);
        setOrientation(VERTICAL);
    }

    public void setBarData(int smokes, int space, float weight, boolean showNumber) {
        mCount.setText((smokes == 0 || !showNumber) ? "" : String.valueOf(smokes));
        mWeight = Math.min(1, (float) smokes / MAX_AMOUNT);

        LinearLayout.LayoutParams backgroundParams =
                (LinearLayout.LayoutParams) mBackground.getLayoutParams();
        backgroundParams.height = 0;
        backgroundParams.weight = 1;
        mBackground.setLayoutParams(backgroundParams);

        LinearLayout.LayoutParams barParams = (LinearLayout.LayoutParams) getLayoutParams();
        barParams.leftMargin = Math.max(1, space / 6);
        barParams.rightMargin = space / 6;
        barParams.width = 0;
        barParams.weight = weight;
        setLayoutParams(barParams);
    }

    public void applyAnimTransformation(float interpolatedTime) {
        setViewWeight(mBackground, 1 - mWeight * interpolatedTime);
        setViewWeight(mForeGround, mWeight * interpolatedTime);
        mBackground.invalidate();
        mForeGround.invalidate();
    }

    private void setViewWeight(View view, float weight) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.weight = weight;
        view.setLayoutParams(params);
    }
}
