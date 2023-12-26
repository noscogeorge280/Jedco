package com.quickpay.jedco.ui.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.quickpay.jedco.R;
import com.quickpay.jedco.databinding.FragmentHomeBinding;
import com.quickpay.jedco.model.MainMenu;
import com.quickpay.jedco.ui.main.MainViewModel;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HomeFragment extends DaggerFragment implements MyMenuRecyclerViewAdapter.OnMenuInteractionListener {

    FragmentHomeBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    @Inject
    HelperRegistry helperRegistry;

    private MainViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);

        List<MainMenu> menuList = new ArrayList<>();

        menuList.add(new MainMenu("Purchase", getResources().getDrawable(R.drawable.light_bulb)));
        menuList.add(new MainMenu("My Account", getResources().getDrawable(R.drawable.myaccount)));
        menuList.add(new MainMenu("Transactions", getResources().getDrawable(R.drawable.transaction)));
        menuList.add(new MainMenu("Wallet", getResources().getDrawable(R.drawable.wallet)));

        binding.menu.setLayoutManager(new GridLayoutManager(binding.getRoot().getContext(), 2));
        binding.menu.setAdapter(new MyMenuRecyclerViewAdapter(menuList, this));

        refreshDashboard();

        return binding.getRoot();
    }

    private void refreshDashboard() {
        homeViewModel.dashboard(pref.getUser(getContext()).getValue().getToken()).observe(getViewLifecycleOwner(), dashboardResponse -> {
            binding.txtAccountBalance.setText(helperRegistry.formatCurrency("" + dashboardResponse.getData().getWalletBalance()));
            binding.txtDailySells.setText(helperRegistry.formatCurrency("" + dashboardResponse.getData().getDailySales()));
            binding.txtMonthlySells.setText(helperRegistry.formatCurrency("" + dashboardResponse.getData().getMonthlySales()));
        });
    }

    @Override
    public void onMenuInteractionListener(MainMenu mainMenu) {
        switch (mainMenu.getName()) {
            case "Purchase":
                coordinator.launchVendActivity(getContext());
                break;
           /* case "My Account":
                coordinator.launchPrintActivity(getContext());
                break;*/
            case "Transactions":
                coordinator.launchTransactionHistoryActivity(getContext());
                break;
            case "Wallet":
                coordinator.launchFundHistoryActivity(getContext());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshDashboard();
    }
}
