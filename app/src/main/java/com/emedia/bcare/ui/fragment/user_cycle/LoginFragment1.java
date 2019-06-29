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
public class LoginFragment1 extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.ET_LoginPhone)
    EditTextCustomFont ETLoginPhone;
    @BindView(R.id.ET_LoginEmail)
    EditTextCustomFont ETLoginEmail;
    @BindView(R.id.ET_LoginPassword)
    EditTextCustomFont ETLoginPassword;

    public LoginFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_Login, R.id.TV_Login_ForgotPasslink, R.id.TV_Login_CreateNewAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Login:
                break;
            case R.id.TV_Login_ForgotPasslink:
                break;
            case R.id.TV_Login_CreateNewAccountLink:
                break;
        }
    }
}
