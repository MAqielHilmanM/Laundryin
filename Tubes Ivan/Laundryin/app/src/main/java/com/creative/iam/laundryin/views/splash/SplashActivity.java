package com.creative.iam.laundryin.views.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.views.onboard.OnboardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(SplashActivity.this, OnboardActivity.class);
                startActivity(in);
                finish();
            }
        },2000);
    }
}
