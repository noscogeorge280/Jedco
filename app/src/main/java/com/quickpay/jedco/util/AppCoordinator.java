package com.quickpay.jedco.util;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//import com.quickpay.jedco.model.response.DataItem;
import com.quickpay.jedco.model.response.DataItem;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.model.response.VendPaymentResponse;
import com.quickpay.jedco.print.PrintActivity;
import com.quickpay.jedco.ui.auth.LoginActivity;
/*import com.quickpay.jedco.ui.history.SummaryPrintActivity;
import com.quickpay.jedco.ui.history.vend.TransactionHistoryActivity;
import com.quickpay.jedco.ui.history.vend.VendHistoryDetailActivity;
import com.quickpay.jedco.ui.history.wallet.WalletHistoryActivity;*/
import com.quickpay.jedco.ui.history.vend.TransactionHistoryActivity;
import com.quickpay.jedco.ui.history.vend.VendHistoryDetailActivity;
import com.quickpay.jedco.ui.history.wallet.WalletHistoryActivity;
import com.quickpay.jedco.ui.main.MainActivity;
/*import com.quickpay.jedco.ui.vending.SearchBTActivity;*/
import com.quickpay.jedco.ui.vending.SummaryActivity;
import com.quickpay.jedco.ui.vending.VendActivity;
import com.quickpay.jedco.ui.vending.VendDetailsActivity;


import javax.inject.Inject;

public class AppCoordinator {

    @Inject
    AppCoordinator() {

    }

    public void replaceFrag(AppCompatActivity activity, int i, Fragment fragment) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(i, fragment);
        ft.commit();
    }

    public void launchMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void launchLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
    public void launchVendActivity(Context context) {
        Intent intent = new Intent(context, VendActivity.class);
        context.startActivity(intent);
    }

    public void launchVendDetailsActivity(Context context, MeterDetailResponse response) {
        Intent intent = new Intent(context, VendDetailsActivity.class);
         intent.putExtra("detailResponse", response);
        context.startActivity(intent);
    }
    public void launchSummaryActivity(Context context, VendPaymentResponse response) {
        Intent intent = new Intent(context, SummaryActivity.class);
        intent.putExtra("metResponse", response);
        context.startActivity(intent);
    }
    public  void launchFundHistoryActivity(Context context){
        Intent intent = new Intent(context, WalletHistoryActivity.class);
        context.startActivity(intent);
    }
    public void launchTransactionHistoryActivity(Context context) {
        Intent intent = new Intent(context, TransactionHistoryActivity.class);
        context.startActivity(intent);
    }

    public void launchHistorySummaryActivity(Context context, VendPaymentResponse response) {
        Intent intent = new Intent(context, VendHistoryDetailActivity.class);
        intent.putExtra("metResponse", response);
        context.startActivity(intent);
    }

    public void launchPrintActivity(Context context, VendPaymentResponse response) {
        Intent intent = new Intent(context, PrintActivity.class);
       intent.putExtra("metResponse", response);
        context.startActivity(intent);
    }
}
