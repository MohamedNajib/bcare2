package com.emedia.bcare.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.ViewPagerTapsAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.fragment.salon_taps.AboutTapFragment;
import com.emedia.bcare.ui.fragment.salon_taps.RatingsTapFragment;
import com.emedia.bcare.ui.fragment.salon_taps.ServicesTapFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalonActivity extends AppCompatActivity {

    @BindView(R.id.SalonTabLayout)
    TabLayout SalonTabLayout;
    @BindView(R.id.SalonViewPager)
    ViewPager SalonViewPager;
    @BindView(R.id.IV_SalonPackIcon)
    ImageView IVSalonPackIcon;
    @BindView(R.id.IV_SalonShareIcon)
    ImageView IVSalonShareIcon;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        ButterKnife.bind(this);


        System.out.println("ret act name : SALONACTIVITY");

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
    }



    /* Add Fragments to Tabs */
    private void setupClientViewPager(ViewPager viewPager) {
        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutTapFragment(), getResources().getString(R.string.About_the_salon));
        adapter.addFragment(new RatingsTapFragment(), getResources().getString(R.string.Services));
        adapter.addFragment(new ServicesTapFragment(), getResources().getString(R.string.Ratings));

        viewPager.setAdapter(adapter);
    }


    public void showLoading()
    {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        progress_view.setVisibility(View.GONE);
    }

    public void loadData()
    {
       //showLoading();
       //RequestSingletone.getInstance().getClient().create(ApiServices.class)
       //        .getSalons(SharedUser.getSharedUser().getToken().equals("") ? "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp"
       //                ,)
    }


}
