package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.home.Advertisement;
import com.example.fontutil.ButtonCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdvertisementsAdapter extends RecyclerView.Adapter<HomeAdvertisementsAdapter.HomeAdvertisementsHolder> {
    private Context mContext;
    private List<Advertisement> mAdvertisementsList;
    private OnClickAdvertisementItem mOnClickAdvertisementItem;

    public HomeAdvertisementsAdapter(Context mContext, List<Advertisement> mAdvertisementsList, OnClickAdvertisementItem mOnClickAdvertisementItem) {
        this.mContext = mContext;
        this.mAdvertisementsList = mAdvertisementsList;
        this.mOnClickAdvertisementItem = mOnClickAdvertisementItem;
    }

    @NonNull
    @Override
    public HomeAdvertisementsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeAdvertisementsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_discounts, viewGroup, false),
                mOnClickAdvertisementItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdvertisementsHolder homeAdvertisementsHolder, int i) {
        Advertisement advertisement = mAdvertisementsList.get(i);
        Glide.with(mContext).load(advertisement.getImage()).into(homeAdvertisementsHolder.IVAdvertisements);
    }

    @Override
    public int getItemCount() {
        return mAdvertisementsList.size();
    }

    public class HomeAdvertisementsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_Advertisements)
        ImageView IVAdvertisements;
        @BindView(R.id.BTN_Booking)
        ButtonCustomFont BTNBooking;
        private OnClickAdvertisementItem mOnClickAdvertisementItem;
        private final View view;

        public HomeAdvertisementsHolder(@NonNull View itemView, OnClickAdvertisementItem onClickAdvertisementItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickAdvertisementItem = onClickAdvertisementItem;
            BTNBooking.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            switch (v.getId()) {
                case R.id.BTN_Booking:
                    if (mOnClickAdvertisementItem != null && position != RecyclerView.NO_POSITION){
                        mOnClickAdvertisementItem.onBookingButtonClicked(position);
                    }
                    break;
            }
        }
    }

    public interface OnClickAdvertisementItem {
        void onBookingButtonClicked(int position);
    }

}
