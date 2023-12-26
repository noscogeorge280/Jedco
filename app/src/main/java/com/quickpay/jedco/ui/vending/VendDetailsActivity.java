package com.quickpay.jedco.ui.vending;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.ActivityVendDetailsBinding;
import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class VendDetailsActivity extends DaggerAppCompatActivity {

    MeterDetailResponse meterResponse;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    VendViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;

    @Inject
    Pref pref;

    @Inject
    AppCoordinator coordinator;

    LoginResponse user;

    ActivityVendDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vend_details);

        viewModel = ViewModelProviders.of(this, providerFactory).get(VendViewModel.class);

        meterResponse = (MeterDetailResponse) getIntent().getSerializableExtra("detailResponse");

          user = pref.getUser(this);

        binding.amount.setText(helperRegistry.formatCurrency(String.valueOf(meterResponse.getAmount())));

        binding.accoNumber.setText("" + meterResponse.getAccount_number());
        binding.creditBalance.setText(helperRegistry.formatCurrency(String.valueOf(meterResponse.getOutstanding())));
        binding.customerName.setText(meterResponse.getCustomer_name());
        binding.address.setText(meterResponse.getCustomer_address());
        binding.meterType.setText(meterResponse.getCustomer_type());
        binding.total.setText(helperRegistry.formatCurrency(String.valueOf(meterResponse.getAmount())));

        binding.proceed.setOnClickListener(view -> {
            helperRegistry.showProgress(this, true);
            viewModel.vendEnergy(meterResponse, user.getValue().getToken()).observe(this, vendResponse -> {
                helperRegistry.showProgress(this, false);
                if (vendResponse.getStatus() ==100) {
                    coordinator.launchSummaryActivity(this, vendResponse.getData());

                } else {
                    helperRegistry.showERRORDialog(this, vendResponse.getMessage());
                }

            });
        });
    }
}