package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.my_event.PreviousAppointment;
import com.emedia.bcare.data.model.api_model.my_event.SalonServiceDetail;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviousEventAdapter extends RecyclerView.Adapter<PreviousEventAdapter.PreviousEventHolder> {

    private Context mContext;
    private List<PreviousAppointment> mPreviousAppointments;
    private OnClickEventItem mOnClickEventItem;

    public PreviousEventAdapter(Context mContext, List<PreviousAppointment> mNextAppointments, OnClickEventItem mOnClickEventItem) {
        this.mContext = mContext;
        this.mPreviousAppointments = mNextAppointments;
        this.mOnClickEventItem = mOnClickEventItem;
    }

    @NonNull
    @Override
    public PreviousEventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PreviousEventHolder(LayoutInflater.from(mContext).inflate(R.layout.item_last_events, viewGroup, false),
                mOnClickEventItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousEventHolder previousEventHolder, int i) {
        PreviousAppointment previousAppointment = mPreviousAppointments.get(i);
        if (Locale.getDefault().getLanguage().equals("ar")) {
            previousEventHolder.IVSalonImage.setRotationY(mContext.getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            previousEventHolder.IVBackground.setRotationY(mContext.getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            previousEventHolder.IVSalonImage.setRotationY(mContext.getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            previousEventHolder.IVBackground.setRotationY(mContext.getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        previousEventHolder.TVServiceDetails.setText(gerServiceDetails(previousAppointment));
        previousEventHolder.TVSalonAddress.setText(previousAppointment.getSalon().getSalonAddress());
    }

    /**
     * ger String Service Details
     */
    private String gerServiceDetails(PreviousAppointment previousAppointment) {
        List<SalonServiceDetail> salonServiceDetail = previousAppointment.getSalonServiceDetails();
        StringBuilder builder = new StringBuilder();
        for (SalonServiceDetail service : salonServiceDetail) {
            builder.append(service.getServiceName() + " ,");
        }
        return builder.toString();
    }


    @Override
    public int getItemCount() {
        return mPreviousAppointments.size();
    }

    public class PreviousEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_SalonImage)
        ImageView IVSalonImage;
        @BindView(R.id.TV_ServiceDetails)
        TextViewCustomFont TVServiceDetails;
        @BindView(R.id.TV_SalonAddress)
        TextViewCustomFont TVSalonAddress;
        @BindView(R.id.TV_EventDate)
        TextViewCustomFont TVEventDate;
        @BindView(R.id.IV_Background)
        ImageView IVBackground;
        private final View view;
        private OnClickEventItem mOnClickEventItem;

        public PreviousEventHolder(@NonNull View itemView, OnClickEventItem onClickEventItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickEventItem = onClickEventItem;
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnClickEventItem {
        void onEventClicked(int position);
    }
}
