package com.quickpay.jedco.model.response;

public class ChangePasswordResponse {

    private final String errorDescription;

    public ChangePasswordResponse(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
