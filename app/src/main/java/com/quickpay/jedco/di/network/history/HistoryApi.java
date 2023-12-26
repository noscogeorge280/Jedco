package com.quickpay.jedco.di.network.history;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import  com.quickpay.jedco.model.response.*;

public interface HistoryApi {


  @POST("Vending/agent-transaction-history")
  Call<TransactionHistoryResponse>  VendHistory(@Header("Authorization") String authHeader,
                                   @Query("PageNumber") int PageNumber,
                                   @Query("PageSize") int PageSize);

    @POST("Vending/agent-wallet-history")
    Call<ResponseDTO<List<WalletResponse>>> walletLog(@Header("Authorization") String authHeader,
                                         @Query("PageNumber") int pageNumber, @Query("PageSize") int pageSize);
}