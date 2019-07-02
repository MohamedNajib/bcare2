package com.emedia.bcare.ui.fragment.bottom_nav;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.NextEventAdapter;
import com.emedia.bcare.adapter.fragments_adapter.PreviousEventAdapter;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.my_event.MyEvent;
import com.emedia.bcare.data.model.api_model.my_event.NextAppointment;
import com.emedia.bcare.data.model.api_model.my_event.PreviousAppointment;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.TextViewCustomFont;

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
public class MyEventFragment extends Fragment {


    // bar Icon
    @BindView(R.id.progress_view)
    ProgressBar progress_view;
//    @BindView(R.id.IV_MyEventNavIcon)
//    ImageView IVMyEventNavIcon;
//    @BindView(R.id.IV_MyEventNotificationIcon)
//    ImageView IVMyEventNotificationIcon;

    // RecycleView
    @BindView(R.id.RV_MyNextEvent)
    RecyclerView RVMyNextEvent;
    @BindView(R.id.RV_MyEventLastDates)
    RecyclerView RVMyEventLastDates;

    // Button Container
    @BindView(R.id.CL_BTN_Container)
    ConstraintLayout CLBTNContainer;
    Unbinder unbinder;
//    @BindView(R.id.TV_NextEventText)
//    TextViewCustomFont TVNextEventText;


    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private NextEventAdapter mNextEventAdapter;
    private List<NextAppointment> mNextAppointments;
    private List<PreviousAppointment> mPreviousAppointments;
    private PreviousEventAdapter mPreviousEventAdapter;

    private static final int REQUEST_CALL = 100;
    private String mSalonNumber;


    public MyEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_event, container, false);
        unbinder = ButterKnife.bind(this, view);

        initialize();

        mNextAppointments = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVMyNextEvent.setLayoutManager(mLayoutManager);
        RVMyNextEvent.setHasFixedSize(true);
        RVMyNextEvent.setItemAnimator(new DefaultItemAnimator());
        RVMyNextEvent.setNestedScrollingEnabled(false);

        mPreviousAppointments = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVMyEventLastDates.setLayoutManager(mLayoutManager);
        RVMyEventLastDates.setHasFixedSize(true);
        RVMyEventLastDates.setItemAnimator(new DefaultItemAnimator());
        getMyEvents(
                SharedUser.getSharedUser().getToken(),
                //"Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                //"VAq8gDWHoxQ8NMiy5A9HFpG5Cyplgr54FHYnNuv4c4TFLFcTwe956RizJ8UW",
                //"UJAoT31Ms4XK16DkkYGAlmqpecznbvJoLZvf6E2u5taENjKSzYIg0AwOkI0P",
                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang));
        return view;
    }

    public void initialize() {
        ((HomeActivity) getActivity()).showBottomToolbar();
        ((HomeActivity) getActivity()).setToolbar(null);
        ((HomeActivity) getActivity()).setSupToolbar(null);
        ((HomeActivity) getActivity()).setEventTitle(((HomeActivity) getActivity()).getResources().getString(R.string.my_appointments));
    }

    private void getMyEvents(String token, String lang) {

        showLoading();
        Call<MyEvent> myEventCall = RequestSingletone.getInstance().getClient().create(ApiServices.class).getMyEvents(token, lang);
        myEventCall.enqueue(new Callback<MyEvent>() {
            @Override
            public void onResponse(Call<MyEvent> call, Response<MyEvent> response) {

                hideLoading();
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        mNextAppointments = response.body().getData().getNextAppointment();

                        showToast(getContext(), "OK");
                        if (mNextAppointments.size() == 0) {
                            CLBTNContainer.setVisibility(View.VISIBLE);
                            //TVNextEventText.setVisibility(View.GONE);
                            RVMyNextEvent.setVisibility(View.GONE);
                        } else {
                            CLBTNContainer.setVisibility(View.GONE);
                            //TVNextEventText.setVisibility(View.VISIBLE);
                            RVMyNextEvent.setVisibility(View.VISIBLE);

                            mNextEventAdapter = new NextEventAdapter(getContext(), mNextAppointments, new NextEventAdapter.OnClickEventItem() {
                                @Override
                                public void onSalonPhoneCallClicked(int position) {
                                    mSalonNumber = mNextAppointments.get(position).getSalon().getMobile();
                                    makePhoneCall(mSalonNumber);
                                }
                            });
                            RVMyNextEvent.setAdapter(mNextEventAdapter);
                        }

                        mPreviousAppointments = response.body().getData().getPreviousAppointment();
                        mPreviousEventAdapter = new PreviousEventAdapter(getContext(), mPreviousAppointments, new PreviousEventAdapter.OnClickEventItem() {
                            @Override
                            public void onEventClicked(int position) {

                            }
                        });
                        RVMyEventLastDates.setAdapter(mPreviousEventAdapter);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MyEvent> call, Throwable t) {
                showToast(getContext(), "onFailure"+ t.getMessage());
                hideLoading();
            }
        });
    }

    private void makePhoneCall(String phoneNumber) {
        if (phoneNumber.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + phoneNumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(getContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(mSalonNumber);
            } else {
                Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_MyEventNewBooking, R.id.BTN_MyEventNearbySalon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_MyEventNewBooking:
                break;

            case R.id.BTN_MyEventNearbySalon:
                break;
        }
    }

    public void showLoading()
    {
        this.progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading()
    {
        this.progress_view.setVisibility(View.GONE);
    }
}
