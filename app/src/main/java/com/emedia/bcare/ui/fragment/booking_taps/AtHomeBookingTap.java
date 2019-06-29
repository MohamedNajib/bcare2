package com.emedia.bcare.ui.fragment.booking_taps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.BookingSalonSpecialistAdapter;
import com.emedia.bcare.adapter.fragments_adapter.BookingTimeAtHomeAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.booking.Booking;
import com.emedia.bcare.data.model.api_model.booking.Specialist;
import com.emedia.bcare.data.model.api_model.booking.Specialist_;
import com.emedia.bcare.data.model.api_model.booking.TimeAtHome;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.activity.HomeActivity;

import java.util.ArrayList;
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
public class AtHomeBookingTap extends Fragment {


    @BindView(R.id.RV_AtHomeBooking)
    RecyclerView RVAtHomeBooking;
    Unbinder unbinder;
    @BindView(R.id.RV_SpecializedAtHome)
    RecyclerView RVSpecializedAtHome;

    /* member variable */
    private BookingTimeAtHomeAdapter mBookingTimeAtHomeAdapter;
    private BookingSalonSpecialistAdapter mBookingSalonSpecialist;
    private List<TimeAtHome> mTimeAtHome;
    private List<Specialist> mSpecialistAtHome;
    private List<Specialist_> specialistList;

    public AtHomeBookingTap() {
        // Required empty public constructor
    }

    private TimeAtHome selectedTimeAtHome;
    private Specialist_ selectedSpecialist;
    private String selectedDate;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_at_home_booking_tap, container, false);
        unbinder = ButterKnife.bind(this, view);

        mTimeAtHome = new ArrayList<>();
        RVAtHomeBooking.setLayoutManager(new GridLayoutManager(getContext(), 3));
        RVAtHomeBooking.setHasFixedSize(true);
        RVAtHomeBooking.setItemAnimator(new DefaultItemAnimator());
        RVAtHomeBooking.setNestedScrollingEnabled(false);

        mSpecialistAtHome = new ArrayList<>();
        //RVSpecializedAtHome.setLayoutManager(new GridLayoutManager(getContext(), 3));
        RVSpecializedAtHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RVSpecializedAtHome.setHasFixedSize(true);
        RVSpecializedAtHome.setItemAnimator(new DefaultItemAnimator());

        getHomeBookingTimes(
                //"Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                SharedUser.getSharedUser().getToken(),
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                1);
        //((HomeActivity) getActivity()).getSalon().getId());

        return view;
    }

    /**
     * get Booking times at Home API Call
     */
    private void getHomeBookingTimes(String token, String lang, int salon_id) {

        showLoading();
        Call<Booking> bookingCall = RetrofitClient.getInstance()
                .getApiServices()
                .salonTimeSpecialist(token, lang, salon_id);
        bookingCall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        //showToast(getContext(), "OK");
                        mTimeAtHome = response.body().getData().getTimeAtHome();

                        mBookingTimeAtHomeAdapter = new BookingTimeAtHomeAdapter(getContext(),
                                mTimeAtHome, new BookingTimeAtHomeAdapter.OnClickTimeItem() {
                            @Override
                            public void onTimeAtHomeClicked(int position, TextView tv) {
                                TimeAtHome timeAtSalon = mTimeAtHome.get(position);

                                //Selected
                                selectedTimeAtHome = mTimeAtHome.get(position);

                                for (int i = 0; i < mTimeAtHome.size(); i++) {
                                    TimeAtHome current = mTimeAtHome.get(i);
                                    mTimeAtHome.get(i).isChecked = timeAtSalon.getTime().equals(current.getTime());
                                }
                                mBookingTimeAtHomeAdapter.notifyDataSetChanged();
                            }
                        });
                        RVAtHomeBooking.setAdapter(mBookingTimeAtHomeAdapter);

                        mSpecialistAtHome = response.body().getData().getSpecialists();
                        for (Specialist specialist : mSpecialistAtHome) {
                            specialistList = specialist.getSpecialist();
                        }
                        mBookingSalonSpecialist = new BookingSalonSpecialistAdapter(getContext(), specialistList,
                                new BookingSalonSpecialistAdapter.OnClickTimeItem() {
                                    @Override
                                    public void onSpecializedClicked(Specialist_ specialist_, int position) {
                                        selectedSpecialist = specialist_;
                                        showToast(getContext(), "Selected " + specialist_.getSpecialistId());

                                        Specialist_ specialist = specialistList.get(position);
                                        for (int i = 0; i < specialistList.size(); i++) {
                                            Specialist_ current = specialistList.get(i);
                                            specialistList.get(i).isChecked = specialist.getSpecialistId().equals(current.getSpecialistId());
                                        }
                                        mBookingSalonSpecialist.notifyDataSetChanged();
                                    }
                                });
                        RVSpecializedAtHome.setAdapter(mBookingSalonSpecialist);

                    } else {
                        //showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_BookNowAtHome)
    public void onViewClicked() {
        //FIXME will by dynamic from the calendar
        selectedDate = ((HomeActivity) getActivity()).getReserveDate();
        ((HomeActivity) getActivity()).setReserveTime(selectedTimeAtHome.getTime());
        ((HomeActivity) getActivity()).setGetReserveSalon(selectedSpecialist, selectedDate, null, selectedTimeAtHome);
        ((HomeActivity) getActivity()).changeFragment(16);
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
