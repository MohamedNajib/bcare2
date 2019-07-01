package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.SalonItemSliderAdapter;

import com.emedia.bcare.data.model.api_model.favorite.Favorite;
import com.emedia.bcare.data.model.api_model.salons.SalonData;
import com.emedia.bcare.data.model.api_model.salons.SalonImage;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.ui.custom.ClickableViewPager;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class SelectSalonAdapter extends RecyclerView.Adapter<SelectSalonAdapter.SelectSalonHolder> {

    private Context mContext;
    private List<SalonData> mSalonDataList;
    private OnClickItem mOnClickItem;

    public SelectSalonAdapter(Context mContext, List<SalonData> mSalonDataList, OnClickItem mOnClickItem) {
        this.mContext = mContext;
        this.mSalonDataList = mSalonDataList;
        this.mOnClickItem = mOnClickItem;
    }

    @NonNull
    @Override
    public SelectSalonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SelectSalonHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_select_salon, viewGroup, false),
                mOnClickItem
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSalonHolder selectSalonHolder, final int i) {
        SalonData salonData = mSalonDataList.get(i);

        // Send Image to Slider Adapter && set ViewPager Adapter
        SalonItemSliderAdapter salonItemSliderAdapter = new SalonItemSliderAdapter(mContext, getImages(salonData));
        selectSalonHolder.SliderViewPager.setAdapter(salonItemSliderAdapter);

        // Set Circle Indicator
        selectSalonHolder.SliderIndicator.setViewPager(selectSalonHolder.SliderViewPager);

        // Add ServiceData into Views
        selectSalonHolder.TVSalonName.setText(salonData.getName());
        selectSalonHolder.TVSalonAddress.setText(salonData.getAddress());
        selectSalonHolder.TVSalonServiceName.setText(salonData.getServiceName());
        selectSalonHolder.TVSalonMainPrice.setText(salonData.getMinPrice());

        // Favorite States
        if (salonData.getIsFavorite() == null){
            selectSalonHolder.IVLikeSalon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_like));

        }else {
            selectSalonHolder.IVLikeSalon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_liked));
        }

        // RatingBar
        if (salonData.getSalonRate() == null){
            selectSalonHolder.RATSalonCountRate.setRating(0);

        }else {
            selectSalonHolder.RATSalonCountRate.setRating(Float.valueOf(String.valueOf(salonData.getSalonRate())));
        }

        if (salonData.getCountRate() == null){
            selectSalonHolder.TVSalonRate.setText(String.valueOf(0));
        }else {
            selectSalonHolder.TVSalonRate.setText(String.valueOf(salonData.getCountRate()));
        }

        selectSalonHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickItem.onViewPagerClicked(i);
            }
        });
    }

    /**
     * get String List Of Salon Images URL
     */
    private List<String> getImages(SalonData salonData) {
        List<String> salonImages = new ArrayList<>();
        List<SalonImage> salonImage = salonData.getSalonImage();
        for (int i = 0; i < salonImage.size(); i++) {
            salonImages.add(salonImage.get(i).getImage());
        }
        return salonImages;
    }


    @Override
    public int getItemCount() {
        return mSalonDataList.size();
    }

    public class SelectSalonHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ClickableViewPager.OnItemClickListener {

        @BindView(R.id.Slider_ViewPager)
        ClickableViewPager SliderViewPager;
        @BindView(R.id.TV_SalonServiceName)
        TextViewCustomFont TVSalonServiceName;
        @BindView(R.id.TV_SalonName)
        TextViewCustomFont TVSalonName;
        @BindView(R.id.TV_SalonAddress)
        TextViewCustomFont TVSalonAddress;
        @BindView(R.id.TV_SalonRate)
        TextViewCustomFont TVSalonRate;
        @BindView(R.id.RAT_SalonCountRate)
        RatingBar RATSalonCountRate;
        @BindView(R.id.TV_SalonMainPrice)
        TextViewCustomFont TVSalonMainPrice;
        @BindView(R.id.SliderIndicator)
        CircleIndicator SliderIndicator;

        @BindView(R.id.BTN_Booking)
        ButtonCustomFont BTNBooking;
        @BindView(R.id.IV_ShareSalon)
        ImageView IVShareSalon;
        @BindView(R.id.IV_LikeSalon)
        ImageView IVLikeSalon;

        private final View view;
        private OnClickItem mOnClickItem;

        public SelectSalonHolder(@NonNull View itemView, OnClickItem onClickItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickItem = onClickItem;

            if (Locale.getDefault().getLanguage().equals("ar")) {
                IVShareSalon.setRotationY(mContext.getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            } else {
                IVShareSalon.setRotationY(mContext.getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            }

            BTNBooking.setOnClickListener(this);
            IVShareSalon.setOnClickListener(this);
            IVLikeSalon.setOnClickListener(this);
            view.setOnClickListener(this);
            SliderViewPager.setOnItemClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch(v.getId()){
                case R.id.BTN_Booking:
                    if (mOnClickItem != null && position != RecyclerView.NO_POSITION) {
                        mOnClickItem.onBookingClicked(position);
                    }
                    break;

                case R.id.IV_ShareSalon:
                    if (mOnClickItem != null && position != RecyclerView.NO_POSITION) {
                        mOnClickItem.onShareClicked(position);
                    }
                    break;

                case R.id.IV_LikeSalon:
                    if (mOnClickItem != null && position != RecyclerView.NO_POSITION) {
                        mOnClickItem.onLikeClicked(position, IVLikeSalon);
                    }
                    break;
            }
            if (v == view){
                if (mOnClickItem != null && position != RecyclerView.NO_POSITION) {
                    mOnClickItem.onViewPagerClicked(position);
                }
            }
        }

        @Override
        public void onItemClick(int position) {
            int itemPosition = getAdapterPosition();
            if (mOnClickItem != null && position != RecyclerView.NO_POSITION) {
                mOnClickItem.onViewPagerClicked(itemPosition);
            }
        }
    }

    public interface OnClickItem{
        void onBookingClicked(int position);
        void onShareClicked(int position);
        void onLikeClicked(int position, ImageView IV_LikeSalon);
        void onViewPagerClicked(int position);
    }
}
