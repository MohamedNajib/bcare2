package com.emedia.bcare.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.verify.VerifyMain;
import com.emedia.bcare.interfaces.nework.IRegister;
import com.emedia.bcare.network.RequestSingletone;
import com.example.fontutil.TextViewCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.TO_REG_ACTIVITY2;
import static com.emedia.bcare.ui.activity.RegisterByEmail1.USER_NAME;
import static com.emedia.bcare.util.HelperMethod.intentTo;

public class RegisterVerifyActivity extends AppCompatActivity {

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

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

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

        if (getResources().getString(R.string.current_lang).equals("ar")) {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVBackToMainRegisterRegister2.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
    }

    private void checkCodeEmail(final String email, final String code, final String userName) {

        showLoading();
        RequestSingletone.getInstance().getClient().create(IRegister.class)
                .verifyEmail(email, code)
                .enqueue(new Callback<VerifyMain>() {
                    @Override
                    public void onResponse(Call<VerifyMain> call, Response<VerifyMain> response) {
                        hideLoading();
                        Toast.makeText(RegisterVerifyActivity.this, response.body().getData().get(0).getSuccess(), Toast.LENGTH_SHORT).show();
                        intentTo(RegisterVerifyActivity.this, GenderActivity.class);
                    }

                    @Override
                    public void onFailure(Call<VerifyMain> call, Throwable t) {
                        hideLoading();
                    }
                });
    }


    private void checkCodePhone(final String email, final String code, final String userName) {
        showLoading();
        RequestSingletone.getInstance().getClient().create(IRegister.class)
                .verifyEmail(email, code)
                .enqueue(new Callback<VerifyMain>() {
                    @Override
                    public void onResponse(Call<VerifyMain> call, Response<VerifyMain> response) {
                        hideLoading();
                        Toast.makeText(RegisterVerifyActivity.this, response.body().getData().get(0).getSuccess(), Toast.LENGTH_SHORT).show();
                        intentTo(RegisterVerifyActivity.this, GenderActivity.class);
                    }

                    @Override
                    public void onFailure(Call<VerifyMain> call, Throwable t) {
                        hideLoading();
                    }
                });
    }


    @OnClick({R.id.IV_BackToMainRegisterRegister2, R.id.BTN_ConfirmCodeRegister2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegisterRegister2:
                intentTo(this, RegisterByPhone1.class);
                break;
            case R.id.BTN_ConfirmCodeRegister2:
                switch (getIntent().getStringExtra(TO_REG_ACTIVITY2)) {
                    case "ByEmail":
                        checkCodeEmail(getIntent().getStringExtra("email"), pinView.getText().toString(), getIntent().getStringExtra(USER_NAME));
                        break;

                    case "ByPhone":
                        checkCodePhone(getIntent().getStringExtra("mobile"), pinView.getText().toString(), getIntent().getStringExtra(USER_NAME));
                        break;
                }
                break;
        }
    }

    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }
}
