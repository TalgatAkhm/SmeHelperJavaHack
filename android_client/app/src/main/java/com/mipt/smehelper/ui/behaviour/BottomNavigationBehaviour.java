package com.mipt.smehelper.ui.behaviour;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Custom behaviour for navigation bar in {@link com.mipt.smehelper.ui.MainMenu}.
 * @author Mikhail Molchanov (molchanov.ma@phystech.edu).
 */
public class BottomNavigationBehaviour<V extends View> extends CoordinatorLayout.Behavior<V> {

    public BottomNavigationBehaviour() {
    }

    public BottomNavigationBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Reject all axes scrolling but vertical axe.
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * Move navigation bar.
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        child.setTranslationY(Math.max(0f, Math.min((float) child.getHeight(), child.getTranslationY() + dy)));
    }
}