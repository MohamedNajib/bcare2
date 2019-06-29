package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.ButtonCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscountCode extends Fragment {


    @BindView(R.id.BTN_GetYourDiscount)
    ButtonCustomFont BTNGetYourDiscount;
    Unbinder unbinder;

    public DiscountCode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount_code2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_GetYourDiscount)
    public void onViewClicked() {
        ((HomeActivity) getActivity()).changeFragment(18);
        ((HomeActivity) getActivity()).heden();
    }
}
