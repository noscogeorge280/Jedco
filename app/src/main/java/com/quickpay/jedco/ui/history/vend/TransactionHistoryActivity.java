package com.quickpay.jedco.ui.history.vend;
import static com.quickpay.jedco.ui.history.vend.PaginationListener.PAGE_START;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.quickpay.jedco.R;
import com.quickpay.jedco.model.response.*;
import com.quickpay.jedco.ui.history.HistoryViewModel;
import com.quickpay.jedco.ui.history.OnVendHistoryClickListener;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.HelperRegistry;
import com.quickpay.jedco.util.Pref;
import com.quickpay.jedco.viewmodels.ViewModelProviderFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import com.quickpay.jedco.ui.history.*;


public class TransactionHistoryActivity extends DaggerAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnVendHistoryClickListener {

    private final int totalPage = 30;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    int itemCount = 0;
    @Inject
    ViewModelProviderFactory providerFactory;
    ProgressBar progressBar;
    @Inject
    HistoryViewModel viewModel;
    @Inject
    Pref pref;
    @Inject
    AppCoordinator coordinator;
    @Inject
    HelperRegistry helperRegistry;
    RecyclerView rv;
    private TransactionRecyclerAdapter adapter;
    private int currentPage = PAGE_START;
    // ActivityTransactionHistoryBinding binding;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    //ActivityTransactionHistoryBinding binding;
    private LinearLayoutManager mLayoutManager;

    private int currentApiVersion;
    private String TAG;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        viewModel = ViewModelProviders.of(this, providerFactory).get(HistoryViewModel.class);
        ButterKnife.bind(this);
        swipeRefresh.setOnRefreshListener(this);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new TransactionRecyclerAdapter(this, this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {

               isLoading = true;
                currentPage++;
                doApiCall();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        doApiCall();
    }

    private void doApiCall() {
        viewModel.vendHistoryLog(pref.getUser(this).getValue().getToken(), currentPage, totalPage).observe(this, response -> {
                if(response !=null){
                    List<VendPaymentResponse> results = fetchResults(response);

                    double pageCount = (response.getTotalCount() / 30);

                    if (currentPage != PAGE_START) adapter.removeLoading();
                    adapter.addItems(results);
                    swipeRefresh.setRefreshing(false);
                    isLoading=false;
                    // check weather is last page or not
                    if (response.getHasNext() == false) {
                        isLoading = false;

                        isLastPage = true;
                    }
                    if (currentPage < totalPage) {
                        adapter.addLoading();
                    } else {
                        isLastPage = true;
                    }

                }
    });
    }

    private List<VendPaymentResponse> fetchResults(TransactionHistoryResponse response) {
        TransactionHistoryResponse topRatedMovies = response;
        return topRatedMovies.getData();
    }


    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        adapter.clear();
        doApiCall();
    }

    @Override
    public void onVendHistoryClickListener(VendPaymentResponse vendResponse) {
        coordinator.launchHistorySummaryActivity(this, vendResponse);
    }
}
