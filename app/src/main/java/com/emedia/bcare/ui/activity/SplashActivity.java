package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.util.HelperMethod;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.bcare_intro);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(R.drawable.bcare_intro).into(imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedUser.getSharedUser().getToken() != null){
                    //Intent toHomeActivity = new Intent(SplashActivity.this, HomeActivity.class);
                    Intent toHomeActivity = new Intent(SplashActivity.this, GenderActivity.class);
                    toHomeActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    toHomeActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(toHomeActivity);
                    finish();

                    HelperMethod.showToast(SplashActivity.this,
                            SharedUser.getSharedUser().getClientLoginData().getName() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getUsername() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getEmail() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getAge() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getAddress() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getCityId() + "\n" +
                                    SharedUser.getSharedUser().getClientLoginData().getCountryId() + "\n" +
                                    SharedUser.getSharedUser().getToken());

                }else {
                    //startActivity(new Intent(SplashActivity.this, ForgotPasswordActivity3.class));
                    startActivity(new Intent(SplashActivity.this, LoginMainActivity.class));
                    finish();
                }
                //startActivity(new Intent(SplashActivity.this, GenderActivity.class));
                //startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        },3000);

    }


}
