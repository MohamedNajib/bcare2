package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.checkcode.CheckCode;
import com.emedia.bcare.data.model.api_model.checkcode.CheckCodeDatum;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.Constants.FragmentsKeys.TO_REG_ACTIVITY2;
import static com.emedia.bcare.ui.activity.RegisterByEmail1.USER_Email;
import static com.emedia.bcare.ui.activity.RegisterByEmail1.USER_NAME;
import static com.emedia.bcare.util.HelperMethod.intentTo;
import static com.emedia.bcare.util.HelperMethod.showToast;

public class RegisterActivity2 extends AppCompatActivity {

    @BindView(R.id.IV_BackToMainRegisterRegister2)
    ImageView IVBackToMainRegisterRegister2;
    @BindView(R.id.TXT_Confirm_Code)
    TextViewCustomFont TXTConfirmCode;
    @BindView(R.id.TXT_Description)
    TextViewCustomFont TXTDescription;

    PinView pinView;
    public static final String R2_USER_NAME = "UserName";
    public static final String R2_EMAIL = "email";

    private String registerBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);
        pinView = findViewById(R.id.pinView);

        registerBy = getIntent().getStringExtra(TO_REG_ACTIVITY2);

        switch (getIntent().getStringExtra(TO_REG_ACTIVITY2)) {
            case "ByEmail":
                TXTConfirmCode.setText(getResources().getString(R.string.verify_your_email));
                TXTDescription.setText(getResources().getString(R.string.Enter_the_code_sent_to_your_email_to_confirm));
                break;

            case "ByPhone":
                TXTConfirmCode.setText(getResources().getString(R.string.Confirm_phone_number));
                TXTDescription.setText(getResources().getString(R.string.Enter_the_code_that_you_received_in_a_text_message));
                break;
        }

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    private void checkCode(final String email, final String code, final String userName) {
        Call<CheckCode> checkCodeCall = RetrofitClient.getInstance().getApiServices().checkCode(email, code);
        checkCodeCall.enqueue(new Callback<CheckCode>() {
            @Override
            public void onResponse(Call<CheckCode> call, Response<CheckCode> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        List<CheckCodeDatum> checkCodeData = response.body().getData();
                        for (CheckCodeDatum checkCodeDatum : checkCodeData) {
                            showToast(RegisterActivity2.this, checkCodeDatum.getEmail());

                            Intent toRegisterActivity3 = new Intent(RegisterActivity2.this, RegisterActivity3.class);
                            toRegisterActivity3.putExtra(R2_USER_NAME, userName);
                            toRegisterActivity3.putExtra(R2_EMAIL, email);
                            startActivity(toRegisterActivity3);
                        }

                        intentTo(RegisterActivity2.this, RegisterActivity3.class);
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

    @OnClick({R.id.IV_BackToMainRegisterRegister2, R.id.BTN_ConfirmCodeRegister2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegisterRegister2:
                intentTo(this, RegisterActivity1.class);
                break;
            case R.id.BTN_ConfirmCodeRegister2:
                checkCode(getIntent().getStringExtra(USER_Email), pinView.getText().toString(), getIntent().getStringExtra(USER_NAME));
                break;
        }
    }
}
