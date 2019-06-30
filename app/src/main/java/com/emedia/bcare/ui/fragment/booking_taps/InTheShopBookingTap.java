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
import com.emedia.bcare.adapter.fragments_adapter.BookingTimeAtSalonAdapter;
import com.emedia.bcare.data.model.api_model.booking.Booking;
import com.emedia.bcare.data.model.api_model.booking.Specialist;
import com.emedia.bcare.data.model.api_model.booking.Specialist_;
import com.emedia.bcare.data.model.api_model.booking.TimeAtSalon;
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
public class InTheShopBookingTap extends Fragment {

    @BindView(R.id.RV_InTheShopBooking)
    RecyclerView RVInTheShopBooking;
    @BindView(R.id.RV_InTheShopSp)
    RecyclerView RVInTheShopSp;
    Unbinder unbinder;


    /* member variable */
    private BookingTimeAtSalonAdapter mBookingTimeAtSalonAdapter;
    private BookingSalonSpecialistAdapter mBookingSalonSpecialist;
    private List<TimeAtSalon> mTimeAtSalon;
    private List<Specialist> mSpecialistAtHome;
    private List<Specialist_> specialistList;

    private TimeAtSalon selectedTimeAtSalon;
    private Specialist_ selectedSpecialist;
    private String selectedDate;

    @BindView(R.id.progress_view)
    ProgressBar progress_view;
    public InTheShopBookingTap() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in_the_shop_booking_tap, container, false);
        unbinder = ButterKnife.bind(this, view);

        mTimeAtSalon = new ArrayList<>();
        RVInTheShopBooking.setLayoutManager(new GridLayoutManager(getContext(), 3));
        RVInTheShopBooking.setHasFixedSize(true);
        RVInTheShopBooking.setItemAnimator(new DefaultItemAnimator());
        RVInTheShopBooking.setNestedScrollingEnabled(false);

        mSpecialistAtHome = new ArrayList<>();
        //RVSpecializedAtHome.setLayoutManager(new GridLayoutManager(getContext(), 3));
        RVInTheShopSp.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RVInTheShopSp.setHasFixedSize(true);
        RVInTheShopSp.setItemAnimator(new DefaultItemAnimator());

        getSalonBookingTimes(
                "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                1);
                //((HomeActivity) getActivity()).getSalon().getId());

        return view;
    }

    /**
     * get Booking times atS alon API Call
     */
    private void getSalonBookingTimes(String token, String lang, int salon_id) {

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
                        mTimeAtSalon = response.body().getData().getTimeAtSalon();

                        mBookingTimeAtSalonAdapter = new BookingTimeAtSalonAdapter(getContext(),
                                mTimeAtSalon, new BookingTimeAtSalonAdapter.OnClickTimeItem() {
                            @Override
                            public void onTimeAtSalonClicked(int position, TextView tv) {
                                TimeAtSalon timeAtSalon = mTimeAtSalon.get(position);
                                //Selected
                                selectedTimeAtSalon = mTimeAtSalon.get(position);

                                for (int i = 0; i < mTimeAtSalon.size(); i++) {
                                    TimeAtSalon current = mTimeAtSalon.get(i);
                                    mTimeAtSalon.get(i).isChecked = timeAtSalon.getTime().equals(current.getTime());
                                }
                                mBookingTimeAtSalonAdapter.notifyDataSetChanged();
                            }
                        });
                        RVInTheShopBooking.setAdapter(mBookingTimeAtSalonAdapter);

                        mSpecialistAtHome = response.body().getData().getSpecialists();
                        for (Specialist specialist : mSpecialistAtHome) {
                            specialistList = specialist.getSpecialist();
                        }
                        mBookingSalonSpecialist = new BookingSalonSpecialistAdapter(getContext(), specialistList,
                                new BookingSalonSpecialistAdapter.OnClickTimeItem() {
                                    @Override
                                    public void onSpecializedClicked(Specialist_ specialist, int position) {
                                        selectedSpecialist = specialist;
                                        showToast(getContext(), "Selected " + specialist.getSpecialistId());
                                        Specialist_ specialistlist = specialistList.get(position);
                                        for (int i = 0; i < specialistList.size(); i++) {
                                            Specialist_ current = specialistList.get(i);
                                            specialistList.get(i).isChecked = specialistlist.getSpecialistId().equals(current.getSpecialistId());
                                        }
                                        mBookingSalonSpecialist.notifyDataSetChanged();
                                    }
                                });
                        RVInTheShopSp.setAdapter(mBookingSalonSpecialist);
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

    @OnClick(R.id.BTN_BookNowInTheShop)
    public void onViewClicked() {
        //FIXME will by dynamic from the calendar
        selectedDate = ((HomeActivity) getActivity()).getReserveDate();
        ((HomeActivity) getActivity()).setReserveTime(selectedTimeAtSalon.getTime());
        ((HomeActivity) getActivity()).setGetReserveSalon(selectedSpecialist, selectedDate, selectedTimeAtSalon,null );
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
