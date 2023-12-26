package com.quickpay.jedco.ui.vending;


import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.ActivityVendBinding;
import com.quickpay.jedco.model.request.MeterDetailsRequest;
import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.model.response.MeterDetailResponse;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.CurrencyTextWatcher;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class VendActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    VendViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;
    int LAYOUT_FLAG;

    LoginResponse user;

    ActivityVendBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_vend);
        viewModel = ViewModelProviders.of(this, providerFactory).get(VendViewModel.class);

        binding.proceed.setOnClickListener(v -> {
            attemptSubmit();
        });
         user = pref.getUser(this);

        binding.amount.addTextChangedListener(new CurrencyTextWatcher(binding.amount));


    }


    private void attemptSubmit() {

        helperRegistry.hideSoftKeyboard(this);
//Reset Error
        binding.meterNumber.setError(null);
        binding.amount.setError(null);

        String meterNumber = binding.meterNumber.getText().toString();
        String amount = binding.amount.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (helperRegistry.isEmpty(meterNumber)) {
            binding.meterNumber.setError(getString(R.string.error_field_required));
            focusView = binding.meterNumber;
            cancel = true;
        }



        if (helperRegistry.isEmpty(amount)) {
            binding.amount.setError(getString(R.string.error_field_required));
            focusView = binding.amount;
            cancel = true;
        }



        if (cancel) {
            if (focusView != null)
                focusView.requestFocus();
        } else {

            helperRegistry.showProgress(this, true);
            MeterDetailsRequest request = new MeterDetailsRequest();
            request.setMeterNumber(meterNumber);
            request.setAmount(Double.parseDouble(amount.replace(",", "")));

            viewModel.getMeterDetails(request,user.getValue().getToken()).observe(this, response -> {
                helperRegistry.showProgress(this, false);

                if (response.getStatus() == 100) {

                    coordinator.launchVendDetailsActivity(this, response.getData());
                } else {
                    helperRegistry.showERRORDialog(this, response.getMessage());
                }
            });
        }

    }


}