package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.CountrySpinnerAdapter;
import com.emedia.bcare.adapter.fragments_adapter.SectionsAdapterA;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.countries.CountriesData;
import com.emedia.bcare.data.model.api_model.countries.RegisterCountries;
import com.emedia.bcare.data.model.api_model.service.Service;
import com.emedia.bcare.data.model.api_model.service.Service_;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @BindView(R.id.RV_Sections)
    RecyclerView RVSections;
    Unbinder unbinder;
    @BindView(R.id.SpinnerCountrySections)
    Spinner SpinnerCountrySections;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SectionsAdapterA mSectionsAdapter;

    //var
    private List<Service_> mSalonDataList;

    public SectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        unbinder = ButterKnife.bind(this, view);

        initialize();

        mSalonDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSections.setLayoutManager(mLayoutManager);
        RVSections.setHasFixedSize(true);
        RVSections.setItemAnimator(new DefaultItemAnimator());

        getCountrySpinnerData(((HomeActivity) getActivity()).getResources().getString(R.string.current_lang));
        getService(SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                GenderFragment.getGenderType());
        return view;
    }

    protected void initialize()
    {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }


    /**
     * get Service Use API Call
     */
    private void getService(String apiToken, String lang, int salontype_id) {

        showLoading();
        Call<Service> serviceCall = RequestSingletone.getInstance().getClient().create(ApiServices.class).getServices(
                apiToken, lang, salontype_id
        );

        serviceCall.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        //showToast(getContext(), "OK");
                        mSalonDataList = response.body().getData().getServices();
                        mSectionsAdapter = new SectionsAdapterA(getContext(),
                                mSalonDataList,
                                getCategoryWithoutMatch(getAllCatNames(mSalonDataList)));
                        RVSections.setAdapter(mSectionsAdapter);

                    } else {
                        //showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {
                hideLoading();
            }
        });
    }

    /**
     * get Category Without match
     */
    private List<String> getCategoryWithoutMatch(List<String> cName) {
        List<String> catNames = new ArrayList<>();
        Set<String> unique = new HashSet<>(cName);
        for (String key : unique) {
            catNames.addAll(Collections.singleton(key));
        }
        return catNames;
    }

    /**
     * get String List Of Category Name
     */
    private List<String> getAllCatNames(List<Service_> sectionsList) {
        List<String> stringCatName = new ArrayList<>();
        for (int i = 0; i < sectionsList.size(); i++) {
            stringCatName.add(sectionsList.get(i).getCatName());
        }
        return stringCatName;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Get Spinner List Country Use API Call
     */
    private void getCountrySpinnerData(String lang) {
        Call<RegisterCountries> registerCountriesCall = RetrofitClient.getInstance().getApiServices().getCountriesList(lang);

        registerCountriesCall.enqueue(new Callback<RegisterCountries>() {
            @Override
            public void onResponse(Call<RegisterCountries> call, Response<RegisterCountries> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        CountrySpinnerAdapter countrySpinnerAdapter =
                                new CountrySpinnerAdapter(getContext(), response.body().getData(), "SectionsFragment");

                        SpinnerCountrySections.setAdapter(countrySpinnerAdapter);
                        SpinnerCountrySections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CountriesData countriesData = (CountriesData) parent.getSelectedItem();
                                //showToast(getContext(), countriesData.getCountryName());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        //showToast(getContext(), "NO");
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

    @OnClick(R.id.BTN_Continue)
    public void onViewClicked() {
        ((HomeActivity) getActivity()).changeFragment(2);
    }

    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }
}
