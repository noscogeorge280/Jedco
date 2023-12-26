package com.quickpay.jedco.model.response;

import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("token")
    public String token;
    @SerializedName("id")
    public String id;
    @SerializedName("index")
    public String index;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String role_name;
}
