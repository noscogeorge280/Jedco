package com.quickpay.jedco.di.network.main;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import com.quickpay.jedco.model.request.ChangePasswordRequest;
import com.quickpay.jedco.model.response.*;

public interface MainApi {

    @POST("Account/ChangePassword")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest request, @Header("Authorization") String authHeader);

    @GET("Account/agent-dashboard")
    Call<ResponseDTO<DashboardViewModel>> dashboard(@Header("Authorization") String authHeader);

}