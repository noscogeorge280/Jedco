package com.quickpay.jedco.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.quickpay.jedco.ui.main.ui.home.HomeFragment;
import com.quickpay.jedco.ui.main.ui.changepassword.ChangePasswordFragment;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    public abstract HomeFragment contributeHomeFrag();

    @ContributesAndroidInjector(modules = MainViewModelModule.class)
    public abstract ChangePasswordFragment contributeAccountFrag();

}