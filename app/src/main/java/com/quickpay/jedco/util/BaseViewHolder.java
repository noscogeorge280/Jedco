package com.quickpay.jedco.util;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;

    //private DataItem mCurrentData;
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position/*, DataItem dataItem*/) {
        mCurrentPosition = position;
        // mCurrentData = dataItem;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
    // public DataItem getCurrentData() {
    //  return mCurrentData;
    //  }
}