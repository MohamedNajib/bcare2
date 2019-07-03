package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.change_password.ChangePassword;
import com.emedia.bcare.data.model.change_password.ChangePasswordData;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.UserInputValidation;
import com.example.fontutil.EditTextCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.intentTo;
import static com.emedia.bcare.util.HelperMethod.showToast;

public class ForgotPasswordActivity3 extends AppCompatActivity {

    @BindView(R.id.IV_BackToMainRegisterRegister2)
    ImageView IVBackToMainRegisterRegister2;
    @BindView(R.id.ET_ForgotPassword3Pass)
    EditTextCustomFont ETForgotPassword3Pass;
    @BindView(R.id.ET_ForgotPassword3RePass)
    EditTextCustomFont ETForgotPassword3RePass;
    @BindView(R.id.progress_view)
    ProgressBar progressView;
    @BindView(R.id.PssI)
    ImageView PssI;
    @BindView(R.id.RePssI)
    ImageView RePssI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);
        ButterKnife.bind(this);

        ETForgotPassword3Pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                PssI.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (UserInputValidation.isValidRePassword(ETForgotPassword3RePass.getText().toString().trim(),
                        ETForgotPassword3Pass.getText().toString().trim())) {
                    RePssI.setVisibility(View.VISIBLE);
                } else {
                    RePssI.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (UserInputValidation.isValidPassword(ETForgotPassword3Pass.getText().toString().trim())) {
                    PssI.setVisibility(View.VISIBLE);
                } else {
                    PssI.setVisibility(View.GONE);
                    RePssI.setVisibility(View.GONE);
                }
            }
        });

        ETForgotPassword3RePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                RePssI.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (UserInputValidation.isValidRePassword(ETForgotPassword3RePass.getText().toString().trim(),
                        ETForgotPassword3Pass.getText().toString().trim())) {
                    RePssI.setVisibility(View.VISIBLE);
                } else {
                    RePssI.setVisibility(View.GONE);
                }
            }
        });

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            PssI.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            RePssI.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            PssI.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            RePssI.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    public void changePassword(String email, String newpassword) {
        showLoading();
        Call<ChangePassword> changePasswordCall = RetrofitClient.getInstance().getApiServices().changePassword(email, newpassword);
        changePasswordCall.enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {

                        for (ChangePasswordData changePasswordData : response.body().getData()) {
                            showToast(ForgotPasswordActivity3.this, changePasswordData.getSuccess());
                            intentTo(ForgotPasswordActivity3.this, LoginMainActivity.class);
                            finish();
                        }

                    } else {
                        //showToast(ForgotPasswordActivityStep1.this, "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ChangePassword> call, Throwable t) {
                showToast(ForgotPasswordActivity3.this, "Failure");
            }
        });
    }

    @OnClick({R.id.IV_BackToMainRegisterRegister2, R.id.ET_ForgotPassword3Pass, R.id.ET_ForgotPassword3RePass, R.id.BTN_ForgotPass3Save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegisterRegister2:
                intentTo(this, ForgotPasswordVerification.class);
                break;

            case R.id.BTN_ForgotPass3Save:
                if (!UserInputValidation.isValidPassword(ETForgotPassword3Pass.getText().toString().trim())) {
                    ETForgotPassword3Pass.setError("Please Enter Strong Password..");

                } else if (!UserInputValidation.isValidRePassword(ETForgotPassword3RePass.getText().toString().trim(),
                        ETForgotPassword3Pass.getText().toString().trim())) {
                    ETForgotPassword3RePass.setError("Re Password Is not equal Password..");

                } else {
                    changePassword(
                            ForgotPasswordActivityStep2.getmEmail(),
                            ETForgotPassword3Pass.getText().toString().trim());
                }
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
