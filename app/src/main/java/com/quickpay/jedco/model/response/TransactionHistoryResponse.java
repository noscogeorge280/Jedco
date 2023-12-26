package com.quickpay.jedco.model.response;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class TransactionHistoryResponse implements Serializable {
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("currentPage")
    private int currentPage;
    @SerializedName("hasNext")
    private Boolean hasNext;
    @SerializedName("hasPrevious")
    private Boolean hasPrevious;
    @SerializedName("data")
    //private List<VendPaymentResponse> data;
    private List<VendPaymentResponse> data;

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public List<VendPaymentResponse> getData() {
        return data;
    }
}
