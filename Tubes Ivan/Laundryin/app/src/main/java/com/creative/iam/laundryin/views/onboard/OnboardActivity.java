package com.creative.iam.laundryin.views.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.creative.iam.laundryin.views.login.LoginActivity;
import com.creative.iam.laundryin.views.splash.SplashActivity;
import com.github.paolorotolo.appintro.AppIntro2;

public class OnboardActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnboardFragment fragment1 = new OnboardFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("status",1);
        fragment1.setArguments(bundle1);
        addSlide(fragment1);

        OnboardFragment fragment2 = new OnboardFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("status",2);
        fragment2.setArguments(bundle2);
        addSlide(fragment2);

        OnboardFragment fragment3 = new OnboardFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("status",3);
        fragment3.setArguments(bundle3);
        addSlide(fragment3);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent in = new Intent(OnboardActivity.this, LoginActivity.class);
        startActivity(in);
        finish();
    }
}
