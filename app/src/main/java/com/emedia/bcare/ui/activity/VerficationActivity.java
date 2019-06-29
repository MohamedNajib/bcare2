package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.register.Registeration;
import com.emedia.bcare.interfaces.nework.IVerify;
import com.emedia.bcare.network.RequestSingletone;
import com.example.fontutil.ButtonCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerficationActivity extends AppCompatActivity {


    @BindView(R.id.ET_VerifyActivityCode_)
    EditText ET_VerifyActivityCode_;
    @BindView(R.id.BTN_Verify_)
    ButtonCustomFont BTN_Verify_;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    VerficationActivity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_verification);
        ButterKnife.bind(this);

        this.act = this;

    }




    @OnClick(R.id.BTN_Verify_)
    public void verifyNow()
    {
        showLoading();
        RequestSingletone.getInstance().getClient()
                .create(IVerify.class)
                .verify("mahmoud.dean1@gmail.com",
                        ET_VerifyActivityCode_.getText().toString().trim())
                .enqueue(new Callback<Registeration>() {
                    @Override
                    public void onResponse(Call<Registeration> call, Response<Registeration> response) {
                        hideLoading();
                        if(response.isSuccessful())
                        {
                            Toast.makeText(BCareApp.getInstance().getContext(), "Success", Toast.LENGTH_SHORT).show();

                            //startActivity(new Intent(VerficationActivity.this, SalonActivity.class));
                            startActivity(new Intent(VerficationActivity.this, LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Registeration> call, Throwable t) {
                        Toast.makeText(BCareApp.getInstance().getContext(), "Failure", Toast.LENGTH_SHORT).show();
                        hideLoading();

                        startActivity(new Intent(VerficationActivity.this, LoginActivity.class));

                    }
                });

    }


    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }
}
