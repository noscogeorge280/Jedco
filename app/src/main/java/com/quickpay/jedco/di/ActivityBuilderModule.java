package com.quickpay.jedco.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import com.quickpay.jedco.di.history.HistoryModule;
import com.quickpay.jedco.di.history.HistoryViewModelModule;
import com.quickpay.jedco.print.PrintActivity;
import com.quickpay.jedco.ui.history.vend.*;
import com.quickpay.jedco.ui.history.wallet.WalletHistoryActivity;
import com.quickpay.jedco.ui.splash.SplashActivity;
import com.quickpay.jedco.di.main.*;
import com.quickpay.jedco.di.auth.*;
import com.quickpay.jedco.di.vending.*;
import com.quickpay.jedco.ui.main.ui.home.HomeFragment;
import com.quickpay.jedco.ui.main.*;
import com.quickpay.jedco.ui.vending.*;
import com.quickpay.jedco.ui.auth.LoginActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplash();

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
                    MainModule.class,
                    MainViewModelModule.class
            }
    )
    abstract MainActivity contributeMain();

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    abstract LoginActivity contributeLogin();


    @ContributesAndroidInjector(
            modules = {
                    VendModule.class,
                    VendViewModelModule.class
            }
    )
    abstract VendActivity contributeVend();

    @ContributesAndroidInjector(
            modules = {
                    VendModule.class,
                    VendViewModelModule.class
            }
    )
    abstract VendDetailsActivity contributeVendDetail();

    @ContributesAndroidInjector
    abstract SummaryActivity contributeSummary();

    @ContributesAndroidInjector(
            modules = {
                    HistoryModule.class,
                    HistoryViewModelModule.class
            }
    )
    abstract TransactionHistoryActivity contributeTransactionHistory();

    @ContributesAndroidInjector(
            modules = {
                    HistoryModule.class,
                    HistoryViewModelModule.class
            }
    )
    abstract WalletHistoryActivity WalletHistory();

    @ContributesAndroidInjector
    abstract VendHistoryDetailActivity contributeHistoryDetailSummary();

    @ContributesAndroidInjector
    abstract PrintActivity contributePrint();
}
