package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.booking.TimeAtSalon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingTimeAtSalonAdapter extends RecyclerView.Adapter<BookingTimeAtSalonAdapter.BookingHolder> {

    private Context mContext;
    private List<TimeAtSalon> mTimeAtSalonsList;
    private OnClickTimeItem mOnClickTimeItem;

    public BookingTimeAtSalonAdapter(Context mContext, List<TimeAtSalon> mTimeAtSalonsList, OnClickTimeItem mOnClickTimeItem) {
        this.mContext = mContext;
        this.mTimeAtSalonsList = mTimeAtSalonsList;
        this.mOnClickTimeItem = mOnClickTimeItem;
    }

    @NonNull
    @Override
    public BookingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookingHolder(LayoutInflater.from(mContext).inflate(R.layout.item_time_specialist, viewGroup, false),
                mOnClickTimeItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHolder bookingHolder, int i) {
        TimeAtSalon timeAtSalon = mTimeAtSalonsList.get(i);
        bookingHolder.TVTimeText.setText(timeAtSalon.getTime());
        bookingHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backggw));

        if (timeAtSalon.isCheced()){
            bookingHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backg));
        }else {
            bookingHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backggw));
        }
    }

    @Override
    public int getItemCount() {
        return mTimeAtSalonsList.size();
    }

    public class BookingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_TimeText)
        TextView TVTimeText;

        private OnClickTimeItem mOnClickTimeItem;

        private final View view;
        public BookingHolder(@NonNull View itemView, OnClickTimeItem onClickTimeItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickTimeItem = onClickTimeItem;

            TVTimeText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()){
                case R.id.TV_TimeText:
                    if (mOnClickTimeItem != null && position != RecyclerView.NO_POSITION) {
                        mOnClickTimeItem.onTimeAtSalonClicked(position, TVTimeText);
                    }
                    break;
            }
        }
    }

    public interface OnClickTimeItem {
        void onTimeAtSalonClicked(int position, TextView tv);
    }
}
