package com.quickpay.jedco;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.multidex.MultiDexApplication;

import com.quickpay.jedco.di.AppInjector;
import com.quickpay.jedco.util.NetworkChangeReceiver;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends MultiDexApplication implements HasActivityInjector {
    private static BaseApplication mInstance;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    NetworkChangeReceiver netr = new NetworkChangeReceiver();

    public static BaseApplication getInstance() {
        if (mInstance == null) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        this.registerReceiver(netr, filter);
        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

