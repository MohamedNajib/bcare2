package com.emedia.bcare.ui.fragment;


import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.SalonServicesAdapterA;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.data.model.checkCopoun.CheckCopoun;
import com.emedia.bcare.data.model.checkCopoun.WithoutCopoun;
import com.emedia.bcare.data.model.salon_services.SalonServices;
import com.emedia.bcare.data.model.salon_services.SalonServicesData;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.interfaces.listeners.OnSectionListener;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.activity.BookingActivity;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.EditTextCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.adapter.fragments_adapter.SalonServicesAdapterB.getmServicesIdList;
import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonServicesFragment extends Fragment implements OnSectionListener {

    Unbinder unbinder;
    @BindView(R.id.SpinnerCountrySalonServices)
    Spinner SpinnerCountrySalonServices;
    @BindView(R.id.RV_SalonServices)
    RecyclerView RVSalonServices;
    @BindView(R.id.TV_SalonServicesPrice)
    TextViewCustomFont TVSalonServicesPrice;
    @BindView(R.id.ET_DiscountCodeSalonServices)
    EditTextCustomFont ETDiscountCodeSalonServices;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SalonServicesAdapterA mSalonServicesAdapterA;

    // var
    private List<SalonServicesData> mSalonServicesDataList;

    public SalonServicesFragment() {
        // Required empty public constructor
    }

    private TextViewCustomFont mToolBarTitle;
    private ImageView mToolBarIconBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salon_services, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar sectionsBar = view.findViewById(R.id.SalonServicesToolBar);
        mToolBarTitle = sectionsBar.findViewById(R.id.TV_Title);
        mToolBarIconBack = sectionsBar.findViewById(R.id.IV_Back);

        mToolBarTitle.setText(((HomeActivity) getActivity()).getResources().getString(R.string.Choose_services));
        mToolBarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).onBackPressed();
            }
        });

        initialize();
        if (Locale.getDefault().getLanguage().equals("ar")) {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSalonServicesDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSalonServices.setLayoutManager(mLayoutManager);
        RVSalonServices.setHasFixedSize(true);
        RVSalonServices.setItemAnimator(new DefaultItemAnimator());

        getSalonServicesAPI(SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                Integer.parseInt(SharedUser.getSharedUser().getClientLoginData().getCountryId()));

        return view;
    }

    public void initialize()
    {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }
    /**
     * get Salon Services API Call
     */
    private void getSalonServicesAPI(String token, String lang, int country_id) {
        showLoading();

        RetrofitClient.getInstance().getApiServices().
                getSalonServices(token, lang, country_id).enqueue(new Callback<SalonServices>() {
            @Override
            public void onResponse(Call<SalonServices> call, Response<SalonServices> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        //showToast(getContext(), "OK");
                        mSalonServicesDataList = response.body().getData();

                        mSalonServicesAdapterA = new SalonServicesAdapterA(getContext(),
                                mSalonServicesDataList,
                                getCategoryWithoutMatch(getAllCatNames(mSalonServicesDataList)),
                                TVSalonServicesPrice,
                                SalonServicesFragment.this);
                        RVSalonServices.setAdapter(mSalonServicesAdapterA);



                    } else {
                        //showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SalonServices> call, Throwable t) {
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
    private List<String> getAllCatNames(List<SalonServicesData> mSalonServicesDataList) {
        List<String> stringCatName = new ArrayList<>();
        for (int i = 0; i < mSalonServicesDataList.size(); i++) {
            stringCatName.add(mSalonServicesDataList.get(i).getCatName());
        }
        return stringCatName;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.CL_Continue)
    public void onViewClicked() {
        ((HomeActivity) getActivity()).changeFragment(4);
        //discountCopounCode("Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
//        discountCopounCode(SharedUser.getSharedUser().getToken(),
//                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
//                ETDiscountCodeSalonServices.getText().toString().trim(),
//                ((HomeActivity) getActivity()).getSalon().getId());
    }

    private void discountCopounCode(String apiToken, String lang, String code, int salon_id) {

        showLoading();
        if (code.equals("")){
            Call<WithoutCopoun> withoutCopounCall = RequestSingletone.getInstance().getClient().create(ApiServices.class).withoutCopoun(
                    apiToken, lang, code, salon_id
            );
            withoutCopounCall.enqueue(new Callback<WithoutCopoun>() {
                @Override
                public void onResponse(Call<WithoutCopoun> call, Response<WithoutCopoun> response) {
                    hideLoading();
                    try {
                        if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                            //showToast(getContext(), "code: " + response.body().getData());
                            //Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                            if(getmServicesIdList().size() > 0)
                            {
                                ((HomeActivity) getActivity()).setListOfCats(getmServicesIdList());
                               // startActivity(new Intent(getContext(), BookingActivity.class));
                                 ((HomeActivity) getActivity()).changeFragment(4);
                            }
                            else
                            {
                                Toast.makeText(getActivity().getApplicationContext(), "اختار قسم" ,Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //showToast(getContext(), "NO");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<WithoutCopoun> call, Throwable t) {
                    //Toast.makeText(getContext(), "no", Toast.LENGTH_SHORT).show();
                    hideLoading();
                }
            });
        }else {
            Call<CheckCopoun> checkCopounCall = RetrofitClient.getInstance().getApiServices().checkCopoun(
                    apiToken, lang, code, salon_id
            );
            checkCopounCall.enqueue(new Callback<CheckCopoun>() {
                @Override
                public void onResponse(Call<CheckCopoun> call, Response<CheckCopoun> response) {
                    //hideLoading();
                    try {
                        if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {

                            //showToast(getContext(), "code: " + response.body().getData().getCode());
                            if(getmServicesIdList().size() > 0)
                            {
                                ((HomeActivity) getActivity()).setListOfCats(getmServicesIdList());
                                //((HomeActivity) getActivity()).changeFragment(1);

                                startActivity(new Intent(getContext(), BookingActivity.class));
                            }
                            else
                            {
                                Toast.makeText(getActivity().getApplicationContext(), "اختار قسم" ,Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            //showToast(getContext(), "NO");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CheckCopoun> call, Throwable t) {
                    //showToast(getContext(), "onFailure" + t.getMessage());
                    //hideLoading();
                }
            });
        }
    }

    public void showLoading()
    {
        if(progress_view != null)
            progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        if(progress_view != null)
            progress_view.setVisibility(View.GONE);
    }

    List<String> mServicesIdList;

    @Override
    public void onSectionClick(List<String> mServicesIdList) {
        this.mServicesIdList = mServicesIdList;
    }
}
