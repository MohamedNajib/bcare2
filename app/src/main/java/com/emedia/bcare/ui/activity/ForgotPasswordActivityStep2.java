package com.emedia.bcare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.util.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.emedia.bcare.util.HelperMethod.intentTo;

public class ForgotPasswordActivityStep2 extends AppCompatActivity {

    @BindView(R.id.IV_BackIconForgot2)
    ImageView IVBackIconForgot2;

    public static String mEmail = null;
    public static String getmEmail() {
        return mEmail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step2);
        ButterKnife.bind(this);
        if (getResources().getString(R.string.current_lang).equals("ar")) {
            IVBackIconForgot2.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackIconForgot2.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mEmail = getIntent().getStringExtra("FORGOT_PASSWORD_EMAIL");
    }

    @OnClick({R.id.BTN_Ending, R.id.IV_BackIconForgot2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Ending:
                if (!mEmail.equals("") && mEmail != null) {
                    intentTo(ForgotPasswordActivityStep2.this, ForgotPasswordVerification.class);
                }
                break;
            case R.id.IV_BackIconForgot2:
                HelperMethod.intentTo(this, ForgotPasswordActivityStep1.class);
                break;
        }
    }
}
