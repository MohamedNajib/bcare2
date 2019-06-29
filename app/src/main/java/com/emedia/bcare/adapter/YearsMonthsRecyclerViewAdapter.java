package com.emedia.bcare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.DateTimePickerModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YearsMonthsRecyclerViewAdapter extends RecyclerView.Adapter<YearsMonthsRecyclerViewAdapter.RecyclerViewHolder> {


    private List<DateTimePickerModel> arrayList;
    private Context context;
    private ItemListener mListener;

    public YearsMonthsRecyclerViewAdapter(List<DateTimePickerModel> arrayList, Context context, ItemListener mListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.
                from(viewGroup.getContext()).inflate(R.layout.item_years_month, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        DateTimePickerModel model = arrayList.get(recyclerViewHolder.getAdapterPosition());
        recyclerViewHolder.tv_year_month.setText(model.day);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_year_month)
        TextView tv_year_month;
        private final View view;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(arrayList.get(getAdapterPosition()));
            }
        }
    }

    public interface ItemListener {
        void onItemClick(DateTimePickerModel model);
    }

    public void increment()
    {
        //if(po)
    }
}
