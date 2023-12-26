package com.quickpay.jedco.model.response;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    private final String errorDescription;
    @SerializedName("value")
    private Value value;
    @SerializedName("formatters")
    private List<String> formatters;
    @SerializedName("contentTypes")
    private  List<String> contentTypes;
    @SerializedName("declaredType")
    private String declaredType;
    @SerializedName("statusCode")
    private int statusCode;

    public LoginResponse(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Value getValue() {
        return value;
    }

    public List<String> getFormatters() {
        return formatters;
    }

    public List<String> getContentTypes() {
        return contentTypes;
    }

    public String getDeclaredType() {
        return declaredType;
    }
    public int statusCode() {
        return statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
