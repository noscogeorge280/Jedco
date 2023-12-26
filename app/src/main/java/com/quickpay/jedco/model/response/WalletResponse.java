package com.quickpay.jedco.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WalletResponse implements Serializable {
    @SerializedName("amount")
    private  double  amount;
    @SerializedName("walletBalanceBeforeFund")
    private  double  walletBalanceBeforeFund;
    @SerializedName("fundStatus")
    private  String  fundStatus;
    @SerializedName("fundedBy")
    private  String  fundedBy;
    @SerializedName("dateOfTransaction")
    private  String  dateOfTransaction;

    public double getAmount() {
        return amount;
    }

    public double getWalletBalanceBeforeFund() {
        return walletBalanceBeforeFund;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public String getFundedBy() {
        return fundedBy;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }
}

