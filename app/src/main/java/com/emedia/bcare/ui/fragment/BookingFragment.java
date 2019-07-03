package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.emedia.bcare.R;
import com.emedia.bcare.adapter.DatesRecyclerViewAdapter;
import com.emedia.bcare.adapter.ViewPagerTapsAdapter;
import com.emedia.bcare.data.model.DateTimePickerModel;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.ui.fragment.booking_taps.AtHomeBookingTap;
import com.emedia.bcare.ui.fragment.booking_taps.InTheShopBookingTap;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    @BindView(R.id.progress_view)
    ProgressBar progress_view;

    @BindView(R.id.RecyclerViewDate)
    RecyclerView RecyclerViewDate;

    @BindView(R.id.BookingTabLayout)
    TabLayout BookingTabLayout;
    @BindView(R.id.BookingViewPager)
    ViewPager BookingViewPager;

    @BindView(R.id.tv_year_month)
    TextViewCustomFont tv_year_month;

    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;

    private List<DateTimePickerModel> mDataModelList;
    private DatesRecyclerViewAdapter mDatesRecyclerViewAdapter;

    Unbinder unbinder;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_booking, container, false);
        //View view = inflater.inflate(R.layout.fragment_booking, container, false);
        unbinder = ButterKnife.bind(this, view);
        /* Set ViewPager */
        setupClientViewPager(BookingViewPager);
        BookingTabLayout.setupWithViewPager(BookingViewPager);

        ImageView iv_back = view.findViewById(R.id.iv_back);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            ivLeft.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            ivRight.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            iv_back.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            ivLeft.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            ivRight.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            iv_back.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        initialize();

        Calendar c = Calendar.getInstance();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String month = monthName[c.get(Calendar.MONTH)];

        int year = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DATE);

        //Calendar cal = Calendar.getInstance();
        //SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        //String month_name = month_date.format(cal.getTime());

        addData();
        Calendar cal = Calendar.getInstance();
        addDaysByMonth(cal.get(Calendar.MONTH));

        RecyclerViewDate.setHasFixedSize(true);
        RecyclerViewDate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mDatesRecyclerViewAdapter = new DatesRecyclerViewAdapter(mDataModelList, getActivity(), new DatesRecyclerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(DateTimePickerModel model) {

                System.out.println("ret booking day 2 : " + model.date);
                updateDate(model.date);

                for (int i = 0; i < mDataModelList.size(); i++) {

                    DateTimePickerModel current = mDataModelList.get(i);
                    mDataModelList.get(i).highlghted = model.day.equals(current.day) && model.date.equals(current.date);
                }
                mDatesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        RecyclerViewDate.setAdapter(mDatesRecyclerViewAdapter);

        return view;
    }

    /* Add Fragments to Tabs */
    private void setupClientViewPager(ViewPager viewPager) {
        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new InTheShopBookingTap(), ((HomeActivity) getActivity()).getResources().getString(R.string.at_salon));
        adapter.addFragment(new AtHomeBookingTap(), ((HomeActivity) getActivity()).getResources().getString(R.string.at_home));

        viewPager.setAdapter(adapter);
    }


    int minIndex = 0;
    int maxIndex = 12;
    int startIndex = 0;

    int minIndexDay = 0;
    int minIndexYear = 0;

    //int minIndexYear = 0;

    private void addData() {

        Calendar cal = Calendar.getInstance();

        minIndexDay = cal.get(Calendar.DAY_OF_MONTH);
        minIndexYear = cal.get(Calendar.YEAR);
        minIndex = cal.get(Calendar.MONTH);
        startIndex = cal.get(Calendar.MONTH) + 1;
        yearName = String.valueOf(cal.get(Calendar.YEAR));

        switch (startIndex)
        {
            case 1 : monthName = "يناير";
                break;
            case 2 : monthName = "فبراير";
                break;
            case 3 : monthName = "مارس";
                break;
            case 4 : monthName = "ابريل";
                break;
            case 5 : monthName = "مايو";
                break;
            case 6 : monthName = "يونيو";
                break;
            case 7 : monthName = "يوليو";
                break;
            case 8 : monthName = "اغسطس";
                break;
            case 9 : monthName = "سبتمبر";
                break;
            case 10 : monthName = "اكتوبر";
                break;
            case 11 : monthName = "نوفمبر";
                break;
            case 12 : monthName = "ديسمبر";
                break;
        }

        ((HomeActivity) getActivity()).setMonthName(monthName);

        tv_year_month.setText( String.valueOf(cal.get(Calendar.YEAR)) + ", " + monthName);

        //addDaysByMonth(cal.get(Calendar.MONTH));
        //    mDataModelList.add(dataModel);


        //mDataModelList = new ArrayList<>();
        //Calendar c = Calendar.getInstance();
//
        //for (int i = 1; i < 31; i++) {
        //    c.add(Calendar.DAY_OF_YEAR, 1);
        //    Date tomorrow = c.getTime();
//
        //    long timestamp = tomorrow.getTime();
        //    Calendar cal = Calendar.getInstance();
        //    cal.setTimeInMillis(timestamp);
//
        //    DateTimePickerModel dataModel = new DateTimePickerModel();
        //    switch (cal.get(Calendar.DAY_OF_WEEK)) {
        //        case Calendar.SUNDAY:
        //            dataModel.day = "Su";
        //            break;
        //        case Calendar.MONDAY:
        //            dataModel.day = "Mo";
        //            break;
        //        case Calendar.TUESDAY:
        //            dataModel.day = "Tu";
        //            break;
        //        case Calendar.WEDNESDAY:
        //            dataModel.day = "We";
        //            break;
        //        case Calendar.THURSDAY:
        //            dataModel.day = "Th";
        //            break;
        //        case Calendar.FRIDAY:
        //            dataModel.day = "Fr";
        //            break;
        //        case Calendar.SATURDAY:
        //            dataModel.day = "Sa";
        //            break;
        //    }
//
        //    dataModel.date = String.format("%02d", (cal.get(Calendar.DATE)));
        //    dataModel.month = String.format("%02d", (cal.get(Calendar.MONTH)));
        //    dataModel.year = String.valueOf(cal.get(Calendar.YEAR));
        //    dataModel.highlghted = i == 1;
//
        //    System.out.println("ret month minIndex : " + cal.get(Calendar.MONTH));
        //    System.out.println("ret month startIndex : " + cal.get(Calendar.MONTH));
        //    System.out.println("ret day DAY_OF_MONTH : " + cal.get(Calendar.DAY_OF_MONTH));
        //    System.out.println("ret year YEar : " + cal.get(Calendar.YEAR));
//
        //    minIndexDay = cal.get(Calendar.DAY_OF_MONTH);
        //    minIndexYear = cal.get(Calendar.YEAR);
        //    minIndex = cal.get(Calendar.MONTH);
        //    startIndex = cal.get(Calendar.MONTH);
        //    yearName = String.valueOf(cal.get(Calendar.YEAR));
//
        //    //TODO
//
        //    switch (startIndex)
        //    {
        //        case 1 : monthName = "يناير";
        //            break;
        //        case 2 : monthName = "فبراير";
        //            break;
        //        case 3 : monthName = "مارس";
        //            break;
        //        case 4 : monthName = "ابريل";
        //            break;
        //        case 5 : monthName = "مايو";
        //            break;
        //        case 6 : monthName = "يونيو";
        //            break;
        //        case 7 : monthName = "يوليو";
        //            break;
        //        case 8 : monthName = "اغسطس";
        //            break;
        //        case 9 : monthName = "سبتمبر";
        //            break;
        //        case 10 : monthName = "اكتوبر";
        //            break;
        //        case 11 : monthName = "نوفمبر";
        //            break;
        //        case 12 : monthName = "ديسمبر";
        //            break;
//
        //    }
//
        //    tv_year_month.setText( String.valueOf(cal.get(Calendar.YEAR)) + ", " + monthName);
        //    mDataModelList.add(dataModel);
        //}

    }


    public void initialize()
    {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }


    String monthName ;
    String yearName;

    @OnClick(R.id.iv_left)
    protected void decrement()
    {
        System.out.println("ret decrement : " + startIndex +" min : " + minIndex);
        if(startIndex - 1 >= minIndex)
        {
            startIndex = startIndex - 1;
            Calendar instance = Calendar.getInstance();

            //tv_year_month.setText( String.valueOf(cal.get(Calendar.YEAR)) + ", " + String.format("%02d", (cal.get(Calendar.MONTH))));

            switch (startIndex)
            {
                case 1 : monthName = "يناير";
                    break;
                case 2 : monthName = "فبراير";
                    break;
                case 3 : monthName = "مارس";
                    break;
                case 4 : monthName = "ابريل";
                    break;
                case 5 : monthName = "مايو";
                    break;
                case 6 : monthName = "يونيو";
                    break;
                case 7 : monthName = "يوليو";
                    break;
                case 8 : monthName = "اغسطس";
                    break;
                case 9 : monthName = "سبتمبر";
                    break;
                case 10 : monthName = "اكتوبر";
                    break;
                case 11 : monthName = "نوفمبر";
                    break;
                case 12 : monthName = "ديسمبر";
                    break;

            }

            ((HomeActivity) getActivity()).setMonthName(monthName);
            tv_year_month.setText( yearName + ", " + monthName);

            addDaysByMonth(startIndex);
        }
    }

    @OnClick(R.id.iv_right)
    protected void increment()
    {
        System.out.println("ret incremetn : " + startIndex +" min : " + maxIndex);

        if(startIndex + 1 < maxIndex)
        {
            startIndex = startIndex + 1;
            Calendar instance = Calendar.getInstance();

            //tv_year_month.setText( String.valueOf(cal.get(Calendar.YEAR)) + ", " + String.format("%02d", (cal.get(Calendar.MONTH))));

            switch (startIndex)
            {
                case 1 : monthName = "يناير";
                    break;
                case 2 : monthName = "فبراير";
                    break;
                case 3 : monthName = "مارس";
                    break;
                case 4 : monthName = "ابريل";
                    break;
                case 5 : monthName = "مايو";
                    break;
                case 6 : monthName = "يونيو";
                case 7 : monthName = "يوليو";
                    break;
                case 8 : monthName = "اغسطس";
                    break;
                case 9 : monthName = "سبتمبر";
                    break;
                case 10 : monthName = "اكتوبر";
                    break;
                case 11 : monthName = "نوفمبر";
                    break;
                case 12 : monthName = "ديسمبر";
                    break;

            }

            ((HomeActivity) getActivity()).setMonthName(monthName);
            tv_year_month.setText( yearName + ", " + monthName);
            addDaysByMonth(startIndex);
        }
    }



    private void addDaysByMonth(int month) {

        mDataModelList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        c.set(month, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);

        System.out.println("ret addDaysByMonth : " + month +
                " - " + " c.getActualMaximum(Calendar.DAY_OF_MONTH) : " +
                c.getActualMaximum(Calendar.DAY_OF_MONTH));

        for (int i = 1; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            c.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = c.getTime();

            long timestamp = tomorrow.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);

            DateTimePickerModel dataModel = new DateTimePickerModel();
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    dataModel.day = "Su";
                    break;
                case Calendar.MONDAY:
                    dataModel.day = "Mo";
                    break;
                case Calendar.TUESDAY:
                    dataModel.day = "Tu";
                    break;
                case Calendar.WEDNESDAY:
                    dataModel.day = "We";
                    break;
                case Calendar.THURSDAY:
                    dataModel.day = "Th";
                    break;
                case Calendar.FRIDAY:
                    dataModel.day = "Fr";
                    break;
                case Calendar.SATURDAY:
                    dataModel.day = "Sa";
                    break;
            }

            dataModel.date = String.format("%02d", (cal.get(Calendar.DATE)));
            dataModel.month = String.format("%02d", (cal.get(Calendar.MONTH)));
            dataModel.year = String.valueOf(cal.get(Calendar.YEAR));
            dataModel.highlghted = i == 1;

            //TODO

            mDataModelList.add(dataModel);
        }

        mDatesRecyclerViewAdapter = new DatesRecyclerViewAdapter(mDataModelList, getActivity(), new DatesRecyclerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(DateTimePickerModel model) {
                for (int i = 0; i < mDataModelList.size(); i++) {

                    DateTimePickerModel current = mDataModelList.get(i);
                    mDataModelList.get(i).highlghted = model.day.equals(current.day) && model.date.equals(current.date);
                }
                mDatesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        RecyclerViewDate.setAdapter(mDatesRecyclerViewAdapter);

    }

    String monthNum = "";
    protected void updateDate(String day)
    {
        System.out.println("ret booking day : " + day);

        if(startIndex == 1)
            monthNum = "01";
        else if (startIndex == 2)
            monthNum = "02";
        else if (startIndex == 3)
            monthNum = "03";
        else if (startIndex == 4)
            monthNum = "02";
        else if (startIndex == 2)
            monthNum = "04";
        else if (startIndex == 5)
            monthNum = "05";
        else if (startIndex == 6)
            monthNum = "06";
        else if (startIndex == 7)
            monthNum = "07";
        else if (startIndex == 8)
            monthNum = "08";
        else if (startIndex == 9)
            monthNum = "09";
        else if (startIndex == 10)
            monthNum = "10";
        else if (startIndex == 11)
            monthNum = "11";
        else if (startIndex == 12)
            monthNum = "12";

        ((HomeActivity) getActivity()).setDayName(day);
        ((HomeActivity) getActivity()).setReserveDate(yearName + "-" + monthNum + "-" + day);
    }
}
