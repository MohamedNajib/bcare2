package com.emedia.bcare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.change_password.ChangePassword;
import com.emedia.bcare.data.model.change_password.ChangePasswordData;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.HelperMethod;
import com.emedia.bcare.util.UserInputValidation;
import com.example.fontutil.EditTextCustomFont;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);
        ButterKnife.bind(this);

        if (getResources().getString(R.string.current_lang).equals("ar")) {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    public void changePassword(String email, String newpassword){
        Call<ChangePassword> changePasswordCall = RetrofitClient.getInstance().getApiServices().changePassword(email, newpassword);
        changePasswordCall.enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {

                        for (ChangePasswordData changePasswordData : response.body().getData()) {
                            showToast(ForgotPasswordActivity3.this, changePasswordData.getSuccess());
                            intentTo(ForgotPasswordActivity3.this, HomeActivity.class);
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
}
