package com.quickpay.jedco.ui.history.wallet;

import android.os.Bundle;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.ActivityWalletHistoryBinding;
import com.quickpay.jedco.model.response.WalletResponse;
import com.quickpay.jedco.ui.history.HistoryViewModel;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class WalletHistoryActivity extends DaggerAppCompatActivity {
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    HistoryViewModel viewModel;

    @Inject
    Pref pref;

    @Inject
    AppCoordinator coordinator;

    @Inject
    HelperRegistry helperRegistry;

    ActivityWalletHistoryBinding binding;
    int pageNumber = 1, pageSize = 30;
    private LinearLayoutManager mLayoutManager;
    private WalletPaginationAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet_history);

        viewModel = ViewModelProviders.of(this, providerFactory).get(HistoryViewModel.class);


        getHistory(pageNumber, pageSize);
        binding.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                binding.vendProgress.setVisibility(View.VISIBLE);
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    pageSize++;
                    getHistory(pageNumber, pageSize);

                }
            }
        });
    }

    private void getHistory(int pageNumber, int pageSize) {
        viewModel.walletLog(pref.getUser(this).getValue().getToken(), pageNumber, pageSize).observe(this, walletResponses -> {
            if (walletResponses != null) {
                binding.vendProgress.setVisibility(View.GONE);
                mLayoutManager = new LinearLayoutManager(this);
                binding.fundWalletList.setLayoutManager(mLayoutManager);
                System.out.println(walletResponses.toString());
                mAdapter = new WalletPaginationAdapter((ArrayList<WalletResponse>) walletResponses.getData(), helperRegistry);
                binding.fundWalletList.setAdapter(mAdapter);
            }
        });
    }

}
