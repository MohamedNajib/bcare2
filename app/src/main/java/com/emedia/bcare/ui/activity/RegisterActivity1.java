package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.emedia.bcare.R;
import com.example.fontutil.EditTextCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.emedia.bcare.Constants.FragmentsKeys.TO_REG_ACTIVITY2;
import static com.emedia.bcare.util.HelperMethod.intentTo;


public class RegisterActivity1 extends AppCompatActivity {

    @BindView(R.id.Spinner_SignUpPhone_)
    Spinner SpinnerSignUpPhone;
    @BindView(R.id.ET_SignUpPhone_)
    EditTextCustomFont ETSignUpPhone;
    @BindView(R.id.ET_SignUpUserName)
    EditTextCustomFont ETSignUpUserName;
    @BindView(R.id.IV_ContinueIconSignPyPh)
    ImageView IVContinueIcon;
    @BindView(R.id.IV_BackToMainRegister)
    ImageView IVBackToMainRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVBackToMainRegister.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVBackToMainRegister.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    @OnClick({R.id.IV_BackToMainRegister, R.id.CL_BTN_Continue, R.id.TV_SignUP1_HaveAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegister:
                intentTo(this, RegisterActivity.class);
                break;
            case R.id.CL_BTN_Continue:
                Intent toRegisterActivity2 = new Intent(this, RegisterActivity2.class);
                toRegisterActivity2.putExtra(TO_REG_ACTIVITY2, "ByPhone");
                startActivity(toRegisterActivity2);
                break;
            case R.id.TV_SignUP1_HaveAccountLink:
                break;
        }
    }
}
