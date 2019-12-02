package com.creative.iam.laundryin.views.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.onboard.OnboardActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PreferencesUtils sp = new PreferencesUtils(getApplicationContext());
                String username = sp.get("uname",null);

                if (username != null) {
                    Intent intent = new Intent(SplashActivity.this, UtamaActivity.class);
                    intent.putExtra("username", username);
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
