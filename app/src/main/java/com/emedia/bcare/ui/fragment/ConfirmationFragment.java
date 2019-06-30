package com.emedia.bcare.ui.fragment;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.fragments_adapter.SalonServicesAdapterB;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.data.model.salon_reserve.SalonReserve;
import com.emedia.bcare.data.rest.ApiServices;
import com.emedia.bcare.network.RequestSingletone;
import com.emedia.bcare.ui.activity.BookingActivity;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.EditTextCustomFont;
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

import static com.emedia.bcare.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment {

    @BindView(R.id.imageView4)
    ImageView imageView4;
    Unbinder unbinder;
    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @BindView(R.id.ET_DiscountCode)
    EditTextCustomFont ETDiscountCode;
    @BindView(R.id.TV_ServiceType)
    TextViewCustomFont TV_ServiceType;
    @BindView(R.id.TV_EventDate)
    TextViewCustomFont TVEventDare;
    @BindView(R.id.TV_PhoneNumber)
    TextViewCustomFont TVPhoneNumber;

    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.textViewCustomFont19)
    TextView tvSalonName;
    @BindView(R.id.textView4)
    TextView tvSalonAddress;
    @BindView(R.id.TV_Price)
    TextView TV_Price;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;

    @BindView(R.id.textViewCustomFont13)
    TextView tvMonth;
    @BindView(R.id.textViewCustomFont15)
    TextView tvDay;
    @BindView(R.id.tv_time)
    TextView tvTime;


    public ConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (((HomeActivity) getActivity()).getResources().getString(R.string.current_lang).equals("ar")) {
            imageView4.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            imageView4.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        initialize();
        //initialize();

        return view;
    }

    public void initialize()
    {
        //TV_Price.setText(((HomeActivity) getActivity()).getGetReserve().getPrice_after_discount());
        TV_Price.setText(((HomeActivity) getActivity()).getSalon().getMinPrice());
        tvMonth.setText(((HomeActivity) getActivity()).getMonthName());
        tvDay.setText(((HomeActivity) getActivity()).getDayName());
        tvTime.setText(((HomeActivity) getActivity()).getReserveTime());

        ETDiscountCode.setText("2");
        TVEventDare.setText(((HomeActivity) getActivity()).getGetReserve().getReservation_date());
        TV_ServiceType.setText(((HomeActivity) getActivity()).getSalon().getServiceName());
        TVPhoneNumber.setText(SharedUser.getSharedUser().getPhone());
    }

