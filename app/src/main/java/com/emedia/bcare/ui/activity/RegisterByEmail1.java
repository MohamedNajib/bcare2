package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.example.fontutil.EditTextCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.emedia.bcare.Constants.FragmentsKeys.TO_REG_ACTIVITY2;
import static com.emedia.bcare.util.HelperMethod.intentTo;


public class RegisterByEmail1 extends AppCompatActivity {

    @BindView(R.id.IV_BackToMainRegisterByEmail1)
    ImageView IVBackToMainRegisterByEmail1;
    @BindView(R.id.IV_ContinueIconSignPyEm)
    ImageView IVContinueIconSignPyEm;

    @BindView(R.id.ET_RegisterByEmail1)
    EditTextCustomFont ETRegisterByEmail1;
    @BindView(R.id.ET_RegisterByEmail1Name)
    EditTextCustomFont ETRegisterByEmail1Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_email1);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVContinueIconSignPyEm.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVBackToMainRegisterByEmail1.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVContinueIconSignPyEm.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVBackToMainRegisterByEmail1.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    @OnClick({R.id.IV_BackToMainRegisterByEmail1, R.id.CL_BTN_ContinueRegisterByEmail1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegisterByEmail1:
                intentTo(this, RegisterActivity.class);
                break;
            case R.id.CL_BTN_ContinueRegisterByEmail1:
                Intent toRegisterActivity2 = new Intent(this, RegisterActivity2.class);
                toRegisterActivity2.putExtra(TO_REG_ACTIVITY2, "ByEmail");
                startActivity(toRegisterActivity2);

                break;
        }
    }
}