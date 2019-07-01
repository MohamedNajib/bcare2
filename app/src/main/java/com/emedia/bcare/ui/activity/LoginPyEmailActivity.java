package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.login_email.UserData;
import com.emedia.bcare.data.model.login_email.LoginEmail;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.HelperMethod;
import com.emedia.bcare.util.UserInputValidation;
import com.example.fontutil.EditTextCustomFont;
import com.google.gson.Gson;

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

public class LoginPyEmailActivity extends AppCompatActivity {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;
    @BindView(R.id.IV_PyEmailBackToMainLogin)
    ImageView IVPyEmailBackToMainLogin;

    @BindView(R.id.ET_LoginPyEmail)
    EditTextCustomFont ETLoginPyEmail;
    @BindView(R.id.ET_LoginPyEmailPassword)
    EditTextCustomFont ETLoginPyEmailPassword;

    //var
    private boolean isPasswordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_py_email);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVPyEmailBackToMainLogin.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVPyEmailBackToMainLogin.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

       // startTest();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.IV_PyEmailBackToMainLogin, R.id.IV_LoginPyEShowPassword, R.id.BTN_LoginPyEmail, R.id.TV_LoginPyEmailDoNotHaveAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_PyEmailBackToMainLogin:
                intentTo(this, LoginMainActivity.class);
                break;
            case R.id.IV_LoginPyEShowPassword:
                togglePassVisibility(ETLoginPyEmailPassword);
                break;
            case R.id.TV_LoginPyEmailDoNotHaveAccountLink:
                intentTo(this, RegisterActivity.class);
                break;
            case R.id.BTN_LoginPyEmail:
                if (!UserInputValidation.isValidMail(ETLoginPyEmail.getText().toString().trim())) {
                    ETLoginPyEmail.setError(getResources().getString(R.string.Email_Error));
                } else if (!UserInputValidation.isValidPassword(ETLoginPyEmailPassword.getText().toString().trim())) {
                    ETLoginPyEmailPassword.setError("Please Enter Strong Password..");
                } else {
                    doLoginByEmail(ETLoginPyEmail.getText().toString().trim(), ETLoginPyEmailPassword.getText().toString().trim());
                }
                break;
        }
    }

    protected void doLoginByEmail(String email, String password) {
        showLoading();

        Call<LoginEmail> loginEmailCall = RetrofitClient.getInstance().getApiServices().doLoginByEmail(email, password);
        loginEmailCall.enqueue(new Callback<LoginEmail>() {
            @Override
            public void onResponse(Call<LoginEmail> call, Response<LoginEmail> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        hideLoading();
//                        System.out.println("ret rsponse : " + String.valueOf(response.body()));
//                        System.out.println("ret rsponse : " + String.valueOf(new Gson().toJson(response.body())));

                        for (UserData loginData : response.body().getData()) {
                            showToast(BCareApp.getInstance().getContext(),"Welcome " + loginData.getName());
                            SharedUser.getSharedUser().saveClientLoginData(loginData);
                            SharedUser.getSharedUser().setToken(loginData.getUsersSocail().getAccessToken());
                        }
                        startActivity(new Intent(LoginPyEmailActivity.this, HomeActivity.class));

                        HelperMethod.showToast(LoginPyEmailActivity.this,
                                SharedUser.getSharedUser().getClientLoginData().getName() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getUsername() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getEmail() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getAge() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getAddress() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getCityId() + "\n" +
                                        SharedUser.getSharedUser().getClientLoginData().getCityId() + "\n" +
                                        SharedUser.getSharedUser().getToken());

                    } else {
                        hideLoading();
                        Toast.makeText(BCareApp.getInstance().getContext(), "Error: " + response.body().getCode()
                                + response.body().getData(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginEmail> call, Throwable t) {
                Toast.makeText(BCareApp.getInstance().getContext(), "Failure", Toast.LENGTH_SHORT).show();
                hideLoading();
            }
        });
    }

    /**
     * hide and Shaw password
     */
    private void togglePassVisibility(EditTextCustomFont editText) {
        if (isPasswordVisible) {
            String pass = editText.getText().toString();
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText.setText(pass);
            editText.setSelection(pass.length());

        } else {
            String pass = editText.getText().toString();
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setText(pass);
            editText.setSelection(pass.length());
        }
        isPasswordVisible = !isPasswordVisible;
    }

    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }

    public void startTest() {

        //ETLoginPyEmail.setText("adam.lkjuytm@gmail.com");
        //ETLoginPyEmail.setText("01005220568");
        //ETLoginPyEmailPassword.setText("12345678");
        ETLoginPyEmail.setText("mohamed0110@yahoo.com");
        //ETLoginPyEmail.setText("01005220568");
        ETLoginPyEmailPassword.setText("12345678");
    }
}
