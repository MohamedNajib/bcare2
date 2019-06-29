package com.emedia.bcare.ui.fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.ViewPagerTapsAdapter;
import com.emedia.bcare.adapter.fragments_adapter.SelectSalonAdapter;
import com.emedia.bcare.data.locle.SharedPrefManager;
import com.emedia.bcare.data.model.api_model.favorite.Favorite;
import com.emedia.bcare.data.model.api_model.salons.SalonData;
import com.emedia.bcare.data.model.api_model.salons.Salons;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.ui.activity.SalonActivity;
import com.emedia.bcare.ui.fragment.salon_taps.AboutTapFragment;
import com.emedia.bcare.ui.fragment.salon_taps.RatingsTapFragment;
import com.emedia.bcare.ui.fragment.salon_taps.ServicesTapFragment;
import com.emedia.bcare.util.HelperMethod;
import com.emedia.bcare.util.MyLocationProvider;
import com.example.fontutil.TextViewCustomFont;

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

import static com.emedia.bcare.Constants.FragmentsKeys.MY_PERMISSIONS_REQUEST_ACCESS_GPS;
import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.adapter.fragments_adapter.SalonServicesAdapterB.mTotalPrice;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonFragment extends Fragment {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    Unbinder unbinder;

    @BindView(R.id.SalonTabLayout)
    TabLayout SalonTabLayout;
    @BindView(R.id.SalonViewPager)
    ViewPager SalonViewPager;
    @BindView(R.id.IV_SalonPackIcon)
    ImageView IVSalonPackIcon;
    @BindView(R.id.IV_SalonShareIcon)
    ImageView IVSalonShareIcon;

    @BindView(R.id.tv_name)
    TextView tv_name;


    public SalonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_salon, container, false);
        unbinder = ButterKnife.bind(this, view);
        /* Set ViewPager */
        setupClientViewPager(SalonViewPager);
        SalonTabLayout.setupWithViewPager(SalonViewPager);


        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVSalonShareIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));

        } else {
            IVSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVSalonShareIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        }


        loadData();

        return view;
    }

    /* Add Fragments to Tabs */
    private void setupClientViewPager(ViewPager viewPager) {
        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new AboutTapFragment(), ((HomeActivity) getActivity()).getResources().getString(R.string.salon_about));
        adapter.addFragment(new RatingsTapFragment(), ((HomeActivity) getActivity()).getResources().getString(R.string.salon_services));
        adapter.addFragment(new ServicesTapFragment(), ((HomeActivity) getActivity()).getResources().getString(R.string.salon_reviews));

        viewPager.setAdapter(adapter);
    }

    public void loadData()
    {
        tv_name.setText(((HomeActivity) getActivity()).getSalon().getName());
    }

    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }

    @OnClick(R.id.IV_SalonPackIcon)
    void back()
    {
        ((HomeActivity) getActivity()).onBackPressed();
    }
}
