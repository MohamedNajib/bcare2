package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.CountryCodeSpinnerAdapter;
import com.emedia.bcare.data.model.api_model.countries.CountriesData;
import com.emedia.bcare.data.model.api_model.countries.RegisterCountries;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.UserInputValidation;
import com.example.fontutil.EditTextCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.Constants.FragmentsKeys.TO_REG_ACTIVITY2;
import static com.emedia.bcare.util.HelperMethod.intentTo;


public class RegisterByPhone1 extends AppCompatActivity {

    @BindView(R.id.Spinner_SignUpPhone_)
    Spinner SpinnerSignUpPhone;
    @BindView(R.id.ET_SignUpPhone_)
    EditTextCustomFont ETSignUpPhone;
    @BindView(R.id.ET_SignUpUserName)
    EditTextCustomFont ETSignUpUserName;
    @BindView(R.id.IV_ContinueIconSignPyPh)
    ImageView IVContinueIcon;
    @BindView(R.id.IV_BackToMainRegister)
    ImageView IVBackToMainRegister;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        ButterKnife.bind(this);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVBackToMainRegister.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVContinueIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVBackToMainRegister.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        getCountrySpinnerData(getResources().getString(R.string.current_lang));
    }

    @OnClick({R.id.IV_BackToMainRegister, R.id.CL_BTN_Continue, R.id.TV_SignUP1_HaveAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegister:
                intentTo(this, RegisterActivity.class);
                break;
            case R.id.CL_BTN_Continue:
                System.out.println("ret validation 2 : " + ETSignUpPhone.getText().toString().trim().length());
                if (!UserInputValidation.isValidPhone(ETSignUpPhone.getText().toString().trim())) {
                    ETSignUpPhone.setError(getResources().getString(R.string.Mobile_Error));
                }
                else if (ETSignUpUserName.getText().toString().trim().equals("")){
                    ETSignUpUserName.setError("Please Enter your Name");
                }else {
                    sendMobile();
                }

                break;
            case R.id.TV_SignUP1_HaveAccountLink:
                break;
        }
    }

    /**
     * Get Spinner List Country Use API Call
     */
    int mCountryId;
    String countryCode;

    private void getCountrySpinnerData(final String lang) {
        showLoading();
        Call<RegisterCountries> registerCountriesCall = RetrofitClient.getInstance().getApiServices().getCountriesList(lang);
        registerCountriesCall.enqueue(new Callback<RegisterCountries>() {
            @Override
            public void onResponse(Call<RegisterCountries> call, Response<RegisterCountries> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        CountryCodeSpinnerAdapter countrySpinnerAdapter =
                                new CountryCodeSpinnerAdapter(RegisterByPhone1.this, response.body().getData(),
                                        "RegisterByEmail2");
                        SpinnerSignUpPhone.setAdapter(countrySpinnerAdapter);
                        SpinnerSignUpPhone.setBackgroundResource(R.drawable.spinner_d);
                        SpinnerSignUpPhone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CountriesData countriesData = (CountriesData) parent.getSelectedItem();
                                mCountryId = countriesData.getId();
                                countryCode = countriesData.getCode();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegisterCountries> call, Throwable t) {
                hideLoading();
            }
        });
    }


    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }

    public void sendMobile()
    {
        String phone = ETSignUpPhone.getText().toString().trim();
        Intent toRegisterActivity2 = new Intent(this, RegisterByPhone2.class);
        toRegisterActivity2.putExtra(TO_REG_ACTIVITY2, "ByPhone");
        toRegisterActivity2.putExtra("username", ETSignUpUserName.getText().toString().trim());
        if(ETSignUpPhone.getText().toString().length() == 11)
        {
            phone = phone.substring(1);
        }

        //FIXME : after backend
        //toRegisterActivity2.putExtra("phone", "+20" + phone);
        toRegisterActivity2.putExtra("phone", phone);
        toRegisterActivity2.putExtra("+20", phone);
        startActivity(toRegisterActivity2);
    }
}
