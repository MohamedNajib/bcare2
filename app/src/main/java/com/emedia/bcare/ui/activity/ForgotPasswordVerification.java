package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.chaos.view.PinView;
import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.checkcode.CheckCode;
import com.emedia.bcare.data.model.api_model.checkcode.CheckCodeDatum;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.HelperMethod;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.showToast;

public class ForgotPasswordVerification extends AppCompatActivity {

    @BindView(R.id.IV_BackIconForgotVerification)
    ImageView IVBackIconForgotVerification;
    @BindView(R.id.pinViewForgotVerification)
    PinView pinViewForgotVerification;
    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_verfication);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVBackIconForgotVerification.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackIconForgotVerification.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    private void checkVerificationCode(String email, String code){
        showLoading();
        Call<CheckCode> checkCodeCall = RetrofitClient.getInstance().getApiServices().checkCode(email, code);
        checkCodeCall.enqueue(new Callback<CheckCode>() {
            @Override
            public void onResponse(Call<CheckCode> call, Response<CheckCode> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        for (CheckCodeDatum checkCodeDatum : response.body().getData()) {
                            showToast(ForgotPasswordVerification.this, checkCodeDatum.getEmail());
                            startActivity(new Intent(ForgotPasswordVerification.this, ForgotPasswordActivity3.class));
                        }

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CheckCode> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.IV_BackIconForgotVerification, R.id.BTN_ConfirmCodeForgotVerification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackIconForgotVerification:
                HelperMethod.intentTo(this, ForgotPasswordActivityStep2.class);
                break;
            case R.id.BTN_ConfirmCodeForgotVerification:
                    checkVerificationCode(
                            ForgotPasswordActivityStep2.getmEmail(),
                            pinViewForgotVerification.getText().toString().trim());
                break;
        }
    }

    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressView.setVisibility(View.GONE);
    }
}
