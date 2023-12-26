package com.quickpay.jedco.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.quickpay.jedco.model.response.ChangePasswordResponse;
import com.quickpay.jedco.model.request.ChangePasswordRequest;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import  com.quickpay.jedco.di.network.main.MainApi;
import com.quickpay.jedco.model.response.DashboardViewModel;
import com.quickpay.jedco.model.response.ResponseDTO;

public class MainViewModel extends ViewModel {

    private final MainApi mainApi;

    @Inject
    public MainViewModel(MainApi mainApi) {
        this.mainApi = mainApi;
    }


    public LiveData<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest, String token) {

        final MutableLiveData<ChangePasswordResponse> responseApi = new MutableLiveData<>();
        Call call = mainApi.changePassword(changePasswordRequest, "Bearer " + token);
        call.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                try {

                    if (response.isSuccessful()) {
                        responseApi.setValue(response.body());
                    } else {
                        responseApi.setValue(new ChangePasswordResponse(response.message()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                   // responseApi.setValue(new ChangePasswordResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
               // responseApi.setValue(new ChangePasswordResponse(NETWORK_ERROR));
            }
        });

        return responseApi;
    }

    public LiveData<ResponseDTO<DashboardViewModel>> dashboard(String token) {

        final MutableLiveData<ResponseDTO<DashboardViewModel>> responseApi = new MutableLiveData<>();
        Call call = mainApi.dashboard("Bearer " + token);
        call.enqueue(new Callback<ResponseDTO<DashboardViewModel>>() {
            @Override
            public void onResponse(Call<ResponseDTO<DashboardViewModel>> call, Response<ResponseDTO<DashboardViewModel>> response) {
                try {

                    if (response.isSuccessful()) {
                          response.body();
                          System.out.println(" object ::"+ response.body());
                        //...other stuff you might need...
                        //...do something with the response, convert it to
                        //return the correct object...
                       // DashboardViewModel object = new DashboardViewModel();

                        responseApi.setValue(response.body());
                    } else {
                       // responseApi.setValue(new DashboardViewModel(response.message()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                   // responseApi.setValue(new DashboardViewModel(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
               // responseApi.setValue(new DashboardViewModel(NETWORK_ERROR));
            }
        });

        return responseApi;
    }



}