package com.quickpay.jedco.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.quickpay.jedco.di.network.auth.AuthApi;
import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.model.request.LoginRequest;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
    }

    public LiveData<LoginResponse> login(String email, String password) {
        final MutableLiveData<LoginResponse> responseApi = new MutableLiveData<>();
        Call call = authApi.login(new LoginRequest(email, password));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().statusCode()==200)
                            responseApi.setValue(response.body());

                        else
                            responseApi.setValue(new LoginResponse(response.message()));
                    } else {
                        responseApi.setValue(new LoginResponse(response.message()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                  //  responseApi.setValue(new LoginResponse(SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
               // responseApi.setValue(new LoginResponse(NETWORK_ERROR));
            }
        });

        return responseApi;
    }

}