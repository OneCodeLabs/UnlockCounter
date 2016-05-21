package com.unlockchecker.unlockchecker.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unlockchecker.unlockchecker.anim.BarChartAnimation;

import org.joda.time.DateTimeConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a home made chart showing bars that can show a very basic animation. Either document both
 * this class and {@link Bar} or delete them and replace them with a proper charting library.
 * Consider using MPAndroidCharts for that.
 */
public class BarChart extends LinearLayout {

    private static final int MONTH_LABEL_COUNT = 3;
    private static final int MONTHS_COUNT = 12;
    public static final String[] WEEKDAYS = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    public static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec" };
    public static final int[] MONTH_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static final int DAYS_ONE_MONTH = 30;
    public static final int DAYS_MONTH_AND_HALF = 45;
    public static final int DAYS_THREE_MONTHS = 90;

    private LinearLayout mBars;
    private LinearLayout mLabels;

    public BarChart(Context context) {
        super(context);
        init();
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void setBarData(final List<Integer> barData) {
        if (barData.isEmpty()) return;
        if (mBars == null) {
            getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            populateChart(barData);
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        }
                    });
        } else {
            removeView(mBars);
            populateChart(barData);
        }
    }

    private void populateChart(List<Integer> barData) {
        mBars = new LinearLayout(getContext());
        mBars.setLayoutParams(new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        mBars.setOrientation(HORIZONTAL);
        addView(mBars);
        int space = getMeasuredWidth() / barData.size();
        float weight = (float) 1 / barData.size();
        List<Bar> barList = new LinkedList<>();
        for (int i = 0; i < barData.size(); i++) {
            Bar bar = new Bar(getContext());
            if (barData.get(i) != 0) barList.add(bar);
            mBars.addView(bar);
            bar.setBarData(barData.get(i), space, weight,
                    barData.size() <= DateTimeConstants.DAYS_PER_WEEK ? true : false);
        }
        setLabels(barData.size());
        startAnimation(new BarChartAnimation(barList));
    }

    private void setLabels(int days) {
        switch (days) {
            case DateTimeConstants.DAYS_PER_WEEK:
                setWeekdayLabels();
                break;
            case DAYS_ONE_MONTH:
                setMonthlyLabels();
                break;
            case DAYS_THREE_MONTHS:
                setTriMonthlyLabels();
                break;
        }
    }

    private void setWeekdayLabels() {
        if (mLabels != null) removeView(mLabels);
        mLabels = new LinearLayout(getContext());
        mLabels.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLabels.setOrientation(HORIZONTAL);
        addView(mLabels);
        float weight = (float) 1 / DateTimeConstants.DAYS_PER_WEEK;
        int dayInWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        for (int i = 0; i < DateTimeConstants.DAYS_PER_WEEK ; i++) {
            TextView day = new TextView(getContext());
            day.setLayoutParams(new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT, weight));
            day.setText(WEEKDAYS[dayInWeek++ %  DateTimeConstants.DAYS_PER_WEEK]);
            day.setGravity(Gravity.CENTER);
            mLabels.addView(day);
        }
    }

    private void setMonthlyLabels() {
        ArrayList<String> labels = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int dayInMonth = calendar.get(Calendar.DAY_OF_MONTH);

        labels.add(dayInMonth + " " + MONTHS[month]);
        dayInMonth -= MONTH_DAYS[month] / 2;
        if (dayInMonth <= 0) {
            month = (--month + MONTHS_COUNT) % MONTHS_COUNT;
            dayInMonth += MONTH_DAYS[month];
        }
        labels.add(0, dayInMonth + " " + MONTHS[month]);
        dayInMonth -= MONTH_DAYS[month] / 2;
        if (dayInMonth <= 0) {
            month = (--month + MONTHS_COUNT) % MONTHS_COUNT;
            dayInMonth += MONTH_DAYS[month];
        }
        month %= MONTHS_COUNT;
        labels.add(0, dayInMonth + " " + MONTHS[month]);

        setDateLabels(labels);
    }

    private void setTriMonthlyLabels() {
        List<String> labels = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int dayInMonth = calendar.get(Calendar.DAY_OF_MONTH);

        labels.add(dayInMonth + " " + MONTHS[month]);
        int monthIndex = (month - 3 + MONTHS_COUNT) % MONTHS_COUNT;
        labels.add(0, Math.min(MONTH_DAYS[monthIndex], dayInMonth)
                + " " + MONTHS[monthIndex]);
        int daysDifference = -DAYS_MONTH_AND_HALF;
        daysDifference += dayInMonth;
        while (daysDifference <= 0)  {
            month = (--month + MONTHS_COUNT) % MONTHS_COUNT;
            daysDifference += MONTH_DAYS[month];
        }
        if (daysDifference > MONTH_DAYS[month]) {
            daysDifference %= MONTH_DAYS[month++];
            month %= MONTHS_COUNT;
        }
        labels.add(1, daysDifference + " " + MONTHS[month]);

        setDateLabels(labels);
    }

    private void setDateLabels(List<String> labels) {
        if (mLabels != null) removeView(mLabels);
        mLabels = new LinearLayout(getContext());
        mLabels.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLabels.setOrientation(HORIZONTAL);
        addView(mLabels);
        float weight = (float) 1 / MONTH_LABEL_COUNT;
        addLabel(labels.get(0), weight, Gravity.LEFT);
        addLabel(labels.get(1), weight, Gravity.CENTER);
        addLabel(labels.get(2), weight, Gravity.RIGHT);
    }

    private void addLabel(String text, float weight, int gravity) {
        TextView label = new TextView(getContext());
        label.setLayoutParams(new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT, weight));
        label.setText(text);
        label.setGravity(gravity);
        mLabels.addView(label);
    }
}
