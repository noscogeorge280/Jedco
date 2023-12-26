package com.quickpay.jedco.model.request;

import com.google.gson.annotations.SerializedName;

public class MeterDetailsRequest {
    @SerializedName("meterNumber")
    private String meterNumber;

    @SerializedName("amount")
    private double amount;

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