//    List<String> places;
//    protected void initialize()
//    {
//
//        if(((HomeActivity) getActivity()).getFromMap())
//        {
//            places = new ArrayList<String>();
//            places.add("فى مقر المحل");
//            places.add("فى المنزل");
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
//                    (getActivity(), android.R.layout.simple_spinner_item,
//                            places); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
//                    .simple_spinner_dropdown_item);
//            spinnerPlace.setAdapter(spinnerArrayAdapter);
//
//            //spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            //    @Override
//            //    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            //        if(spinnerPlace.getSelectedItem().toString().equals("فى المنزل"))
//            //        {
//            //            //Change to map
//            //            ((HomeActivity) getActivity()).changeFragment(16);
//            //        }
//            //    }
//
//            //    @Override
//            //    public void onNothingSelected(AdapterView<?> parent) {
//
//            //    }
//            //});
//
//            spinnerPlace.setSelection(1);
//
//            ETDiscountCode.setText("2");
//            TV_Price.setText(((HomeActivity) getActivity()).getSalon().getMinPrice());
//            ETEventDare.setText(((HomeActivity) getActivity()).getReserve.getReservation_date());
//
//        }
//        else
//        {
//            places = new ArrayList<String>();
//            places.add("فى مقر المحل");
//            places.add("فى المنزل");
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
//                    (getActivity(), android.R.layout.simple_spinner_item,
//                            places); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter.setDropDownViewResource(android.R.layout
//                    .simple_spinner_dropdown_item);
//            spinnerPlace.setAdapter(spinnerArrayAdapter);
//
//            spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if(spinnerPlace.getSelectedItem().toString().equals("فى المنزل"))
//                    {
//                        //Change to map
//                        ((HomeActivity) getActivity()).changeFragment(16);
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });
//
//            ETDiscountCode.setText("2");
//            TV_Price.setText(((HomeActivity) getActivity()).getSalon().getMinPrice());
//            ETEventDare.setText(((HomeActivity) getActivity()).getReserve.getReservation_date());
//        }
//
//    }

    /**
     * Reserve Request Salon Api Call
     * mTotalPrice && mServicesIdList >> Total Price And  List Of Services ID >>
     * {@link SalonServicesAdapterB}
     * <p>
     * mSalonId >> {@link SelectSalonFragment}
     */
    private void salonReserveApiCall() {

        showLoading();
        Call<SalonReserve> salonReserveCall = RequestSingletone.getInstance().getClient()
                .create(ApiServices.class).
                        homeReserve(SharedUser.getSharedUser().getToken(),
                                ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
                                ((HomeActivity) getActivity()).getSalon().getId(),
                                Float.parseFloat(((HomeActivity) getActivity()).getSalon().getMinPrice()),
                                ((HomeActivity) getActivity()).getReserveTime(),
                                ((HomeActivity) getActivity()).getReserveDate(),
                                SharedUser.getSharedUser().getName(),
                                SharedUser.getSharedUser().getPhone(),
                                ((HomeActivity) getActivity()).getReserve.getPlace(),
                                Integer.parseInt( ((HomeActivity) getActivity()).getReserve.getSpecialist_id()),
                                "1,2",
                                ((HomeActivity) getActivity()).getMapModel().getLat(),
                                ((HomeActivity) getActivity()).getMapModel().getLng(),
                                ((HomeActivity) getActivity()).getMapModel().getBuilding(),
                                ((HomeActivity) getActivity()).getMapModel().getFlat(),
                                ((HomeActivity) getActivity()).getMapModel().getLandmark());
        salonReserveCall.
                enqueue(new Callback<SalonReserve>() {
                    @Override
                    public void onResponse(Call<SalonReserve> call, Response<SalonReserve> response) {

                        hideLoading();

                        if (response.isSuccessful()) {
                            showToast(getContext(), "Congratulation");
                            showDialog();

                        } else {
                            showToast(getContext(), "NO");
                        }
                    }

                    @Override
                    public void onFailure(Call<SalonReserve> call, Throwable t) {
                        hideLoading();
                    }
                });


        //Call<SalonReserve> salonReserveCall = RequestSingletone.getInstance().getClient()
        //        .create(ApiServices.class).
        //                salonReserve(SharedUser.getSharedUser().getToken(),
        //                        ((HomeActivity) getActivity()).getResources().getString(R.string.current_lang),
        //                        ((HomeActivity) getActivity()).getSalon().getId(),
        //                        Float.parseFloat(((HomeActivity) getActivity()).getSalon().getMinPrice()),
        //                        0,
        //                        Float.parseFloat(((HomeActivity) getActivity()).getSalon().getMinPrice()),
        //                        ((HomeActivity) getActivity()).getReserve.getReservation_date(),
        //                        "",
        //                        TVPhoneNumber.getText().toString().trim(),
        //                        ((HomeActivity) getActivity()).getReserve.getPlace(),
        //                        //getmServicesIdList(),
        //                        ((HomeActivity) getActivity()).getListOfCats(),
        //                        ((HomeActivity) getActivity()).getMapModel().getLat(),
        //                        ((HomeActivity) getActivity()).getMapModel().getLng(),
        //                        ((HomeActivity) getActivity()).getMapModel().getBuilding(),
        //                        ((HomeActivity) getActivity()).getMapModel().getFlat(),
        //                        ((HomeActivity) getActivity()).getMapModel().getLandmark(),
        //                        Integer.parseInt( ((HomeActivity) getActivity()).getReserve.getSpecialist_id() )
        //                );
        //salonReserveCall.
        //        enqueue(new Callback<SalonReserve>() {
        //            @Override
        //            public void onResponse(Call<SalonReserve> call, Response<SalonReserve> response) {
        //                hideLoading();
        //                if (response.isSuccessful()) {
        //                    showToast(getContext(), "Congratulation");
        //                    showDialog();
        //                } else {
        //                    showToast(getContext(), "NO");
        //                }
        //            }
        //            @Override
        //            public void onFailure(Call<SalonReserve> call, Throwable t) {
        //                hideLoading();
        //            }
        //        });
    }


    /**
     * Dialog to Show if the Request is Successful
     */
    public void showDialog() {
        final Dialog dialog = new Dialog(getContext());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmatoin);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dialog)));
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        final ButtonCustomFont dialogConfirmationPtn = dialog.findViewById(R.id.BTN_DialogConfirmation);
        dialogConfirmationPtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Dialog Confirmation Button Event Listener
                dialog.dismiss();

                ((HomeActivity) getActivity()).changeFragment(12);
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_ConfirmationRequest)
    public void onViewClicked() {
        confirmSalonService();
    }

    @OnClick(R.id.imageView4)
    public void goBack() {
        ((HomeActivity) getActivity()).onBackPressed();
    }


    public void confirmSalonService() {
        //OnSuccess
        //showDialog();
        salonReserveApiCall();
    }


    public void showLoading() {
        progress_view.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progress_view.setVisibility(View.GONE);
    }

    @OnClick(R.id.imageView4)
    void back()
    {
        ((HomeActivity) getActivity()).onBackPressed();
    }
}
