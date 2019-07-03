package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.salon_services.SalonServicesData;
import com.emedia.bcare.interfaces.listeners.OnSectionListener;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalonServicesAdapterB extends RecyclerView.Adapter<SalonServicesAdapterB.SalonServicesHolderB> {

    private Context mContext;
    private List<SalonServicesData> mSalonServicesDataList;
    private TextViewCustomFont mPriceText;
    public static float mTotalPrice;

    public static float getmTotalPrice() {
        return mTotalPrice;
    }


    public static List<String> mServicesIdList;
    OnSectionListener listener;

    public SalonServicesAdapterB(Context mContext,
                                 List<SalonServicesData> mSSalonServicesDataList,
                                 TextViewCustomFont priceText,
                                 OnSectionListener listener) {
        this.mContext = mContext;
        this.mSalonServicesDataList = mSSalonServicesDataList;
        this.mPriceText = priceText;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SalonServicesHolderB onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SalonServicesHolderB(LayoutInflater.from(mContext).inflate(R.layout.item_sections_b, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SalonServicesHolderB salonServicesHolderB, int i) {
        final SalonServicesData salonServicesData = mSalonServicesDataList.get(i);

        salonServicesHolderB.TVItemSectionsB.setText(salonServicesData.getName());
        Glide.with(mContext).load(salonServicesData.getImage()).into(salonServicesHolderB.IVItemSectionsB);
        salonServicesHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.itemGray));
        salonServicesHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.itemGray));

        mServicesIdList = new ArrayList<>();
        salonServicesHolderB.CLServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (salonServicesData.isChick()) {
                    salonServicesData.setChick(false);

                    mServicesIdList.remove(salonServicesData.getId());

                    mTotalPrice -= Integer.valueOf(salonServicesData.getPrice());
                    mPriceText.setText(String.valueOf(mTotalPrice) + " $");
                    salonServicesHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.itemGray));
                    salonServicesHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.itemGray));
                    salonServicesHolderB.CLServices.setBackground(mContext.getResources().getDrawable(R.drawable.hear));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        salonServicesHolderB.CLServices.setElevation(0);
                    }
                } else {
                    salonServicesData.setChick(true);

                    mServicesIdList.add(salonServicesData.getId());
                    listener.onSectionClick(mServicesIdList);


                    mTotalPrice += Integer.valueOf(salonServicesData.getPrice());
                    mPriceText.setText(String.valueOf(mTotalPrice) + " $");
                    salonServicesHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.tap_blue));
                    salonServicesHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.tap_blue));
                    salonServicesHolderB.CLServices.setBackground(mContext.getResources().getDrawable(R.drawable.item_b_ba));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        salonServicesHolderB.CLServices.setElevation(8);
                    }
                }

//                StringBuilder builder = new StringBuilder();
//                for (String details : mServicesId) {
//                    builder.append(details + "\n");
//                }
//                Toast.makeText(mContext, builder.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSalonServicesDataList.size();
    }


    public class SalonServicesHolderB extends RecyclerView.ViewHolder {
        @BindView(R.id.CL_Services)
        ConstraintLayout CLServices;
        @BindView(R.id.IV_ItemSectionsB)
        ImageView IVItemSectionsB;
        @BindView(R.id.TV_ItemSectionsB)
        TextViewCustomFont TVItemSectionsB;

        private final View view;

        public SalonServicesHolderB(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

    }

    public static List<String> getmServicesIdList() {
        return mServicesIdList;
    }

}
