package com.quickpay.jedco.ui.vending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quickpay.jedco.di.network.vending.VendApi;
import com.quickpay.jedco.model.request.MeterDetailsRequest;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.model.response.ResponseDTO;
import com.quickpay.jedco.model.response.VendPaymentResponse;
import com.quickpay.jedco.util.HelperRegistry;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendViewModel extends ViewModel {
    private final VendApi vendApi;
    @Inject
    HelperRegistry helperRegistry;

    @Inject
    public VendViewModel(VendApi vendApi) {
        this.vendApi = vendApi;
    }

    public LiveData<ResponseDTO<MeterDetailResponse>> getMeterDetails(MeterDetailsRequest request, String token) {
        final MutableLiveData<ResponseDTO<MeterDetailResponse>> responseApi = new MutableLiveData<>();
        Call call = vendApi.meterDetails(request, "Bearer " + token);
        call.enqueue(new Callback<ResponseDTO<MeterDetailResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<MeterDetailResponse>> call, Response<ResponseDTO<MeterDetailResponse>> response) {
                try {
                    if (response.isSuccessful())
                        responseApi.setValue(response.body());
                    else {
                       /* MeterDetailResponse meterViewModelResponse = new MeterDetailResponse(response.message());

                        switch (response.code()) {
                            case 404:
                                meterViewModelResponse.setErrorMessage("Meter number not found " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                            case 400:
                                meterViewModelResponse.setErrorMessage("Please Phone No, Amount and meter number is required " + response.message());
                                responseApi.setValue(meterViewModelResponse);

                                //Toast.makeText(this,"")
                                // responseApi.setValue(meterViewModelResponse);
                                break;
                            case 500:
                                meterViewModelResponse.setErrorMessage("Internal server error " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                            case 503:
                                meterViewModelResponse.setErrorMessage("Server Unable to process " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                        }*/

                        response.errorBody().toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    //responseApi.setValue(new MeterResponse(e.getMessage()));
                    //  responseApi.setValue(e.getMessage()));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // responseApi.setValue(t.getCause());
            }
        });

        return responseApi;
    }


    public LiveData<ResponseDTO<VendPaymentResponse>> vendEnergy(MeterDetailResponse request, String token) {
        final MutableLiveData<ResponseDTO<VendPaymentResponse>> responseApi = new MutableLiveData<>();
        Call call = vendApi.vendEnergy(request, "Bearer " + token);
        call.enqueue(new Callback<ResponseDTO<VendPaymentResponse>>() {
            @Override
            public void onResponse(Call<ResponseDTO<VendPaymentResponse>> call, Response<ResponseDTO<VendPaymentResponse>> response) {
                try {
                    if (response.isSuccessful())
                        responseApi.setValue(response.body());
                    else {
                       /* VendPaymentResponse meterViewModelResponse = new VendPaymentResponse(response.message());
                        switch (response.code()) {
                            case 404:
                                response.errorBody().toString();

                                meterViewModelResponse.setErrorMessage("Unable to validate payment " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                            case 400:
                                meterViewModelResponse.setErrorMessage("payment  " + response.message());
                                responseApi.setValue(meterViewModelResponse);

                                //Toast.makeText(this,"")
                                // responseApi.setValue(meterViewModelResponse);
                                break;
                            case 500:
                                meterViewModelResponse.setErrorMessage("Internal server error " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                            case 503:
                                meterViewModelResponse.setErrorMessage("Server Unable to process " + response.message());
                                responseApi.setValue(meterViewModelResponse);
                                break;
                        }*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    // responseApi.setValue(new PaymentResponse(e.getMessage()));
                    //  responseApi.setValue(e.getMessage()));
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.getCause();
            }
        });

        return responseApi;
    }

}