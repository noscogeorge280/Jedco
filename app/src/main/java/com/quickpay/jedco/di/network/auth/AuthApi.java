package com.quickpay.jedco.di.network.auth;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.model.request.LoginRequest;
public interface AuthApi {
    //@FormUrlEncoded
    @POST("Token/login")
    Call<LoginResponse> login(@Body LoginRequest request);

}
