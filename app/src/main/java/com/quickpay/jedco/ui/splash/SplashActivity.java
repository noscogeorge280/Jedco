package com.quickpay.jedco.ui.splash;


import static com.quickpay.jedco.util.Constants.SPLASH_TIME_OUT;

import android.os.Bundle;
import android.os.Handler;

import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.Pref;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> checkLoginUser(), SPLASH_TIME_OUT);
    }

    private void checkLoginUser() {
        LoginResponse user = pref.getUser(this);
        if (user == null) {
            coordinator.launchLoginActivity(this);
        } else {
            coordinator.launchMainActivity(this);
        }
    }
}
