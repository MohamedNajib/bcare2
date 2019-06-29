package com.emedia.bcare.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;
import com.example.fontutil.EditTextCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment2 extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.ET_LoginPhoneNumber)
    EditTextCustomFont ETLoginPhoneNumber;
    @BindView(R.id.ET_LoginPassword)
    EditTextCustomFont ETLoginPassword;

    public LoginFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_Login_, R.id.TV_Login_ForgotPassLink_, R.id.TV_Login_CreateNewAccountLink_})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Login_:
                break;
            case R.id.TV_Login_ForgotPassLink_:
                break;
            case R.id.TV_Login_CreateNewAccountLink_:
                break;
        }
    }
}
