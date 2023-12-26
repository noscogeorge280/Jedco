package com.quickpay.jedco.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MeterDetailResponse implements Serializable {
    @SerializedName("amount")
    private double amount;
    @SerializedName("status_code")
    private String status_code;
    @SerializedName("access_code")
    private String access_code;
    @SerializedName("account_number")
    private String account_number;
    @SerializedName("customer_type")
    private String customer_type;
    @SerializedName("customer_name")
    private String customer_name;
    @SerializedName("customer_address")
    private String customer_address;
    @SerializedName("customer_phone")
    private String customer_phone;
    @SerializedName("feeder")
    private String feeder;
    @SerializedName("tariff")
    private String tariff;
    @SerializedName("tariff_rate")
    private String tariff_rate;
    @SerializedName("outstanding")
    private String outstanding;
    @SerializedName("user_balance_before")
    private String user_balance_before;
    @SerializedName("user_balance_after")
    private String user_balance_after;
    @SerializedName("agentID")
    private String agentID;
    @SerializedName("txtime")
    private String txtime;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getFeeder() {
        return feeder;
    }

    public void setFeeder(String feeder) {
        this.feeder = feeder;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getTariff_rate() {
        return tariff_rate;
    }

    public void setTariff_rate(String tariff_rate) {
        this.tariff_rate = tariff_rate;
    }

    public String getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(String outstanding) {
        this.outstanding = outstanding;
    }

    public String getUser_balance_before() {
        return user_balance_before;
    }

    public void setUser_balance_before(String user_balance_before) {
        this.user_balance_before = user_balance_before;
    }

    public String getUser_balance_after() {
        return user_balance_after;
    }

    public void setUser_balance_after(String user_balance_after) {
        this.user_balance_after = user_balance_after;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getTxtime() {
        return txtime;
    }

    public void setTxtime(String txtime) {
        this.txtime = txtime;
    }
}
