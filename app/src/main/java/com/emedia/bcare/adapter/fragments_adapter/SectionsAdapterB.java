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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.service.Service_;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionsAdapterB extends RecyclerView.Adapter<SectionsAdapterB.SectionsHolderB> {

    private Context mContext;
    private List<Service_> mSectionsList;

    public static int mTotalPrice;
    public static List<String> mServicesIdList;

    public SectionsAdapterB(Context mContext, List<Service_> mSectionsList) {
        this.mContext = mContext;
        this.mSectionsList = mSectionsList;
    }

    @NonNull
    @Override
    public SectionsHolderB onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SectionsHolderB(LayoutInflater.from(mContext).inflate(R.layout.item_sections_b, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final SectionsHolderB sectionsHolderB, int i) {
        final Service_ serviceData = mSectionsList.get(i);

        sectionsHolderB.TVItemSectionsB.setText(serviceData.getName());
        Glide.with(mContext).load(serviceData.getImage()).into(sectionsHolderB.IVItemSectionsB);
        sectionsHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.itemGray));
        sectionsHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.itemGray));

        mServicesIdList = new ArrayList<>();
        sectionsHolderB.CLServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (serviceData.isChick()) {
                    serviceData.setChick(false);
                    mServicesIdList.remove(serviceData.getId());

                    sectionsHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.itemGray));
                    sectionsHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.itemGray));
                    sectionsHolderB.CLServices.setBackground(mContext.getResources().getDrawable(R.drawable.hear));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        sectionsHolderB.CLServices.setElevation(0);
                    }
                } else {
                    serviceData.setChick(true);
                    mServicesIdList.add(serviceData.getId());

                    sectionsHolderB.IVItemSectionsB.setColorFilter(mContext.getResources().getColor(R.color.tap_blue));
                    sectionsHolderB.TVItemSectionsB.setTextColor(mContext.getResources().getColor(R.color.tap_blue));
                    sectionsHolderB.CLServices.setBackground(mContext.getResources().getDrawable(R.drawable.item_b_ba));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        sectionsHolderB.CLServices.setElevation(8);
                    }
                }
               // Toast.makeText(mContext, String.valueOf(serviceData.isChick()), Toast.LENGTH_SHORT).show();

//                Toast.makeText(mContext, String.valueOf(sectionsHolderB.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSectionsList.size();
    }

    public class SectionsHolderB extends RecyclerView.ViewHolder {
        @BindView(R.id.CL_Services)
        ConstraintLayout CLServices;
        @BindView(R.id.IV_ItemSectionsB)
        ImageView IVItemSectionsB;
        @BindView(R.id.TV_ItemSectionsB)
        TextViewCustomFont TVItemSectionsB;

        private final View view;

        public SectionsHolderB(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
