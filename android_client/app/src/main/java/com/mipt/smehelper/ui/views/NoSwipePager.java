package com.mipt.smehelper.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Custom ViewPager with disabled left and right swiping. Is used in {@link com.mipt.smehelper.ui.MainMenu}.
 * @author Mikhail Molchanov (molchanov.ma@phystech.edu).
 */
public class NoSwipePager extends ViewPager {
    private boolean enabled;

    public NoSwipePager(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.enabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.enabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
