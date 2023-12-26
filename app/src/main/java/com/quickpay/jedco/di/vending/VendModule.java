package com.quickpay.jedco.di.vending;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import com.quickpay.jedco.di.network.vending.VendApi;

@Module
public class VendModule {

    @Provides
    static VendApi provideVendApi(Retrofit retrofit) {
        return retrofit.create(VendApi.class);
    }
}