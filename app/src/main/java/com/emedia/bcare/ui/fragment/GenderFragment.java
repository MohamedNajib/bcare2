package com.emedia.bcare.ui.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.ButtonCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.emedia.bcare.util.HelperMethod.showToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenderFragment extends Fragment {

//    @BindView(R.id.BTN_Women)
//    ButtonCustomFont BTNWomen;
//    @BindView(R.id.BTN_Men)
//    ButtonCustomFont BTNMen;
//    @BindView(R.id.BTN_Children)
//    ButtonCustomFont BTNChildren;
//    @BindView(R.id.IV_ContinueIcon)
//    ImageView IVContinueIcon;
//    Unbinder unbinder;
//
//    //var
//    public static int mGender = 0;
//
//
//    public GenderFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_gender, container, false);
//        unbinder = ButterKnife.bind(this, view);
//
//        if (((HomeActivity) getActivity()).getResources().getString(R.string.current_lang).equals("ar")) {
//            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
//        } else {
//            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
//        }
//        return view;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @OnClick({R.id.BTN_Women, R.id.BTN_Men, R.id.BTN_Children, R.id.CL_GenderContinue})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.BTN_Women:
//                mGender = 1;
//                setButtonStates(BTNWomen, R.drawable.ic_woman_g, R.drawable.et_w, R.color.Green);
//                setButtonStates(BTNMen, R.drawable.ic_man, R.drawable.et_f, R.color.white);
//                BTNChildren.setBackground(getContext().getResources().getDrawable(R.drawable.et_f));
//                BTNChildren.setTextColor(getContext().getResources().getColor(R.color.white));
//                break;
//
//            case R.id.BTN_Men:
//                mGender = 2;
//                setButtonStates(BTNMen, R.drawable.ic_man_g, R.drawable.et_w, R.color.Green);
//                setButtonStates(BTNWomen, R.drawable.ic_woman, R.drawable.et_f, R.color.white);
//                BTNChildren.setBackground(getContext().getResources().getDrawable(R.drawable.et_f));
//                BTNChildren.setTextColor(getContext().getResources().getColor(R.color.white));
//                break;
//
//            case R.id.BTN_Children:
//                mGender = 3;
//                setButtonStates(BTNWomen, R.drawable.ic_woman, R.drawable.et_f, R.color.white);
//                BTNChildren.setBackground(getContext().getResources().getDrawable(R.drawable.et_w));
//                BTNChildren.setTextColor(getContext().getResources().getColor(R.color.Green));
//                setButtonStates(BTNMen, R.drawable.ic_man, R.drawable.et_f, R.color.white);
//                break;
//
//            case R.id.CL_GenderContinue:
//                if (mGender != 0) {
//                    startActivity(new Intent(getActivity(), HomeActivity.class));
//                    //((HomeActivity) getActivity()).changeFragment(1);
//                } else {
//                    showToast(getContext(), "Choose Gender Type");
//                }
//                break;
//        }
//    }
//
//    private void setButtonStates(ButtonCustomFont btn, int drawableEnd, int background, int textColor) {
//        Drawable img = getContext().getResources().getDrawable(drawableEnd);
//        btn.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
//        btn.setBackground(getContext().getResources().getDrawable(background));
//        btn.setTextColor(getContext().getResources().getColor(textColor));
//    }
//
//    public static int getGenderType() {
//        return mGender;
//    }
}
