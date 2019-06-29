package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.booking.TimeAtHome;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingTimeAtHomeAdapter extends RecyclerView.Adapter<BookingTimeAtHomeAdapter.BookingTimeAtHomeHolder> {

    private Context mContext;
    private List<TimeAtHome> mTimeAtHome;
    private OnClickTimeItem mOnClickTimeItem;

    public BookingTimeAtHomeAdapter(Context mContext, List<TimeAtHome> mmTimeAtHome, OnClickTimeItem mOnClickTimeItem) {
        this.mContext = mContext;
        this.mTimeAtHome = mmTimeAtHome;
        this.mOnClickTimeItem = mOnClickTimeItem;
    }

    @NonNull
    @Override
    public BookingTimeAtHomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookingTimeAtHomeHolder(LayoutInflater.from(mContext).inflate(R.layout.item_time_specialist, viewGroup, false),
                mOnClickTimeItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingTimeAtHomeHolder bookingTimeAtHomeHolder, int i) {
        TimeAtHome timeAtHome = mTimeAtHome.get(i);

        bookingTimeAtHomeHolder.TVTimeText.setText(timeAtHome.getTime());
        bookingTimeAtHomeHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backggw));

        if (timeAtHome.isCheced()){
            bookingTimeAtHomeHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backg));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bookingTimeAtHomeHolder.TVTimeText.setElevation(4);
            }
            bookingTimeAtHomeHolder.TVTimeText.setTextColor(mContext.getResources().getColor(R.color.gray));
        }else {
            bookingTimeAtHomeHolder.TVTimeText.setBackground(mContext.getResources().getDrawable(R.drawable.item_backggw));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                bookingTimeAtHomeHolder.TVTimeText.setElevation(0);
            }
            bookingTimeAtHomeHolder.TVTimeText.setTextColor(mContext.getResources().getColor(R.color.t_color));
        }
    }

    @Override
    public int getItemCount() {
        return mTimeAtHome.size();
    }


    public class BookingTimeAtHomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_TimeText)
        TextView TVTimeText;

        private OnClickTimeItem mOnClickTimeItem;

        private final View view;
        public BookingTimeAtHomeHolder(@NonNull View itemView, OnClickTimeItem onClickTimeItem) {
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
                        mOnClickTimeItem.onTimeAtHomeClicked(position, TVTimeText);
                    }
                    break;
            }
        }
    }

    public interface OnClickTimeItem {
        void onTimeAtHomeClicked(int position, TextView tv);
    }

}
