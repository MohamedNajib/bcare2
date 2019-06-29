package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.my_event.NextAppointment;
import com.emedia.bcare.data.model.api_model.my_event.SalonServiceDetail_;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NextEventAdapter extends RecyclerView.Adapter<NextEventAdapter.NextEventHolder> {
    private Context mContext;
    private List<NextAppointment> mNextAppointments;
    private OnClickEventItem mOnClickEventItem;

    public NextEventAdapter(Context mContext, List<NextAppointment> mNextAppointments, OnClickEventItem mOnClickEventItem) {
        this.mContext = mContext;
        this.mNextAppointments = mNextAppointments;
        this.mOnClickEventItem = mOnClickEventItem;
    }

    @NonNull
    @Override
    public NextEventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NextEventHolder(LayoutInflater.from(mContext).inflate(R.layout.item_upcoming_events, viewGroup, false),
                mOnClickEventItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NextEventHolder nextEventHolder, int i) {
        NextAppointment nextAppointment = mNextAppointments.get(i);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            nextEventHolder.IVYellow.setRotationY(mContext.getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            nextEventHolder.IVYellow.setRotationY(mContext.getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        System.out.println("ret myevnt address : " + nextAppointment.getSalon().getSalonAddress());
        System.out.println("ret myevnt date : " + nextAppointment.getReservationDate());

        nextEventHolder.TVSalonServiceDetails.setText(gerServiceDetails(nextAppointment));
        nextEventHolder.TVSalonAddress.setText(nextAppointment.getSalon().getSalonAddress());

    }

    /**
     * ger String Service Details
     */
    private String gerServiceDetails(NextAppointment nextAppointment) {
        List<SalonServiceDetail_> salonServiceDetail_s = nextAppointment.getSalonServiceDetails();
        StringBuilder builder = new StringBuilder();
        for (SalonServiceDetail_ service : salonServiceDetail_s) {
            builder.append(service.getServiceName() + " ,");
        }
        return builder.toString();
    }

    @Override
    public int getItemCount() {
        return mNextAppointments.size();
    }

    public class NextEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.TV_SalonServiceDetails)
        TextViewCustomFont TVSalonServiceDetails;
        @BindView(R.id.TV_SalonAddress)
        TextViewCustomFont TVSalonAddress;
        @BindView(R.id.IV_SalonMobileNumber)
        ImageView IVSalonMobileNumber;
        @BindView(R.id.IV_yellow)
        ImageView IVYellow;
        private final View view;
        private OnClickEventItem mOnClickEventItem;

        public NextEventHolder(@NonNull View itemView, OnClickEventItem onClickEventItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickEventItem = onClickEventItem;
            IVSalonMobileNumber.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            switch (v.getId()){
                case R.id.IV_SalonMobileNumber:
                    if (mOnClickEventItem != null && position != RecyclerView.NO_POSITION) {
                        mOnClickEventItem.onSalonPhoneCallClicked(position);
                    }
                    break;
            }
        }
    }

    public interface OnClickEventItem {
        void onSalonPhoneCallClicked(int position);
    }
}
