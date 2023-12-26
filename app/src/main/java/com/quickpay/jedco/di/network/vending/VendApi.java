package com.quickpay.jedco.di.network.vending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import com.quickpay.jedco.model.request.MeterDetailsRequest;
import com.quickpay.jedco.model.response.DashboardViewModel;
import com.quickpay.jedco.model.response.ResponseDTO;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.model.response.VendPaymentResponse;

public interface VendApi {


    //@FormUrlEncoded
    @POST("Vending/get-customer")
    Call<ResponseDTO<MeterDetailResponse>> meterDetails( @Body MeterDetailsRequest request, @Header("Authorization") String authHeader);

    @POST("Vending/agent-paynow")
    Call<ResponseDTO<VendPaymentResponse>> vendEnergy(@Body MeterDetailResponse request,@Header("Authorization") String authHeader);


    
}