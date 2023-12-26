package com.quickpay.jedco.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.quickpay.jedco.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HelperRegistry {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    @Inject
    public HelperRegistry() {
    }

    public void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    public boolean doStringsMatch(String s1, String s2) {
        return s1.equals(s2);
    }


    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void showProgress(Activity activity, boolean show) {
        if (dialogBuilder == null) {
            dialogBuilder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.progress, null);
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.setCancelable(false);
        }

        if (show) {
            alertDialog.show();
        } else {
            alertDialog.dismiss();
        }


    }


    public void showErroDialog(Activity activity, boolean show, String text) {
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Error")
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .show();
    }

    public void showERRORDialog(Activity activity, String text) {
        new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops..")
                .setContentText(text)
                .show();
    }

    public void showNetworkDialog(Activity activity, boolean show) {
        new AlertDialog.Builder(activity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Connection lost?")
                .setMessage("Please check your internet connection!")
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isNetworkConnected(activity.getApplicationContext());
                    }
                })
                .show();
    }

    public void makeToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public boolean isNetworkConnected(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public String formatCurrency(String amount) {
        try {
            // The comma in the format specifier does the trick
            amount = "₦ " + String.format("%,.2f", Float.parseFloat(amount));
        } catch (Exception e) {
            e.printStackTrace();
            return amount;
        }

        return amount;
    }

    public String formatImputeCurrency(String amount) {
        try {
            System.out.println("Amount: " + amount);
            // The comma in the format specifier does the trick
            amount = (String.format("%,d", Long.parseLong(amount))).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return amount;
        }

        return amount;
    }

    public double getDoubleFromString(final String value) throws ParseException {
        String newvalue = value.replace(",", ".");
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(newvalue);
        return number.doubleValue();
    }
}

