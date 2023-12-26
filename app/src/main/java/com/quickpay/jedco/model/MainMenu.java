package com.quickpay.jedco.model;


import android.graphics.drawable.Drawable;

public class MainMenu {

    private final String name;
    Drawable icon;

    public MainMenu(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }
}
