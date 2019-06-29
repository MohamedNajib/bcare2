package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.SpecialistsAdapter;
import com.emedia.bcare.data.model.api_model.specialists.Specialists;
import com.emedia.bcare.data.model.api_model.specialists.SpecialistsData;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
public class SpecialistsFragment extends Fragment {


    @BindView(R.id.IV_SpecialistsPackIcon)
    ImageView IVSpecialistsPackIcon;
    @BindView(R.id.RV_Specialists)
    RecyclerView RVSpecialists;
    Unbinder unbinder;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SpecialistsAdapter mSpecialistsAdapter;

    //var
    private List<SpecialistsData> mSpecialistsData;
    private static int mSpecialistId;

    public SpecialistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specialists, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSpecialistsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVSpecialistsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSpecialistsData = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSpecialists.setLayoutManager(mLayoutManager);
        RVSpecialists.setHasFixedSize(true);
        RVSpecialists.setItemAnimator(new DefaultItemAnimator());

        getSpecialists(
                "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                "ar", 1, 1, 1, "rate"
        );

        return view;
    }

    /**
     * get Specialists Use API Call
     */
    private void getSpecialists(String token, String lang, int country_id, int salontype_id, int specialistgroup_id, String orderBy) {

        Call<Specialists> specialistsCall = RetrofitClient.getInstance().getApiServices().getSpecialists(
                token, lang, country_id, salontype_id, specialistgroup_id, orderBy
        );
        specialistsCall.enqueue(new Callback<Specialists>() {
            @Override
            public void onResponse(Call<Specialists> call, Response<Specialists> response) {
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


    @OnClick(R.id.IV_SpecialistsPackIcon)
    public void onViewClicked() {
    }

    public static int getSpecialistId() {
        return mSpecialistId;
    }
}
