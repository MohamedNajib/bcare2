package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.forgotPass_sendmail.EmailDatum;
import com.emedia.bcare.data.model.api_model.forgotPass_sendmail.ForgotPassSendMail;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.UserInputValidation;
import com.example.fontutil.EditTextCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.intentTo;
import static com.emedia.bcare.util.HelperMethod.showToast;


public class ForgotPasswordActivityStep1 extends AppCompatActivity {


    @BindView(R.id.ET_ForgotPassEmail)
    EditTextCustomFont ETForgotPassEmail;
    @BindView(R.id.progress_view)
    ProgressBar progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step1);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    private void sendEmail(String email) {
        showLoading();
        Call<ForgotPassSendMail> forgotPassSendMailCall = RetrofitClient.getInstance().getApiServices().SendMail(email);
        forgotPassSendMailCall.enqueue(new Callback<ForgotPassSendMail>() {
            @Override
            public void onResponse(Call<ForgotPassSendMail> call, Response<ForgotPassSendMail> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        List<EmailDatum> emailData = response.body().getData();
                        hideLoading();
                        for (EmailDatum emailDatum : emailData) {
                            showToast(ForgotPasswordActivityStep1.this, emailDatum.getEmail());
                            Intent toForgotPasswordActivityStep2 = new Intent(ForgotPasswordActivityStep1.this, ForgotPasswordActivityStep2.class);
                            toForgotPasswordActivityStep2.putExtra("FORGOT_PASSWORD_EMAIL", emailDatum.getEmail());
                            startActivity(toForgotPasswordActivityStep2);

                        }

                    } else {
                        showToast(ForgotPasswordActivityStep1.this, "NO");
                        hideLoading();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ForgotPassSendMail> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @OnClick({R.id.BTN_Restore_password_btn, R.id.TV_ForgotPass_LoginLink_Login, R.id.TV_ForgotPass_CreateAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Restore_password_btn:

                if (!UserInputValidation.isValidMail(ETForgotPassEmail.getText().toString().trim())) {
                    ETForgotPassEmail.setError(getResources().getString(R.string.Email_Error));
                } else {
                    sendEmail(ETForgotPassEmail.getText().toString().trim());
                }
                break;
            case R.id.TV_ForgotPass_LoginLink_Login:
                intentTo(this, LoginMainActivity.class);
                break;
            case R.id.TV_ForgotPass_CreateAccountLink:
                intentTo(this, RegisterActivity.class);
                break;
        }
    }

    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressView.setVisibility(View.GONE);
    }

//    public static class LoginActivity extends AppCompatActivity {
//
//
//        @BindView(R.id.ET_LoginPhone)
//        EditText ETLoginPhone;
//        @BindView(R.id.ET_LoginEmail)
//        EditText ETLoginEmail;
//        @BindView(R.id.ET_LoginPassword)
//        EditText ETLoginPassword;
//        @BindView(R.id.progress_view)
//        ProgressBar progress_view;
//
//        ButtonCustomFont BTN_Login;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_login1);
//            ButterKnife.bind(this);
//
//            initUI();
//            handleUI();
//
//            startTest();
//
//
//        }
//
//
//        public void initUI()
//        {
//            BTN_Login = (ButtonCustomFont) findViewById(R.id.BTN_Login);
//        }
//
//        public void handleUI()
//        {
//            BTN_Login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    doLogin();
//                }
//            });
//        }
//
//        @OnClick(R.id.BTN_Login)
//        public void doLogin()
//        {
//            if(! ETLoginEmail.getText().toString().trim().equals(""))
//            {
//                //startActivity(new Intent(LoginActivity.this, SalonActivity.class));
//
//                showLoading();
//                RequestSingletone.getInstance().getClient()
//                        .create(ILogin.class)
//                        .doLogginByEmail(ETLoginEmail.getText().toString().trim(),
//                                ETLoginPassword.getText().toString().trim())
//                        .enqueue(new Callback<Registeration>() {
//                            @Override
//                            public void onResponse(Call<Registeration> call, Response<Registeration> response) {
//                                hideLoading();
//                                //HelperMethod.replaceFragments(new GenderFragment(), getSupportFragmentManager(), R.id.container);
//                                //((HomeActivity) Activity).changeFragment(16);
//                                startActivity(new Intent(LoginActivity.this, GenderActivity.class));
//                                //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//
//                                if(response.isSuccessful())
//                                {
//                                    Toast.makeText(BCareApp.getInstance().getContext(), "Success", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
//                                    LoginErrorMain2 error = ErrorUtils.parseError(response);
//                                    if(error.getData() != null)
//                                    {
//                                       Toast.makeText(getApplicationContext(), error.getData(), Toast.LENGTH_SHORT).show();
//
//                                       if( error.getData().equals("Email is not verified"))
//                                       {
//                                           startActivity(new Intent(LoginActivity.this, VerficationActivity.class));
//                                       }
//
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Registeration> call, Throwable t) {
//                                Toast.makeText(BCareApp.getInstance().getContext(), "Failure", Toast.LENGTH_SHORT).show();
//                                hideLoading();
//
//                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                            }
//                        });
//            }
//        }
//
//        @OnClick(R.id.TV_Login_CreateNewAccountLink)
//        public void navigateToSignUp() {
//            HelperMethod.intentTo(this, RegisterActivity.class);
//
//        }
//
//
//
//        public void startTest() {
//            ETLoginEmail.setText("mohamed0110@yahoo.com");
//            ETLoginPhone.setText("01013041337");
//            ETLoginPassword.setText("12345678");
//        }
//
//        @OnClick({R.id.TV_Login_CreateNewAccountLink, R.id.TV_Login_ForgotPasslink})
//        public void onViewClicked(View view) {
//            switch (view.getId()) {
//                case R.id.TV_Login_CreateNewAccountLink:
//                    intentTo(this, RegisterActivity.class);
//                    break;
//                case R.id.TV_Login_ForgotPasslink:
//                    intentTo(this, LoginMainActivity.class);
//                    break;
//            }
//        }
//    }
}
