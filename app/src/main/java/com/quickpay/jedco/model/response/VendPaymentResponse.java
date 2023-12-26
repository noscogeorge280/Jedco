package com.quickpay.jedco.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VendPaymentResponse implements Serializable {

    @SerializedName("agent_name")
    private String agent_name;
    @SerializedName("agent_phone_no")
    private String agent_phone_no;

    public String getAgent_name() {
        return agent_name;
    }

    public String getAgent_phone_no() {
        return agent_phone_no;
    }

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

    @SerializedName("status_code")
    private String status_code;
    @SerializedName("message")
    private String message;
    @SerializedName("transaction_id")
    private String transaction_id;
    @SerializedName("user_balance_before")
    private String user_balance_before;
    @SerializedName("agentID")
    private String agentID;
    @SerializedName("user_balance_after")
    private String user_balance_after;
    @SerializedName("txtime")
    private String txtime;
    @SerializedName("date_of_transaction")
    private String date_of_transaction;

    public String getStatus_code() {
        return status_code;
    }

    public String getAccess_code() {
        return access_code;
    }

    public double getAmount() {
        return amount;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getTariff_rate() {
        return tariff_rate;
    }

    public void setTariff_rate(String tariff_rate) {
        this.tariff_rate = tariff_rate;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getOutstanding_paid() {
        return outstanding_paid;
    }

    public void setOutstanding_paid(String outstanding_paid) {
        this.outstanding_paid = outstanding_paid;
    }

    public String getUser_balance_before() {
        return user_balance_before;
    }

    public void setUser_balance_before(String user_balance_before) {
        this.user_balance_before = user_balance_before;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public String getUser_balance_after() {
        return user_balance_after;
    }

    public void setUser_balance_after(String user_balance_after) {
        this.user_balance_after = user_balance_after;
    }

    public String getTxtime() {
        return txtime;
    }

    public void setTxtime(String txtime) {
        this.txtime = txtime;
    }

    public String getVref() {
        return vref;
    }

    public void setVref(String vref) {
        this.vref = vref;
    }

    public String getDate_of_transaction() {
        return date_of_transaction;
    }

    public void setDate_of_transaction(String date_of_transaction) {
        this.date_of_transaction = date_of_transaction;
    }
}
