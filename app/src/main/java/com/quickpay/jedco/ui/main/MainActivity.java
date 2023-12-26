package com.quickpay.jedco.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.quickpay.jedco.R;
import com.quickpay.jedco.ui.main.ui.changepassword.ChangePasswordFragment;
import com.quickpay.jedco.ui.main.ui.home.HomeFragment;
import com.quickpay.jedco.util.AppCoordinator;
import com.quickpay.jedco.util.Pref;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Inject
    Pref pref;
    @Inject
    AppCoordinator coordinator;
    DrawerLayout drawer;
    private TextView txtEmail, fullname;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_change_password)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // navController = Navigation.findNavController(this, R.id.nav_change_password);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        UpdateNavHeader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                pref.saveUser(this, null);
                coordinator.launchLoginActivity(this);
                break;
            case R.id.nav_home:
                HomeFragment hfragment = new HomeFragment();

                coordinator.replaceFrag(this, R.id.nav_host_fragment, hfragment);
                break;
            case R.id.nav_change_password:
                ChangePasswordFragment cfragment = new ChangePasswordFragment();
                coordinator.replaceFrag(this, R.id.nav_host_fragment, cfragment);
                break;
            case R.id.nav_vend:
                coordinator.launchVendActivity(this);
                break;
            case R.id.nav_wallet:
                coordinator.launchFundHistoryActivity(this);
                break;
            case R.id.nav_history:
                coordinator.launchTransactionHistoryActivity(this);
                break;


        }
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
// Close the navigation drawer
        drawer.closeDrawers();
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_change_password:
                System.out.println("Select Item :: " + id);
                ChangePasswordFragment cfragment = new ChangePasswordFragment();
                coordinator.replaceFrag(this, R.id.nav_host_fragment, cfragment);

                break;
            case R.id.action_logout:
                pref.saveUser(this, null);
                coordinator.launchLoginActivity(this);
                break;
        }
        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        return super.onOptionsItemSelected(item);
    }

    public void UpdateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
      /*  txtEmail = headerView.findViewById(R.id.txtAgentEmail);
        fullname = headerView.findViewById(R.id.txtAgentFullName);
        ImageView navPhoto = headerView.findViewById(R.id.imageView);
        fullname.setText(pref.getUser(this).getUserName());
        txtEmail.setText(pref.getUser(this).getEmail());*/
    }
}