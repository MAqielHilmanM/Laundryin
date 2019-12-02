package com.creative.iam.laundryin.views.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.onboard.OnboardActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "username";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedPreferences;
    Boolean session = false;
    String username;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
                session = sharedPreferences.getBoolean(session_status, false);
                username = sharedPreferences.getString(TAG_USERNAME, null);

                if (session) {
                    Intent intent = new Intent(SplashActivity.this, UtamaActivity.class);
                    intent.putExtra(TAG_USERNAME, username);
                    finish();
                    startActivity(intent);
                }else {
                    Intent in = new Intent(SplashActivity.this, OnboardActivity.class);
                    startActivity(in);
                    finish();
                }

            }
        },2000);
    }
}
