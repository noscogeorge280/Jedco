package com.quickpay.jedco.di.auth;

import androidx.lifecycle.ViewModel;

import com.quickpay.jedco.di.ViewModelKey;
import com.quickpay.jedco.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
