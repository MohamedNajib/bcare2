package com.emedia.bcare.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.emedia.bcare.R;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.EditTextCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    @BindView(R.id.Spinner_SignUpPhone_)
    Spinner SpinnerSignUpPhone;
    @BindView(R.id.ET_SignUpPhone_)
    EditTextCustomFont ETSignUpPhone;
    Unbinder unbinder;

    @BindView(R.id.BTN_BottomSheet)
    ButtonCustomFont BTNBottomSheet;

    private View mLayoutBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);

        mLayoutBottomSheet = view.findViewById(R.id.layout_bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(mLayoutBottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_DRAGGING);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;

                    case BottomSheetBehavior.STATE_DRAGGING:
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        final CoordinatorLayout coordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        ImageView cancel = mLayoutBottomSheet.findViewById(R.id.IV_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                coordinatorLayout.setVisibility(View.GONE);
            }
        });

//        ButtonCustomFont buttonCustomFont = view.findViewById(R.id.BTN_BottomSheet);
//        buttonCustomFont.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HelperMethod.showToast(getContext(), "good");
//                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                coordinatorLayout.setVisibility(View.VISIBLE);
//            }
//        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_SignUp_, R.id.TV_SignUP_HaveAccountLink, R.id.BTN_BottomSheet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_SignUp_:
                break;
            case R.id.TV_SignUP_HaveAccountLink:
                break;
            case R.id.BTN_BottomSheet:
                HelperMethod.showToast(getContext(), "good");
                break;

        }
    }
}
