package com.mipt.smehelper.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mipt.smehelper.R;

public class SplashScreenActivity extends AppCompatActivity {
    private final static String TAG = SplashScreenActivity.class.getName();


    private final static String PREFERENCES = "preferences";
    private final static String FIRST_APP = "is_first_enter";

    private SharedPreferences preferences;

    private ViewPager viewPager;
    private SplashViewPagerAdapter pagerAdapter;

    interface SurveyListener {
        void surveyDoneCallback();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isFirstEnter = preferences.getBoolean(FIRST_APP, true);

//        if (!isFirstEnter) {
            Log.d(TAG, "First enterence");
            setContentView(R.layout.activity_splash_screen);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(FIRST_APP, false);
            editor.apply();
            configurateSplashScreen();
//        } else {
//            Log.d(TAG, "Redirect to menu");
//            Intent intent = new Intent(this, MainMenu.class);
//            startActivity(intent);
//        }
    }

    private void configurateSplashScreen() {
        viewPager = findViewById(R.id.splash_viewpager);
        pagerAdapter = new SplashViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    private void surveyDone() {
        String name = pagerAdapter.name.getEditText().getText().toString();
        String nalog = pagerAdapter.nalog.getSelectedItem().toString();
        String job = pagerAdapter.job.getSelectedItem().toString();
        String city = pagerAdapter.city.getEditText().getText().toString();

        Log.d(TAG, String.format("Survey results: %s, %s, %s, %s", name, nalog, job, city));

        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    class SplashViewPagerAdapter extends PagerAdapter {

        private Context context;

        private TextInputLayout name;
        private Spinner job;
        private TextInputLayout city;
        private Spinner nalog;

        public SplashViewPagerAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout;
            if (position == 0) {
                Log.d(TAG, "Screen 0");
                layout = inflater.inflate(R.layout.splash_screen_1, container, false);

                name = layout.findViewById(R.id.user_name_input_layout);

            } else {
                Log.d(TAG, "Screen 1");
                layout = inflater.inflate(R.layout.splash_screen_2, container, false);

                job = layout.findViewById(R.id.job_spinner);
                city = layout.findViewById(R.id.city_input_layout);
                nalog = layout.findViewById(R.id.nalog_spinner);

                ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(context, R.array.jobs, android.R.layout.simple_spinner_item);
                jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                job.setAdapter(jobAdapter);

                ArrayAdapter<CharSequence> nalogAdapter = ArrayAdapter.createFromResource(context, R.array.nalogs, android.R.layout.simple_spinner_item);
                nalogAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                nalog.setAdapter(nalogAdapter);

                layout.findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SplashScreenActivity.this.surveyDone();
                    }
                });
            }

            container.addView(layout);
            return layout;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout)object);
        }
    }
}
