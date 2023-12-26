package com.quickpay.jedco.di.main;

import androidx.lifecycle.ViewModel;

import com.quickpay.jedco.di.ViewModelKey;
import com.quickpay.jedco.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
