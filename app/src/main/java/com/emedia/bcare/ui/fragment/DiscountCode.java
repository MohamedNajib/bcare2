package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

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

    private TextViewCustomFont mToolBarTitle;
    private ImageView mToolBarIconBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount_code2, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar sectionsBar = view.findViewById(R.id.DiscountTB);
        sectionsBar.setBackground(ContextCompat.getDrawable((HomeActivity) getActivity(), R.drawable.review_background_));
        mToolBarTitle = sectionsBar.findViewById(R.id.TV_Title);
        mToolBarIconBack = sectionsBar.findViewById(R.id.IV_Back);

        mToolBarTitle.setText(((HomeActivity) getActivity()).getResources().getString(R.string.discount_code));
        mToolBarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethod.intentTo((HomeActivity) getActivity(), HomeActivity.class);
            }
        });

        if (((HomeActivity) getActivity()).getResources().getString(R.string.current_lang).equals("ar")) {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        initialize();
        return view;
    }

    protected void initialize() {
        ((HomeActivity) getActivity()).hideBottomToolbar();
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
