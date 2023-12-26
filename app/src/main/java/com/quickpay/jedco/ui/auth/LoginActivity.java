package com.quickpay.jedco.ui.auth;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.ActivityLoginBinding;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    AuthViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        boolean isOnline = helperRegistry.isNetworkConnected(getApplicationContext());
        if (isOnline == false) {
            helperRegistry.showNetworkDialog(this, true);
        }
        binding.login.setOnClickListener(view -> attemptSubmit());

    }

    private void attemptSubmit() {
        helperRegistry.hideSoftKeyboard(this);
//Reset Error
        binding.username.setError(null);
        binding.password.setError(null);

        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

//Validate Data
        if (!helperRegistry.isEmpty(password) && !helperRegistry.isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            focusView = binding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (helperRegistry.isEmpty(username)) {
            binding.username.setError(getString(R.string.error_field_required));
            focusView = binding.username;
            cancel = true;
        }

        if (helperRegistry.isEmpty(password)) {
            binding.password.setError(getString(R.string.error_field_required));
            focusView = binding.password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            helperRegistry.showProgress(this, true);
            viewModel.login(username, password).observe((LifecycleOwner) this, loginResponse -> {
                helperRegistry.showProgress(this, false);

                if (loginResponse.getErrorDescription() != null) {

                    String newMessage = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        newMessage = String.valueOf((Html.fromHtml(loginResponse.getErrorDescription(), Html.FROM_HTML_MODE_LEGACY)));
                        System.out.println("newMessage :: " + newMessage);
                        helperRegistry.showERRORDialog(this, newMessage);
                    } else {
                        newMessage = String.valueOf((Html.fromHtml(loginResponse.getErrorDescription())));

                        helperRegistry.showERRORDialog(this, newMessage);

                    }


                } else {
                    pref.saveUser(this, loginResponse);
                    coordinator.launchMainActivity(this);
                }
            });
        }

    }
}
