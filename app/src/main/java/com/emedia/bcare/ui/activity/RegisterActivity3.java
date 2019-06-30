package com.emedia.bcare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.R;
import com.emedia.bcare.adapter.CitesSpinnerAdapter;
import com.emedia.bcare.adapter.CountrySpinnerAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.cities.Cities;
import com.emedia.bcare.data.model.api_model.cities.CitiesDatum;
import com.emedia.bcare.data.model.api_model.countries.CountriesData;
import com.emedia.bcare.data.model.api_model.countries.RegisterCountries;
import com.emedia.bcare.data.model.register.Register;
import com.emedia.bcare.data.model.register.UserData;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.util.HelperMethod;
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
import static com.emedia.bcare.ui.activity.RegisterActivity2.R2_EMAIL;
import static com.emedia.bcare.ui.activity.RegisterActivity2.R2_USER_NAME;
import static com.emedia.bcare.util.HelperMethod.intentTo;

public class RegisterActivity3 extends AppCompatActivity {
    @BindView(R.id.SpinnerCountryRegister)
    Spinner SpinnerCountryRegister;
    @BindView(R.id.SpinnerCityRegister)
    Spinner SpinnerCityRegister;

    @BindView(R.id.ET_SignUpName)
    EditTextCustomFont ETSignUpName;
    @BindView(R.id.ET_SignUpAddress)
    EditTextCustomFont ETSignUpAddress;
    @BindView(R.id.ET_SignUpPassword)
    EditTextCustomFont ETSignUpPassword;
    @BindView(R.id.ET_SignUpRePassword)
    EditTextCustomFont ETSignUpRePassword;

    @BindView(R.id.IV_BackToMainRegister3)
    ImageView IVBackToMainRegister3;
    @BindView(R.id.IV_ContinueIconSignPyPh3)
    ImageView IVContinueIconSignPyPh3;
    @BindView(R.id.progress_view)
    ProgressBar progressView;
    @BindView(R.id.DatePickerRegister3)
    DatePicker DatePickerRegister3;

