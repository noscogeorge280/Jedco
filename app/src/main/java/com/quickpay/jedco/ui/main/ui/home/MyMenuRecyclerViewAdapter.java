package com.quickpay.jedco.ui.main.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.quickpay.jedco.R;
import com.quickpay.jedco.model.MainMenu;

import java.util.List;


public class MyMenuRecyclerViewAdapter extends RecyclerView.Adapter<MyMenuRecyclerViewAdapter.ViewHolder> {

    private final List<MainMenu> mValues;
    private final OnMenuInteractionListener mListener;


    public MyMenuRecyclerViewAdapter(List<MainMenu> items, OnMenuInteractionListener mListener) {
        mValues = items;
        this.mListener = mListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mName.setText(holder.mItem.getName());

        holder.mIcon.setImageDrawable(holder.mItem.getIcon());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onMenuInteractionListener(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    interface OnMenuInteractionListener {

        void onMenuInteractionListener(MainMenu mainMenu);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIcon;
        public final TextView mName;
        public MainMenu mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.name);
            mIcon = view.findViewById(R.id.image);
        }

    }
}
