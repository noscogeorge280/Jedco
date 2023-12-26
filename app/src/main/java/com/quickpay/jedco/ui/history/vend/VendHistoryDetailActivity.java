package com.quickpay.jedco.ui.history.vend;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.ActivitySummaryBinding;
import com.quickpay.jedco.model.response.DataItem;
import com.quickpay.jedco.model.response.VendPaymentResponse;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import androidx.databinding.DataBindingUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class VendHistoryDetailActivity extends DaggerAppCompatActivity {
    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final String[] wifi_PERMISSIONS = {
            "android.permission.CHANGE_WIFI_STATE",
            "android.permission.ACCESS_WIFI_STATE"
    };
    private final Spinner spnPrinterList = null;
    private final Button btnOpenCashDrawer = null;
    private final Button btnSampleReceipt = null;

    private final EditText edtTimes = null;
    private final String ConnectType = "";
    private final String PrinterName = "";
    private final String PortParam = "";
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    String baudrate = "";
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    ActivitySummaryBinding binding;
    VendPaymentResponse response;
    @Inject
    HelperRegistry helperRegistry;
    @Inject
    AppCoordinator coordinator;
    private BluetoothAdapter mBluetoothAdapter;
    private TextView txtTips, txtAccount, txtReceiptNo, txtName, txtAddress, txtState, txtAmountPaid, txtAcountType, txtTransactionDate;
    private ArrayAdapter arrPrinterList;
    private String date, walletId, Pin, address, name, meter, state;
    private Double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_summary);

        response = (VendPaymentResponse) getIntent().getSerializableExtra("metResponse");
        binding.amount.setText(helperRegistry.formatCurrency(String.valueOf(response.getAmount())));
        binding.unit.setText("" + response.getUnits());
        binding.vat.setText("" + response.getVat());
        binding.tariffRate.setText("" + response.getTariff_rate());
        binding.token.setText(response.getToken());
        binding.meterNumber.setText("" + response.getMeter_number());
        binding.accountNumber.setText("" + response.getAccount_number());
        binding.arrearsBalance.setText(helperRegistry.formatCurrency(String.valueOf(response.getOutstanding())));
        binding.sCustomerName.setText(response.getCustomer_name());
        binding.customerAddress.setText(response.getCustomer_address());
        binding.total.setText(helperRegistry.formatCurrency(String.valueOf(response.getAmount())));

        binding.print.setOnClickListener(view -> {
           coordinator.launchPrintActivity(this, response);
        });

    }

}