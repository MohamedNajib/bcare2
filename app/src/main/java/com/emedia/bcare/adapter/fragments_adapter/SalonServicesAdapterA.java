package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.salon_services.SalonServicesData;
import com.emedia.bcare.interfaces.listeners.OnSectionListener;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SalonServicesAdapterA extends RecyclerView.Adapter<SalonServicesAdapterA.SalonServicesHolderA> {

    private Context mContext;
    private List<SalonServicesData> mSalonServicesDataList;
    private List<String> mCategoryName;
    private TextViewCustomFont mPriceText;
    OnSectionListener listener;

    public SalonServicesAdapterA(Context mContext,
                                 List<SalonServicesData> mSalonServicesDataList,
                                 List<String> mCategoryName,
                                 TextViewCustomFont priceText,
                                 OnSectionListener listener) {
        this.mContext = mContext;
        this.mSalonServicesDataList = mSalonServicesDataList;
        this.mCategoryName = mCategoryName;
        this.mPriceText = priceText;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SalonServicesHolderA onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SalonServicesHolderA(LayoutInflater.from(mContext).inflate(R.layout.item_sections_a,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SalonServicesHolderA salonServicesHolderA, final int i) {

        // set TextView Category Name
        salonServicesHolderA.TVItemSectionsName.setText(mCategoryName.get(i));

        String s = mCategoryName.get(i);

        List<SalonServicesData> mList = new ArrayList<>();
        mList.clear();

        for (int k = 0; k < mSalonServicesDataList.size(); k++) {

            if (mSalonServicesDataList.get(k).getCatName().equals(s)){
                mList.add(mSalonServicesDataList.get(k));
            } else {
                mList.remove(mSalonServicesDataList.get(k));
            }
        }


        // set Services RecyclerView
        SalonServicesAdapterB sectionsAdapterB = new SalonServicesAdapterB(mContext,
                mList,
                mPriceText,
                listener);
        salonServicesHolderA.RVItemSections.setHasFixedSize(true);
        salonServicesHolderA.RVItemSections.setLayoutManager(new GridLayoutManager(mContext, 4));
        salonServicesHolderA.RVItemSections.setNestedScrollingEnabled(false);
        salonServicesHolderA.RVItemSections.setAdapter(sectionsAdapterB);
    }

    @Override
    public int getItemCount() {
        return mCategoryName.size();
    }

    public class SalonServicesHolderA extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.TV_ItemSectionsName)
        TextViewCustomFont TVItemSectionsName;
        @BindView(R.id.RV_ItemSections)
        RecyclerView RVItemSections;

        private final View view;

        public SalonServicesHolderA(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
