package com.quickpay.jedco.di.main;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import com.quickpay.jedco.di.network.main.MainApi;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
