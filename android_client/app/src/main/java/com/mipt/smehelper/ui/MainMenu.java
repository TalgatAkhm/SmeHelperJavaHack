package com.mipt.smehelper.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.mipt.smehelper.EBMessages.UsersFetchedMessage;
import com.mipt.smehelper.R;
import com.mipt.smehelper.network.WorkerFetcher;
import com.mipt.smehelper.ui.behaviour.BottomNavigationBehaviour;
import com.mipt.smehelper.ui.fragments.ChatFragment;
import com.mipt.smehelper.ui.fragments.CoworkersFragment;
import com.mipt.smehelper.ui.fragments.DocumentsFragment;
import com.mipt.smehelper.ui.fragments.FeedFragment;
import com.mipt.smehelper.ui.utils.BottomNavigationViewHelper;
import com.mipt.smehelper.ui.views.NoSwipePager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenu extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainMenu.class.getName();

    @BindView(R.id.bottom_navigation)
    private BottomNavigationView bottomNavigation;
    private NoSwipePager noSwipePager;

    private final static int MENU = R.menu.bottom_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        noSwipePager = findViewById(R.id.main_menu_view_pager);

        NoSwipePagerAdapter pagerAdapter = new NoSwipePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new FeedFragment());
        pagerAdapter.addFragment(new CoworkersFragment());
        pagerAdapter.addFragment(new ChatFragment());
        pagerAdapter.addFragment(new DocumentsFragment());

        noSwipePager.setPagingEnabled(false);
        noSwipePager.setOffscreenPageLimit(4);
        noSwipePager.setAdapter(pagerAdapter);

        bottomNavigation.inflateMenu(MENU);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigation);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) bottomNavigation.getLayoutParams();
        params.setBehavior(new BottomNavigationBehaviour());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.feed_item:
                noSwipePager.setCurrentItem(0, false);
                break;
            case R.id.coworkers_item:
                noSwipePager.setCurrentItem(1, false);
                break;
            case R.id.chat_item:
                noSwipePager.setCurrentItem(2, false);
                break;
            case R.id.doc_item:
                noSwipePager.setCurrentItem(3, false);
                break;
        }
        return true;
    }

    class NoSwipePagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments = new ArrayList<>();

        NoSwipePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, String.format("Current position: %d", position));
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
