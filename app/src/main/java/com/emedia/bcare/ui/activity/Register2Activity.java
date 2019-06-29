package com.emedia.bcare.ui.activity;

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
import com.emedia.bcare.data.model.api_model.register.Registeration;
import com.emedia.bcare.interfaces.nework.IRegister;
import com.emedia.bcare.network.RequestSingletone;
import com.example.fontutil.ButtonCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register2Activity extends AppCompatActivity {


   //@BindView(R.id.ET_SignUpEmail)
   //EditText ET_SignUpEmail;
   //@BindView(R.id.ET_SignUpPassword)
   //EditText ET_SignUpPassword;
   //@BindView(R.id.ET_SignUpREPassword)
   //EditText ET_SignUpREPassword;

   //@BindView(R.id.BTN_SignUp_ptn)
   //ButtonCustomFont BTN_SignUp_ptn;


    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up1);
        ButterKnife.bind(this);

        initUI();
        handleUI();

        startTest();
    }


    public void initUI()
    {
        //BTN_Login = (ButtonCustomFont) findViewById(R.id.BTN_Login);
    }

    public void handleUI()
    {
       //BTN_Login.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         doLogin();
       //     }
       // });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    //@OnClick(R.id.BTN_SignUp_ptn)
    //public void doSignUp()
    //{
    //    if(validateForm())
    //    {
//
    //        showLoading();
    //        RequestSingletone.getInstance().getClient()
    //                .create(IRegister.class)
    //                .doSignUp(ET_SignUpEmail.getText().toString().trim(),
    //                        ET_SignUpPassword.getText().toString().trim(),
    //                        "",
    //                        "15",
    //                        String.valueOf(1),
    //                        String.valueOf(1),
    //                        getIntent().getStringExtra("phone"),
    //                        "2")
    //                .enqueue(new Callback<Registeration>() {
    //                    @Override
    //                    public void onResponse(Call<Registeration> call, Response<Registeration> response) {
    //                        hideLoading();
    //                        if(response.isSuccessful())
    //                        {
    //                            Toast.makeText(BCareApp.getInstance().getContext(), "Success", Toast.LENGTH_SHORT).show();
//
//
    //                            SharedUser.getSharedUser().setEmail(ET_SignUpEmail.getText().toString().trim());
    //                            SharedUser.getSharedUser().setPhone(getIntent().getStringExtra("phone"));
    //                            SharedUser.getSharedUser().setAddress("");
    //                            SharedUser.getSharedUser().setCityid("1");
    //                            SharedUser.getSharedUser().setCountryid("1");
//
    //                            startActivity(new Intent(Register2Activity.this, VerficationActivity.class));
    //                        }
    //                        else
    //                        {
    //                            Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
    //                        }
    //                    }
//
    //                    @Override
    //                    public void onFailure(Call<Registeration> call, Throwable t) {
    //                        Toast.makeText(BCareApp.getInstance().getContext(), "Failure" , Toast.LENGTH_SHORT).show();
    //                        //System.out.println("ret Failure" + t.getMessage());
    //                        //System.out.println("ret Failure" + t.getStackTrace());
    //                        //System.out.println("ret Failure" + t.getLocalizedMessage());
    //                        //System.out.println("ret Failure" + t.getCause());
    //                        //System.out.println("ret Failure" + t.getSuppressed());
    //                        hideLoading();
//
    //                        startActivity(new Intent(Register2Activity.this, LoginActivity.class));
//
    //                    }
    //                });
//
    //    }
    //}

    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }

    public boolean validateForm()
    {
        return true;

    }

    public void startTest()
    {
        //ET_SignUpEmail.setText("mahmoud.dean@gmail.com");
        //ET_SignUpPassword.setText("12345678");
    }
}
