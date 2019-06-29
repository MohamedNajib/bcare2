package com.emedia.bcare.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.forgotPass_sendmail.EmailDatum;
import com.emedia.bcare.data.model.api_model.forgotPass_sendmail.ForgotPassSendMail;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.example.fontutil.EditTextCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.showToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassword1 extends Fragment {


    @BindView(R.id.ET_ForgotPassEmail)
    EditTextCustomFont ETForgotPassEmail;
    Unbinder unbinder;

    public ForgotPassword1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password1, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }




    private void sendEmail(String email){
        Call<ForgotPassSendMail> forgotPassSendMailCall = RetrofitClient.getInstance().getApiServices().SendMail(email);
        forgotPassSendMailCall.enqueue(new Callback<ForgotPassSendMail>() {
            @Override
            public void onResponse(Call<ForgotPassSendMail> call, Response<ForgotPassSendMail> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "ok");

                        List<EmailDatum> emailData = response.body().getData();
                        for (EmailDatum emailDatum : emailData) {

                        }

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ForgotPassSendMail> call, Throwable t) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

   //@OnClick({R.id.BTN_Restore_password_btn, R.id.TV_ForgotPass_LoginLink_, R.id.TV_ForgotPass_CreateAccountLink})
   //public void onViewClicked(View view) {
   //    switch (view.getId()) {
   //        case R.id.BTN_Restore_password_btn:
   //            break;

   //        case R.id.TV_ForgotPass_LoginLink_:
   //            break;

   //        case R.id.TV_ForgotPass_CreateAccountLink:
   //            break;
   //    }
   //}
}
