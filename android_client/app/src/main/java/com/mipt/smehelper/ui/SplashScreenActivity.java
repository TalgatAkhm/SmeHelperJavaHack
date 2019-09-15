package com.mipt.smehelper.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.mipt.smehelper.Data;
import com.mipt.smehelper.EBMessages.UsersFetchedMessage;
import com.mipt.smehelper.R;
import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.User;
import com.mipt.smehelper.network.ClientApiGet;
import com.mipt.smehelper.network.ClientApiPost;
import com.mipt.smehelper.network.NetworkService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    private final static String TAG = SplashScreenActivity.class.getName();
    private static ClientApiPost clientApiPost = NetworkService.getInstance().getPostClientApi();

    private final static String PREFERENCES = "preferences";
    private final static String FIRST_APP = "is_first_enter";
    private final static String USER_NAME = "user_name";

    private SharedPreferences preferences;

    private ViewPager viewPager;
    private SplashViewPagerAdapter pagerAdapter;

    private Data applicationData;

    interface SurveyListener {
        void surveyDoneCallback();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        applicationData = Data.getInstance();
        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean isFirstEnter = preferences.getBoolean(FIRST_APP, true);

        if (isFirstEnter) {
            Log.d(TAG, "First enterence");
            setContentView(R.layout.activity_splash_screen);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(FIRST_APP, false);
            editor.apply();
            configurateSplashScreen();
        } else {
            Log.d(TAG, "Redirect to menu");
            setContentView(R.layout.activity_splash_screen_logedin);
            final String userName = preferences.getString(USER_NAME, null);
            if (userName == null) {
                Log.e(TAG, "Error getting loged in user");
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // send request to server

                    try {
                        Response response = clientApiPost.getUser(userName).execute();
                        User user = (User) response.body();

                        if (user == null) {
                            Log.d(TAG, "Error while auth");
                        }

                        Data.getInstance().setUser(user);

                        Response notificationResponse = clientApiPost.getNotifications().execute();

                        if (notificationResponse.isSuccessful()) {
                            Log.d(TAG, "Get notifications successfully");
                            List<Notification> notificationList = (List<Notification>) notificationResponse.body();
                            applicationData.setNotifications(notificationList);
                        } else {
                            Log.d(TAG, "Error while getting notifications");
                        }

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity();
                            }
                        }, 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        EventBus.getDefault().register(this);
        //TODO:: normal parameters
        //WorkerFetcher.fromUs(null);
        //WorkerFetcher.fromAvito(1488);
    }

    private void startActivity() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }


    private void configurateSplashScreen() {
        viewPager = findViewById(R.id.splash_viewpager);
        pagerAdapter = new SplashViewPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    private void surveyDone() {
        final String name = pagerAdapter.name.getEditText().getText().toString();
        final String nalog = pagerAdapter.nalog.getSelectedItem().toString();
        final String job = pagerAdapter.job.getSelectedItem().toString();
        final String city = pagerAdapter.city.getEditText().getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, name);
        editor.apply();

        Log.d(TAG, String.format("Survey results: %s, %s, %s, %s", name, nalog, job, city));

        pagerAdapter.applyBtn.setVisibility(View.INVISIBLE);
        pagerAdapter.progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // send request to registration, use name

                User user = new User();
                user.setSmallUSN(true);
                user.setAmount(Math.random() * 100000);
                user.setCity(city);
                user.setJob(job);
                user.setName(name);
                user.setRegDate(new DateTime().toString());

                try {
                    Response response = clientApiPost.authClient(user).execute();

                    if (response.isSuccessful()) {
                        Log.d(TAG, "Auth successful");
                    } else {
                        Log.e(TAG, "Auth error for user " + String.valueOf(user));
                    }

                    Response notificationResponse = clientApiPost.getNotifications().execute();

                    if (notificationResponse.isSuccessful()) {
                        Log.d(TAG, "Get notifications successfully");
                        List<Notification> notificationList = (List<Notification>) notificationResponse.body();
                        applicationData.setNotifications(notificationList);
                    } else {
                        Log.d(TAG, "Error while getting notifications");
                    }

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity();
                        }
                    }, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    class SplashViewPagerAdapter extends PagerAdapter {

        private Context context;

        private TextInputLayout name;
        private Spinner job;
        private TextInputLayout city;
        private Spinner nalog;
        private ProgressBar progressBar;
        private Button applyBtn;

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
                progressBar = layout.findViewById(R.id.progress_bar_survey);

                ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(context, R.array.jobs, android.R.layout.simple_spinner_item);
                jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                job.setAdapter(jobAdapter);

                ArrayAdapter<CharSequence> nalogAdapter = ArrayAdapter.createFromResource(context, R.array.nalogs, android.R.layout.simple_spinner_item);
                nalogAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                nalog.setAdapter(nalogAdapter);

                applyBtn = layout.findViewById(R.id.next_btn);
                applyBtn.setOnClickListener(new View.OnClickListener() {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void workersFetched(UsersFetchedMessage message) {
        // Set workers
    }
}
