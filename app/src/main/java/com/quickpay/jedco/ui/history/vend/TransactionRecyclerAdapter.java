package com.quickpay.jedco.ui.history.vend;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.quickpay.jedco.R;
import com.quickpay.jedco.model.response.DataItem;
import com.quickpay.jedco.model.response.VendPaymentResponse;
import com.quickpay.jedco.ui.history.OnVendHistoryClickListener;
import com.quickpay.jedco.ui.history.*;

import com.quickpay.jedco.util.BaseViewHolder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransactionRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private final OnVendHistoryClickListener mListener;
    private final Context context;
    private final List<VendPaymentResponse> mDataItem;
    private final DateFormat mFormatter = new SimpleDateFormat("dd-MM-yyyy");
    private boolean isLoaderVisible = false;

    public TransactionRecyclerAdapter(Context context, OnVendHistoryClickListener listener) {
        this.mDataItem = new ArrayList<>();
        mListener = listener;
        this.context = context;

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.vend_item, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.onVendHistoryClickListener(mDataItem.get(holder.getCurrentPosition()));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mDataItem.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mDataItem == null ? 0 : mDataItem.size();
    }

    public void addItems(List<VendPaymentResponse> postItems) {
        mDataItem.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        mDataItem.add(new VendPaymentResponse());
        notifyItemInserted(mDataItem.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mDataItem.size() - 1;
        VendPaymentResponse item = getItem(position);
        if (item != null) {
            mDataItem.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mDataItem.clear();
        notifyDataSetChanged();
    }

    VendPaymentResponse getItem(int position) {
        return mDataItem.get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.ve_name)
        TextView textViewName;
        @BindView(R.id.ve_meter_number)
        TextView textViewMeter_number;
        @BindView(R.id.ve_amount)
        TextView textViewAmount;
        @BindView(R.id.ve_date)
        TextView textViewVend_date;
        @BindView(R.id.ve_phone_number)
        TextView textViewPhone_no;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
        }

        public void onBind(int position) {
            super.onBind(position/*, mDataItem.get(position)*/);
            VendPaymentResponse item = mDataItem.get(position);
            textViewAmount.setText("NGN " + item.getAmount());
            textViewName.setText(item.getCustomer_name());
            textViewPhone_no.setText(item.getCustomer_phone());
            textViewVend_date.setText(item.getDateCreated().substring(0, 10));
            textViewMeter_number.setText(item.getAccount_number());
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
        }
    }
}