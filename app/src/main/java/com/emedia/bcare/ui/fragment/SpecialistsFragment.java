package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.SpecialistsAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.specialists.Specialists;
import com.emedia.bcare.data.model.api_model.specialists.SpecialistsData;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.GenderActivity;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.intentTo;
import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialistsFragment extends Fragment {


    @BindView(R.id.RV_Specialists)
    RecyclerView RVSpecialists;
    Unbinder unbinder;
    @BindView(R.id.progress_view)
    ProgressBar progressView;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SpecialistsAdapter mSpecialistsAdapter;

    //var
    private List<SpecialistsData> mSpecialistsData;
    private static int mSpecialistId;

    public SpecialistsFragment() {
        // Required empty public constructor
    }

    private TextViewCustomFont mToolBarTitle;
    private TextViewCustomFont mToolBarDiscrib;
    private ImageView mToolBarIconBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specialists, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar sectionsBar = view.findViewById(R.id.SpecialistsTB);
        mToolBarTitle = sectionsBar.findViewById(R.id.TV_Title);
        mToolBarDiscrib = sectionsBar.findViewById(R.id.TV_description);
        mToolBarIconBack = sectionsBar.findViewById(R.id.IV_Back);

        mToolBarTitle.setText(((HomeActivity) getActivity()).getResources().getString(R.string.Specialists));
        mToolBarDiscrib.setText(SharedUser.getSharedUser().getClientLoginData().getAddress());

        mToolBarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTo((HomeActivity) getActivity(), HomeActivity.class);
            }
        });

        if (Locale.getDefault().getLanguage().equals("ar")) {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSpecialistsData = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSpecialists.setLayoutManager(mLayoutManager);
        RVSpecialists.setHasFixedSize(true);
        RVSpecialists.setItemAnimator(new DefaultItemAnimator());

        getSpecialists(
                SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                Integer.valueOf(SharedUser.getSharedUser().getClientLoginData().getCountryId()),
                GenderActivity.getGenderType(), 1, "rate"
        );
        initialize();
        return view;
    }

    protected void initialize() {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }

    /**
     * get Specialists Use API Call
     */
    private void getSpecialists(String token, String lang, int country_id, int salontype_id, int specialistgroup_id, String orderBy) {
        showLoading();
        Call<Specialists> specialistsCall = RetrofitClient.getInstance().getApiServices().getSpecialists(
                token, lang, country_id, salontype_id, specialistgroup_id, orderBy
        );
        specialistsCall.enqueue(new Callback<Specialists>() {
            @Override
            public void onResponse(Call<Specialists> call, Response<Specialists> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        //showToast(getContext(), "ok");

                        mSpecialistsData = response.body().getData();
                        mSpecialistsAdapter = new SpecialistsAdapter(getContext(), mSpecialistsData, new SpecialistsAdapter.OnSpecialistClick() {
                            @Override
                            public void onSpecialistItemClicked(int position) {
                                mSpecialistId = mSpecialistsData.get(position).getId();
                                ((HomeActivity) getActivity()).changeFragment(14);
                            }
                        });
                        RVSpecialists.setAdapter(mSpecialistsAdapter);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Specialists> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static int getSpecialistId() {
        return mSpecialistId;
    }

    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressView.setVisibility(View.GONE);
    }
}
