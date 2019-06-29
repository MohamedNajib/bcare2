package com.emedia.bcare.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.ErrorUtils;
import com.emedia.bcare.data.model.api_model.login.LogInMain;
import com.emedia.bcare.data.model.api_model.login.LoginErrorMain2;
import com.emedia.bcare.data.model.api_model.register.Registeration;
import com.emedia.bcare.interfaces.nework.ILogin;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.fragment.GenderFragment;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.ButtonCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.ET_LoginPhone)
    EditText ETLoginPhone;
    @BindView(R.id.ET_LoginEmail)
    EditText ETLoginEmail;
    @BindView(R.id.ET_LoginPassword)
    EditText ETLoginPassword;
    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    ButtonCustomFont BTN_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login1);
        ButterKnife.bind(this);

        initUI();
        handleUI();

        startTest();
    }


    public void initUI()
    {
        BTN_Login = (ButtonCustomFont) findViewById(R.id.BTN_Login);
    }

    public void handleUI()
    {
        BTN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }


    @OnClick(R.id.BTN_Login)
    public void doLogin()
    {
        if(! ETLoginEmail.getText().toString().trim().equals(""))
        {
            //startActivity(new Intent(LoginActivity.this, SalonActivity.class));

            showLoading();
            RequestSingletone.getInstance().getClient()
                    .create(ILogin.class)
                    .doLogginByEmail(ETLoginEmail.getText().toString().trim(),
                            ETLoginPassword.getText().toString().trim())
                    .enqueue(new Callback<Registeration>() {
                        @Override
                        public void onResponse(Call<Registeration> call, Response<Registeration> response) {
                            hideLoading();
                            //HelperMethod.replaceFragments(new GenderFragment(), getSupportFragmentManager(), R.id.container);
                            //((HomeActivity) Activity).changeFragment(16);
                            //startActivity(new Intent(LoginActivity.this, GenderActivity.class));
                            //

                            if(response.isSuccessful())
                            {
                                Toast.makeText(BCareApp.getInstance().getContext(),
                                        "Welcome " + response.body().getData().get(0).getName() , Toast.LENGTH_SHORT).show();
                                SharedUser.getSharedUser().setEmail(response.body().getData().get(0).getEmail());
                                SharedUser.getSharedUser().setName(response.body().getData().get(0).getName());
                                SharedUser.getSharedUser().setToken(response.body().getData().get(0).getUsersSocail().getAccessToken());
                                SharedUser.getSharedUser().setAddress("");
                                SharedUser.getSharedUser().setCityid("1");
                                SharedUser.getSharedUser().setCountryid("1");

                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                            else
                            {
                                Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
                                LoginErrorMain2 error = ErrorUtils.parseError(response);
                                if(error.getData() != null)
                                {
                                   Toast.makeText(getApplicationContext(), error.getData(), Toast.LENGTH_SHORT).show();

                                   if( error.getData().equals("Email is not verified"))
                                   {
                                       startActivity(new Intent(LoginActivity.this, VerficationActivity.class));
                                   }

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Registeration> call, Throwable t) {
                            Toast.makeText(BCareApp.getInstance().getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            hideLoading();

                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    });
        }

        else
        {
            showLoading();
            RequestSingletone.getInstance().getClient()
                    .create(ILogin.class)
                    .doLogginByMobile(ETLoginPhone.getText().toString().trim(),
                            ETLoginPassword.getText().toString().trim())
                    .enqueue(new Callback<Registeration>() {
                        @Override
                        public void onResponse(Call<Registeration> call, Response<Registeration> response) {
                            hideLoading();
                            //HelperMethod.replaceFragments(new GenderFragment(), getSupportFragmentManager(), R.id.container);
                            //((HomeActivity) Activity).changeFragment(16);
                            //startActivity(new Intent(LoginActivity.this, GenderActivity.class));
                            //

                            if(response.isSuccessful())
                            {
                                Toast.makeText(BCareApp.getInstance().getContext(),
                                        "Welcome " + response.body().getData().get(0).getName() , Toast.LENGTH_SHORT).show();
                                SharedUser.getSharedUser().setName(response.body().getData().get(0).getName());
                                //SharedUser.getSharedUser().setEmail(ETLoginEmail.getText().toString().trim());
                                SharedUser.getSharedUser().setPhone(response.body().getData().get(0).getMobile());
                                SharedUser.getSharedUser().setToken(response.body().getData().get(0).getUsersSocail().getAccessToken());
                                SharedUser.getSharedUser().setAddress("");
                                SharedUser.getSharedUser().setCityid("1");
                                SharedUser.getSharedUser().setCountryid("1");

                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            }
                            else
                            {
                                Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
                                LoginErrorMain2 error = ErrorUtils.parseError(response);
                                if(error.getData() != null)
                                {
                                    Toast.makeText(getApplicationContext(), error.getData(), Toast.LENGTH_SHORT).show();

                                    if( error.getData().equals("Email is not verified"))
                                    {
                                        startActivity(new Intent(LoginActivity.this, VerficationActivity.class));
                                    }

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Registeration> call, Throwable t) {
                            Toast.makeText(BCareApp.getInstance().getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            hideLoading();

                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    });
        }
    }

    @OnClick(R.id.TV_Login_CreateNewAccountLink)
    public void navigateToSignUp()
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }

    public void startTest()
    {
        //ETLoginEmail.setText("mohamed0110@yahoo.com");
        //ETLoginPhone.setText("01013041337");
        ETLoginEmail.setText("test_nexmo1@yahoo.com");
        ETLoginPhone.setText("01005220568");
        ETLoginPassword.setText("12345678");
    }
}
