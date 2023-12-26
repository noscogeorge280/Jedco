package com.quickpay.jedco.di.network.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.quickpay.jedco.model.request.LoginRequest;
import com.quickpay.jedco.model.response.LoginResponse;
public interface ChangePasswordApi {
    @POST("Account/token")
    Call<LoginResponse> login(@Body LoginRequest request);

}
