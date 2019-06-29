package com.emedia.bcare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.emedia.bcare.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivityStep2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.BTN_Ending)
    public void onViewClicked() {
        // TODO : when the Ending Restore Password Button Clicked
    }
}
