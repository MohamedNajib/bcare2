package com.emedia.bcare.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.home.Category;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesHolder> {

    private Context mContext;
    private List<Category> mCategoriesList;
    private OnClickCategoriesItem mOnClickCategoriesItem;

    public HomeCategoriesAdapter(Context mContext, List<Category> mCategoriesList, OnClickCategoriesItem mOnClickCategoriesItem) {
        this.mContext = mContext;
        this.mCategoriesList = mCategoriesList;
        this.mOnClickCategoriesItem = mOnClickCategoriesItem;
    }

    @NonNull
    @Override
    public HomeCategoriesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HomeCategoriesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sections_b, viewGroup, false),
                mOnClickCategoriesItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesHolder homeCategoriesHolder, int i) {
        Category category = mCategoriesList.get(i);
        Glide.with(mContext).load(category.getImage()).into(homeCategoriesHolder.IVItemSectionsB);
        homeCategoriesHolder.TVItemSectionsB.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public class HomeCategoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.IV_ItemSectionsB)
        ImageView IVItemSectionsB;
        @BindView(R.id.TV_ItemSectionsB)
        TextViewCustomFont TVItemSectionsB;
        private OnClickCategoriesItem mOnClickCategoriesItem;
        private final View view;

        public HomeCategoriesHolder(@NonNull View itemView, OnClickCategoriesItem onClickCategoriesItem) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
            this.mOnClickCategoriesItem = onClickCategoriesItem;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (mOnClickCategoriesItem != null && position != RecyclerView.NO_POSITION) {
                mOnClickCategoriesItem.onCategoriesItemClicked(position);
            }

        }
    }

    public interface OnClickCategoriesItem {
        void onCategoriesItemClicked(int position);
    }
}
