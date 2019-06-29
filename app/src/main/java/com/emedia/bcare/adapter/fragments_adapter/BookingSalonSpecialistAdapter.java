package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.booking.Specialist_;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class BookingSalonSpecialistAdapter extends RecyclerView.Adapter<BookingSalonSpecialistAdapter.BookingSalonSpecialistHolder> {
    @BindView(R.id.constraintLayout5)
    ConstraintLayout constraintLayout5;
    private Context mContext;
    private List<Specialist_> specialist_;
    private OnClickTimeItem mOnClickTimeItem;

    public BookingSalonSpecialistAdapter(Context mContext, List<Specialist_> specialist_, OnClickTimeItem onClickTimeItem) {
        this.mContext = mContext;
        this.specialist_ = specialist_;
        this.mOnClickTimeItem = onClickTimeItem;
    }

    @NonNull
    @Override
    public BookingSalonSpecialistHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookingSalonSpecialistHolder(LayoutInflater.from(mContext).inflate(R.layout.item_in_the_shop, viewGroup, false),
                mOnClickTimeItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingSalonSpecialistHolder bookingSalonSpecialistHolder, int i) {
        Specialist_ specialist = specialist_.get(i);
        Glide.with(mContext).load(specialist.getImage()).into(bookingSalonSpecialistHolder.CIVSpecializedImage);
        bookingSalonSpecialistHolder.TVSpecializedName.setText(specialist.getName());
        bookingSalonSpecialistHolder.constraintLayout5.setBackground(mContext.getResources().getDrawable(R.drawable.at_w));

        if (specialist.isCheced()) {
            bookingSalonSpecialistHolder.constraintLayout5.setBackground(mContext.getResources().getDrawable(R.drawable.at_g));
            bookingSalonSpecialistHolder.TVSpecializedName.setTextColor(mContext.getResources().getColor(R.color.tap_blue));
        } else {
            bookingSalonSpecialistHolder.constraintLayout5.setBackground(mContext.getResources().getDrawable(R.drawable.at_w));
            bookingSalonSpecialistHolder.TVSpecializedName.setTextColor(mContext.getResources().getColor(R.color.ccc));
        }
    }

    @Override
    public int getItemCount() {
        return specialist_.size();
    }

    public class BookingSalonSpecialistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.CIV_SpecializedImage)
        CircleImageView CIVSpecializedImage;
        @BindView(R.id.TV_SpecializedName)
        TextViewCustomFont TVSpecializedName;
        @BindView(R.id.constraintLayout5)
        ConstraintLayout constraintLayout5;
        private final View view;
        private OnClickTimeItem mOnClickTimeItem;

        public BookingSalonSpecialistHolder(@NonNull View itemView, OnClickTimeItem onClickTimeItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickTimeItem = onClickTimeItem;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (v == view) {
                if (mOnClickTimeItem != null && position != RecyclerView.NO_POSITION) {
                    mOnClickTimeItem.onSpecializedClicked(specialist_.get(position), position);
                }
            }
        }
    }

    public interface OnClickTimeItem {
        void onSpecializedClicked(Specialist_ specialist, int position);
    }
}
