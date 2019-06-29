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

public class DatesRecyclerViewAdapter extends RecyclerView.Adapter<DatesRecyclerViewAdapter.RecyclerViewHolder> {


    private List<DateTimePickerModel> arrayList;
    private Context context;
    private ItemListener mListener;

    public DatesRecyclerViewAdapter(List<DateTimePickerModel> arrayList, Context context, ItemListener mListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.
                from(viewGroup.getContext()).inflate(R.layout.item_booking, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        DateTimePickerModel model = arrayList.get(recyclerViewHolder.getAdapterPosition());
        recyclerViewHolder.myDay.setText(model.day);
        recyclerViewHolder.myDate.setText(model.date);

        if (model.highlghted) {
            recyclerViewHolder.ll.setBackground(context.getResources().getDrawable(R.drawable.dd));
            recyclerViewHolder.myDate.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            recyclerViewHolder.ll.setBackgroundColor(Color.TRANSPARENT);
            recyclerViewHolder.myDate.setTextColor(context.getResources().getColor(R.color.white));
        }


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.myDay)
        TextView myDay;
        @BindView(R.id.myDate)
        TextView myDate;
        @BindView(R.id.ll)
        ConstraintLayout ll;

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
}
