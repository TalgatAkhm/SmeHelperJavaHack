package com.mipt.smehelper.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.mipt.smehelper.R;
import com.mipt.smehelper.ui.behaviour.BottomNavigationBehaviour;
import com.mipt.smehelper.ui.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    private final static int MENU = R.menu.bottom_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.inflateMenu(MENU);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigation);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        params.setBehavior(new BottomNavigationBehaviour());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