    //var
    private boolean isPasswordVisible;
    private int mCountryId;
    private int mCityId;
    //private String mDate;


//    @BindView(R.id.ET_SignUpEmail)
//    EditText ET_SignUpEmail;
//    @BindView(R.id.ET_SignUpPassword)
//    EditText ET_SignUpPassword;
//    @BindView(R.id.ET_SignUpREPassword)
//    EditText ET_SignUpREPassword;
//
//    @BindView(R.id.BTN_SignUp_ptn)
//    ButtonCustomFont BTN_SignUp_ptn;
//
//
//    @BindView(R.id.progress_view)
//    ProgressBar progress_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up1);
        ButterKnife.bind(this);


        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVContinueIconSignPyPh3.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVBackToMainRegister3.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVContinueIconSignPyPh3.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVBackToMainRegister3.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        getCountrySpinnerData(Locale.getDefault().getLanguage());

//        initUI();
//        handleUI();
//        startTest();


    }

    private void signUpUser(String email, String password, String name, String address, String age,
                            int city_id, int country_id, String code, String username){

        Call<Register> registerCall = RetrofitClient.getInstance()
                .getApiServices().userRegister(email, password, name, address, age, city_id, country_id, code, username);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        Toast.makeText(BCareApp.getInstance().getContext(), "Success", Toast.LENGTH_SHORT).show();

                        for (UserData userData : response.body().getData()) {
                            SharedUser.getSharedUser().saveClientRegisterData(userData);
                        }
                        intentTo(RegisterActivity3.this, GenderActivity.class);

                    } else {
                        Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }

    /**
     * Get Spinner List Country Use API Call
     */
    private void getCountrySpinnerData(final String lang) {
        Call<RegisterCountries> registerCountriesCall = RetrofitClient.getInstance().getApiServices().getCountriesList(lang);
        registerCountriesCall.enqueue(new Callback<RegisterCountries>() {
            @Override
            public void onResponse(Call<RegisterCountries> call, Response<RegisterCountries> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        CountrySpinnerAdapter countrySpinnerAdapter =
                                new CountrySpinnerAdapter(RegisterActivity3.this, response.body().getData(), "RegisterActivity3");
                        SpinnerCountryRegister.setAdapter(countrySpinnerAdapter);
                        SpinnerCountryRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CountriesData countriesData = (CountriesData) parent.getSelectedItem();
                                mCountryId = countriesData.getId();

                                getCitesSpinnerData(lang, mCountryId);
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

            }
        });
    }

    /**
     * Get Spinner List Cities Use API Call
     */
    private void getCitesSpinnerData(String lang, int country_id) {
        Call<Cities> citiesCall = RetrofitClient.getInstance().getApiServices().getCitesList(lang, country_id);
        citiesCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        CitesSpinnerAdapter citesSpinnerAdapter = new CitesSpinnerAdapter(RegisterActivity3.this, response.body().getData());
                        SpinnerCityRegister.setAdapter(citesSpinnerAdapter);
                        SpinnerCityRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CitiesDatum citiesDatum = (CitiesDatum) parent.getSelectedItem();
                                mCityId = citiesDatum.getId();
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
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });

    }


    @OnClick({R.id.IV_BackToMainRegister3, R.id.CL_BTN_ContinueRegister3, R.id.IV_ShowPassword, R.id.IV_ReShowPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_BackToMainRegister3:
                break;
            case R.id.CL_BTN_ContinueRegister3:

                if (!UserInputValidation.isValidPassword(ETSignUpPassword.getText().toString().trim())) {
                    ETSignUpPassword.setError("Please Enter Strong Password..");

                } else if (!UserInputValidation.isValidRePassword(ETSignUpRePassword.getText().toString().trim(),
                        ETSignUpPassword.getText().toString().trim())) {
                    ETSignUpRePassword.setError("Re Password Is not equal Password..");

                }else {
                    signUpUser(getIntent().getStringExtra(R2_EMAIL),
                            ETSignUpPassword.getText().toString().trim(),
                            ETSignUpName.getText().toString(),
                            ETSignUpAddress.getText().toString(),
                            getDate(),
                            mCityId,
                            mCountryId,
                            "+20",
                            getIntent().getStringExtra(R2_USER_NAME));
                }

                break;
            case R.id.IV_ShowPassword:
                togglePassVisibility(ETSignUpPassword);
                break;
            case R.id.IV_ReShowPassword:
                togglePassVisibility(ETSignUpRePassword);
                break;
        }
    }

    private String getDate(){
        int day = DatePickerRegister3.getDayOfMonth();
        int month = DatePickerRegister3.getMonth() + 1;
        int year = DatePickerRegister3.getYear();
        String s_day = String.valueOf(day);
        String s_month = String.valueOf(month);
        String s_year = String.valueOf(year);
        return s_day + s_month + s_year;
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


//    public void initUI()
//    {
//        //BTN_Login = (ButtonCustomFont) findViewById(R.id.BTN_Login);
//    }
//
//    public void handleUI()
//    {
//       //BTN_Login.setOnClickListener(new View.OnClickListener() {
//       //     @Override
//       //     public void onClick(View v) {
//       //         doLogin();
//       //     }
//       // });
//    }
//
//    @OnClick(R.id.BTN_SignUp_ptn)
//    public void doSignUp()
//    {
//        if(validateForm())
//        {
//
//            showLoading();
//            RequestSingletone.getInstance().getClient()
//                    .create(IRegister.class)
//                    .doSignUp(ET_SignUpEmail.getText().toString().trim(),
//                            ET_SignUpPassword.getText().toString().trim(),
//                            "",
//                            "15",
//                            String.valueOf(1),
//                            String.valueOf(1),
//                            getIntent().getStringExtra("phone"),
//                            "2")
//                    .enqueue(new Callback<Registeration>() {
//                        @Override
//                        public void onResponse(Call<Registeration> call, Response<Registeration> response) {
//                            hideLoading();
//                            if(response.isSuccessful())
//                            {
//                                Toast.makeText(BCareApp.getInstance().getContext(), "Success", Toast.LENGTH_SHORT).show();
//
//
//                                SharedUser.getSharedUser().setEmail(ET_SignUpEmail.getText().toString().trim());
//                                SharedUser.getSharedUser().setPhone(getIntent().getStringExtra("phone"));
//                                SharedUser.getSharedUser().setAddress("");
//                                SharedUser.getSharedUser().setCityid("1");
//                                SharedUser.getSharedUser().setCountryid("1");
//
//                                startActivity(new Intent(RegisterActivity3.this, VerficationActivity.class));
//                            }
//                            else
//                            {
//                                Toast.makeText(BCareApp.getInstance().getContext(), "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Registeration> call, Throwable t) {
//                            Toast.makeText(BCareApp.getInstance().getContext(), "Failure" , Toast.LENGTH_SHORT).show();
//                            //System.out.println("ret Failure" + t.getMessage());
//                            //System.out.println("ret Failure" + t.getStackTrace());
//                            //System.out.println("ret Failure" + t.getLocalizedMessage());
//                            //System.out.println("ret Failure" + t.getCause());
//                            //System.out.println("ret Failure" + t.getSuppressed());
//                            hideLoading();
//
//                            startActivity(new Intent(RegisterActivity3.this, LoginActivity.class));
//
//                        }
//                    });
//
//        }
//    }
//
//    public void showLoading()
//    {
//        progress_view.setVisibility(View.VISIBLE);
//    }
//
//    public void hideLoading()
//    {
//        progress_view.setVisibility(View.GONE);
//    }
//
//    public boolean validateForm()
//    {
//        return true;
//
//    }
//
//    public void startTest()
//    {
//        ET_SignUpEmail.setText("mahmoud.dean@gmail.com");
//        ET_SignUpPassword.setText("12345678");
//    }
}
