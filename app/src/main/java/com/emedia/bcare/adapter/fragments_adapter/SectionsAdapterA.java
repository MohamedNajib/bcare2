package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;

import com.emedia.bcare.data.model.api_model.service.Service_;
import com.example.fontutil.TextViewCustomFont;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionsAdapterA extends RecyclerView.Adapter<SectionsAdapterA.SectionsHolder> {

    private Context mContext;
    private List<Service_> mSectionsList;
    private List<String> mCategoryName;

    public SectionsAdapterA(Context mContext, List<Service_> mSectionsList, List<String> mCategoryName) {
        this.mContext = mContext;
        this.mSectionsList = mSectionsList;
        this.mCategoryName = mCategoryName;
    }

    @NonNull
    @Override
    public SectionsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SectionsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sections_a, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionsHolder sectionsHolder, int i) {

        // set TextView Category Name
        sectionsHolder.TVItemSectionsName.setText(mCategoryName.get(i));

            String s = mCategoryName.get(i);

            List<Service_> mList = new ArrayList<>();
            mList.clear();

            for (int k = 0; k < mSectionsList.size(); k++) {

                if (mSectionsList.get(k).getCatName().equals(s)){
                    mList.add(mSectionsList.get(k));
                } else {
                    mList.remove(mSectionsList.get(k));
                }
            }

            // set Services RecyclerView
            SectionsAdapterB sectionsAdapterB = new SectionsAdapterB(mContext, mList);
            sectionsHolder.RVItemSections.setHasFixedSize(true);
            sectionsHolder.RVItemSections.setLayoutManager(new GridLayoutManager(mContext, 4));
            sectionsHolder.RVItemSections.setNestedScrollingEnabled(false);
            sectionsHolder.RVItemSections.setAdapter(sectionsAdapterB);

    }

    @Override
    public int getItemCount() {
        return mCategoryName.size();
    }

    public class SectionsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.TV_ItemSectionsName)
        TextViewCustomFont TVItemSectionsName;
        @BindView(R.id.RV_ItemSections)
        RecyclerView RVItemSections;

        private final View view;

        public SectionsHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
