package com.emedia.bcare.ui.fragment.bottom_nav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.data.model.api_model.user_details.UserData;
import com.emedia.bcare.data.model.api_model.user_details.UserDetails;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @BindView(R.id.CIV_ProfileImage)
    CircleImageView CIVProfileImage;
    @BindView(R.id.TV_ProfileName)
    TextView TVProfileName;
    @BindView(R.id.TV_ProfileAddress)
    TextViewCustomFont TVProfileAddress;
    @BindView(R.id.TV_UserName)
    TextView TVUserName;
    @BindView(R.id.TV_UserEmail)
    TextView TVUserEmail;
    @BindView(R.id.TV_UserPassword)
    TextView TVUserPassword;
    @BindView(R.id.TV_Location)
    TextView TVLocation;
    Unbinder unbinder;

    private List<UserData> mUserDataList;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        mUserDataList = new ArrayList<>();

        initialize();

        getUserDetails(
                //"LYxvwTsQWbFF0bRWQA3Y7eUWfxDy1vV4yKrZHW9P247Tg2uG86kAN8PdNRu5",
                SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang));
        return view;
    }

    public void initialize()
    {
        ((HomeActivity) getActivity()).showBottomToolbar();
        ((HomeActivity) getActivity()).setToolbar(((HomeActivity) getActivity()).getResources().getString(R.string.my_account));
    }

    private void getUserDetails(String token, String lang) {
        showLoading();
        Call<UserDetails> userDetailsCall = RetrofitClient.getInstance().getApiServices().getUserDetails(token, lang);
        userDetailsCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "OK");

                        mUserDataList = response.body().getData();
                        for (UserData userData : mUserDataList) {

                            Glide.with(getActivity()).load(userData.getProfilePicture()).into(CIVProfileImage);
                            TVProfileName.setText(userData.getCityName());
                            TVProfileAddress.setText(userData.getAddress());
                            TVUserName.setText(userData.getCityName());
                            TVUserEmail.setText(userData.getEmail());
                            TVLocation.setText(userData.getCountryName() + " , " + userData.getCityName());
                        }


                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_DiscountCode)
    public void onViewClicked() {
        //DiscountCode

        ((HomeActivity) getActivity()).changeFragment(17);
        ((HomeActivity) getActivity()).heden();

    }

    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }

}
