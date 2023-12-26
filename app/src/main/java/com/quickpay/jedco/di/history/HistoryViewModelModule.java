package com.quickpay.jedco.di.history;

import androidx.lifecycle.ViewModel;

import com.quickpay.jedco.di.ViewModelKey;
import com.quickpay.jedco.ui.history.HistoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class HistoryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    public abstract ViewModel bindHistoryViewModel(HistoryViewModel viewModel);
}