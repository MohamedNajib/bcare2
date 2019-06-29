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
import com.emedia.bcare.data.model.api_model.specialist_info.SpecialistReview;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SpecialistReviewAdapter extends RecyclerView.Adapter<SpecialistReviewAdapter.SpecialistReviewHolder> {
    private Context mContext;
    private List<SpecialistReview> mSpecialistReview;
    private OnSpecialistReviewClick mOnSpecialistReviewClick;

    public SpecialistReviewAdapter(Context mContext, List<SpecialistReview> specialistReview, OnSpecialistReviewClick mOnSpecialistReviewClick) {
        this.mContext = mContext;
        this.mSpecialistReview = specialistReview;
        this.mOnSpecialistReviewClick = mOnSpecialistReviewClick;
    }

    @NonNull
    @Override
    public SpecialistReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpecialistReviewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_reviews_special, viewGroup, false),
                mOnSpecialistReviewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistReviewHolder specialistReview, int i) {
        SpecialistReview specialistReview1 = mSpecialistReview.get(i);

        Glide.with(mContext).load(specialistReview1.getUser().getProfilePicture())
                .into(specialistReview.CIVSpecialistReviewImage);

        specialistReview.TVSpecialistReviewUserName.setText(specialistReview1.getUser().getName());
        specialistReview.TVSpecialistReviewDescription.setText(specialistReview1.getDescription());

        if (specialistReview1.getRate() == null){
            specialistReview.RBSpecialistReview.setRating(0);
        }else {
            specialistReview.RBSpecialistReview.setRating(Float.parseFloat(specialistReview1.getRate()));
        }


    }

    @Override
    public int getItemCount() {
        return mSpecialistReview.size();
    }

    public class SpecialistReviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.CIV_SpecialistReviewImage)
        CircleImageView CIVSpecialistReviewImage;
        @BindView(R.id.RB_SpecialistReview)
        RatingBar RBSpecialistReview;
        @BindView(R.id.TV_SpecialistReviewUserName)
        TextViewCustomFont TVSpecialistReviewUserName;
        @BindView(R.id.TV_SpecialistReviewDescription)
        TextViewCustomFont TVSpecialistReviewDescription;

        //Buttons
        @BindView(R.id.BTN_UnUseful)
        ButtonCustomFont BTNUnUseful;
        @BindView(R.id.BTN_Useful)
        ButtonCustomFont BTNUseful;

        private final View view;
        private OnSpecialistReviewClick mOnSpecialistReviewClick;

        public SpecialistReviewHolder(@NonNull View itemView, OnSpecialistReviewClick onSpecialistReviewClick) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnSpecialistReviewClick = onSpecialistReviewClick;
            BTNUnUseful.setOnClickListener(this);
            BTNUseful.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()){
                case R.id.BTN_Useful:
                    if (mOnSpecialistReviewClick != null && position != RecyclerView.NO_POSITION) {
                        mOnSpecialistReviewClick.onUsefulClicked(position);
                    }
                    break;

                case R.id.BTN_UnUseful:
                    if (mOnSpecialistReviewClick != null && position != RecyclerView.NO_POSITION) {
                        mOnSpecialistReviewClick.onUnUsefulClicked(position);
                    }
                    break;
            }
        }
    }

    public interface OnSpecialistReviewClick {
        void onUnUsefulClicked(int position);
        void onUsefulClicked(int position);
    }
}
