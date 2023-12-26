package com.quickpay.jedco.di.history;

import com.quickpay.jedco.di.network.history.HistoryApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HistoryModule {

    @Provides
    static HistoryApi provideHistoryApi(Retrofit retrofit) {
        return retrofit.create(HistoryApi.class);
    }
}