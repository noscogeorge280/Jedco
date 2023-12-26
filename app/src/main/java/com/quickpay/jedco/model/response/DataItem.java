package com.quickpay.jedco.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {
    @SerializedName("access_code")
    private String access_code;
    @SerializedName("account_number")
    private String account_number;
    @SerializedName("meter_number")
    private String meter_number;
    @SerializedName("amount")
    private double amount;
    @SerializedName("amount_paid")
    private String amount_paid;
    @SerializedName("token")
    private String token;
    @SerializedName("units")
    private String units;
    @SerializedName("tariff_rate")
    private String tariff_rate;
    @SerializedName("vat")
    private String vat;
    @SerializedName("customer_type")
    private String customer_type;
    @SerializedName("customer_name")
    private String customer_name;
    @SerializedName("customer_address")
    private String customer_address;
    @SerializedName("vref")
    private String vref;
    @SerializedName("customer_phone")
    private String customer_phone;
    @SerializedName("tariff")
    private String tariff;
    @SerializedName("feeder")
    private String feeder;
    @SerializedName("outstanding")
    private String outstanding;
    @SerializedName("outstanding_paid")
    private String outstanding_paid;
    @SerializedName("createdBy")
    private String createdBy;
    @SerializedName("dateCreated")
    private String dateCreated;

    public String getAccess_code() {
        return access_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public String getToken() {
        return token;
    }

    public String getUnits() {
        return units;
    }

    public String getTariff_rate() {
        return tariff_rate;
    }

    public String getVat() {
        return vat;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public String getVref() {
        return vref;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public String getTariff() {
        return tariff;
    }

    public String getFeeder() {
        return feeder;
    }

    public String getOutstanding() {
        return outstanding;
    }

    public String getOutstanding_paid() {
        return outstanding_paid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
