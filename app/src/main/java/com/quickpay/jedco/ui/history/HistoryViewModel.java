package com.quickpay.jedco.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickpay.jedco.di.network.history.HistoryApi;
import com.quickpay.jedco.model.response.DataItem;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.model.response.ResponseDTO;
import com.quickpay.jedco.model.response.TransactionHistoryResponse;
import com.quickpay.jedco.model.response.VendPaymentResponse;
import com.quickpay.jedco.model.response.WalletResponse;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryViewModel extends ViewModel {
    private  final HistoryApi historyApi;
    @Inject
    public HistoryViewModel(HistoryApi historyApi) {
        this.historyApi = historyApi;
    }


    public LiveData<ResponseDTO<List<WalletResponse>>> walletLog(String token, int pageNumber, int pageSize) {
        final MutableLiveData<ResponseDTO<List<WalletResponse>>> responseApi = new MutableLiveData<>();
        Call call = historyApi.walletLog("Bearer " + token, pageNumber, pageSize);
        call.enqueue(new Callback<ResponseDTO<List<WalletResponse>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<WalletResponse>>> call, Response<ResponseDTO<List<WalletResponse>>> response) {
                try {

                    if (response.isSuccessful()) {
                        responseApi.setValue(response.body());
                    } else {
                        responseApi.setValue(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    responseApi.setValue(null);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                responseApi.setValue(null);
            }
        });

        return responseApi;
    }

    public LiveData<TransactionHistoryResponse> vendHistoryLog(String token, int pageNumber, int pageSize) {
        final MutableLiveData<TransactionHistoryResponse> responseApi = new MutableLiveData<>();

        Call call = historyApi.VendHistory("Bearer " + token, pageNumber, pageSize);
        call.enqueue(new Callback<TransactionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransactionHistoryResponse> call, Response<TransactionHistoryResponse> response) {
                try {

                    if (response.isSuccessful()) {
                       responseApi.setValue(response.body());

                    } else {
                        responseApi.setValue(null);
                        System.out.println("not successful Loading  ::");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error Loading  ::" + e.getMessage());

                    responseApi.setValue(null);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println(t.fillInStackTrace());
                responseApi.setValue(null);
            }
        });

        return responseApi;
    }


}
