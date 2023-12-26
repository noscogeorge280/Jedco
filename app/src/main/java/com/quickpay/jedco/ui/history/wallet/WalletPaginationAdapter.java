package com.quickpay.jedco.ui.history.wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quickpay.jedco.R;
import com.quickpay.jedco.model.response.WalletResponse;
import com.quickpay.jedco.util.HelperRegistry;

import java.util.ArrayList;

public class WalletPaginationAdapter extends RecyclerView.Adapter<WalletPaginationAdapter.ViewHolder> {

    private final ArrayList<WalletResponse> walletDataArrayList;
    private final HelperRegistry _helperRegistry;

    public WalletPaginationAdapter(ArrayList<WalletResponse> walletResponseArrayList, HelperRegistry helperRegistry) {
        _helperRegistry = helperRegistry;
        this.walletDataArrayList = walletResponseArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.walletResponse = walletDataArrayList.get(position);

        holder.fund_type.setText(walletDataArrayList.get(position).getFundStatus());
        holder.funded_by.setText(walletDataArrayList.get(position).getFundedBy() );
        holder.fund_amount.setText("NGN" + walletDataArrayList.get(position).getAmount());
        holder.old_balance.setText("NGN" + walletDataArrayList.get(position).getWalletBalanceBeforeFund());
        holder.fund_date.setText(walletDataArrayList.get(position).getDateOfTransaction().substring(0, 10));
    }
    @Override
    public int getItemCount() {
        return walletDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public WalletResponse walletResponse;
        TextView fund_amount,old_balance, fund_date, fund_type, funded_by;
        View mView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mView = itemView;
            fund_amount = itemView.findViewById(R.id.fund_amount);
            fund_type = itemView.findViewById(R.id.fund_type);
            funded_by = itemView.findViewById(R.id.fund_by);
            fund_date = itemView.findViewById(R.id.fund_date);
            old_balance = itemView.findViewById(R.id.old_balance);

        }
    }
}
