package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.home.Specialist;
import com.emedia.bcare.ui.custom.RoundRectCornerImageView;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomSpecialistsAdapter extends RecyclerView.Adapter<HomSpecialistsAdapter.HomSpecialistsHolder> {
    private Context mContext;
    private List<Specialist> mSpecialistList;
    private OnClickSpecialistsItem mOnClickSpecialistsItem;

    public HomSpecialistsAdapter(Context mContext, List<Specialist> mSpecialistList, OnClickSpecialistsItem mOnClickSpecialistsItem) {
        this.mContext = mContext;
        this.mSpecialistList = mSpecialistList;
        this.mOnClickSpecialistsItem = mOnClickSpecialistsItem;
    }

    @NonNull
    @Override
    public HomSpecialistsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomSpecialistsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_specialists, viewGroup, false),
                mOnClickSpecialistsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomSpecialistsHolder homSpecialistsHolder, int i) {
        Specialist specialist = mSpecialistList.get(i);
        Glide.with(mContext).load(specialist.getImage()).into(homSpecialistsHolder.IVSpecialistImage);
        homSpecialistsHolder.TVSpecialistName.setText(specialist.getName());
        homSpecialistsHolder.TVSpecialistSpecialty.setText(specialist.getDesc());
        if (specialist.getSpecialistRate() == null){
            homSpecialistsHolder.RBSpecialistRat.setRating(0);
        }else {
            homSpecialistsHolder.RBSpecialistRat.setRating(Float.parseFloat(specialist.getSpecialistRate()));
        }
    }

    @Override
    public int getItemCount() {
        return mSpecialistList.size();
    }

    public class HomSpecialistsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_SpecialistImage)
        RoundRectCornerImageView IVSpecialistImage;
        @BindView(R.id.TV_SpecialistName)
        TextViewCustomFont TVSpecialistName;
        @BindView(R.id.TV_SpecialistSpecialty)
        TextViewCustomFont TVSpecialistSpecialty;
        @BindView(R.id.RB_SpecialistRat)
        RatingBar RBSpecialistRat;
        private OnClickSpecialistsItem mOnClickSpecialistsItem;
        private final View view;

        public HomSpecialistsHolder(@NonNull View itemView, OnClickSpecialistsItem onClickSpecialistsItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickSpecialistsItem = onClickSpecialistsItem;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (v == view) {
                if (mOnClickSpecialistsItem != null && position != RecyclerView.NO_POSITION) {
                    mOnClickSpecialistsItem.onSpecialistsClicked(position);
                }
            }
        }
    }

    public interface OnClickSpecialistsItem {
        void onSpecialistsClicked(int position);
    }
}
