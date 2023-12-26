package com.quickpay.jedco.ui.main.ui.changepassword;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.FragmentChangepasswordBinding;
import com.quickpay.jedco.model.request.ChangePasswordRequest;
import com.quickpay.jedco.model.response.LoginResponse;
import com.quickpay.jedco.ui.main.MainViewModel;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;

public class ChangePasswordFragment extends DaggerFragment {


    @Inject
    MainViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;
    @Inject
    Pref pref;


    FragmentChangepasswordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_changepassword, container, false);

        //mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        LoginResponse user = pref.getUser(binding.getRoot().getContext());


        binding.changePassword.setOnClickListener(v -> {

        });
      /*  galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return binding.getRoot();
    }

    private void attemptSubmit() {
        helperRegistry.hideSoftKeyboard(getActivity());
//Reset Error
        binding.confirmPassword.setError(null);
        binding.newPassword.setError(null);
        binding.currentPassword.setError(null);

        String rePassword = binding.confirmPassword.getText().toString();
        String password = binding.newPassword.getText().toString();
        String oldPassword = binding.currentPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

//Validate Data
        if (!helperRegistry.isEmpty(password) && !helperRegistry.isPasswordValid(password)) {
            binding.newPassword.setError(getString(R.string.error_invalid_password));
            focusView = binding.newPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (helperRegistry.isEmpty(rePassword)) {
            binding.confirmPassword.setError(getString(R.string.error_field_required));
            focusView = binding.confirmPassword;
            cancel = true;
        }

        if (helperRegistry.isEmpty(oldPassword)) {
            binding.currentPassword.setError(getString(R.string.error_field_required));
            focusView = binding.currentPassword;
            cancel = true;
        }

        if (helperRegistry.isEmpty(password)) {
            binding.newPassword.setError(getString(R.string.error_field_required));
            focusView = binding.newPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            helperRegistry.showProgress(getActivity(), true);

            ChangePasswordRequest request = new ChangePasswordRequest();
            request.setNewPassword(password);
            request.setConfirmPassword(rePassword);
            request.setCurrentPassword(oldPassword);

         /*   viewModel.changePassword(request, pref.getUser(getContext()).getToken()).observe(this, passwordResponse -> {
                helperRegistry.showProgress(getActivity(), false);
                if (passwordResponse.getErrorDescription() != null) {
                    helperRegistry.makeToast(getContext(), passwordResponse.getErrorDescription());
                } else {
                    helperRegistry.makeToast(getContext(), "Password change successful!");
                    getActivity().finish();
                }
            });*/
        }

    }
}