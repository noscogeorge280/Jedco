package com.quickpay.jedco.di.vending;

import androidx.lifecycle.ViewModel;

import com.quickpay.jedco.di.ViewModelKey;
import com.quickpay.jedco.ui.vending.VendViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class VendViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VendViewModel.class)
    public abstract ViewModel bindAuthViewModel(VendViewModel viewModel);
}