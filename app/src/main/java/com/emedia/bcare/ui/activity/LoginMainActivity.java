package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.emedia.bcare.util.HelperMethod.intentTo;


public class LoginMainActivity extends AppCompatActivity {

    @BindView(R.id.layout_bottom_sheet)
    ConstraintLayout layoutBottomSheet;

    private View mLayoutBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);

        mLayoutBottomSheet = findViewById(R.id.layout_bottom_sheet);

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

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.BTN_LoginPyPhoneNumber, R.id.BTN_LoginPyEmile, R.id.TV_CreateAccountLink, R.id.TV_ForGotPassLink,
            R.id.BTN_LoginBottomSheet, R.id.IV_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_LoginPyPhoneNumber:
                intentTo(this, LoginPyPhoneActivity.class);
                break;
            case R.id.BTN_LoginPyEmile:
                intentTo(this, LoginPyEmailActivity.class);
                break;
            case R.id.TV_CreateAccountLink:
                intentTo(this, RegisterActivity.class);
                break;
            case R.id.TV_ForGotPassLink:
                intentTo(this, ForgotPasswordActivityStep1.class);
                break;
            case R.id.BTN_LoginBottomSheet:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.IV_cancel:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }
}
