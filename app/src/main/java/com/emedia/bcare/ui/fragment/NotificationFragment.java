package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emedia.bcare.R;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.TextViewCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.emedia.bcare.util.HelperMethod.intentTo;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    Unbinder unbinder;
    private TextViewCustomFont mToolBarTitle;
    private ImageView mToolBarIconBack;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar sectionsBar = view.findViewById(R.id.NotificationToolBar);
        mToolBarTitle = sectionsBar.findViewById(R.id.TV_TitleTB);
        mToolBarIconBack = sectionsBar.findViewById(R.id.IV_BackTB);

        mToolBarTitle.setText(((HomeActivity) getActivity()).getResources().getString(R.string.Notifications));
        mToolBarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentTo((HomeActivity) getActivity(), HomeActivity.class);
            }
        });

        initialize();
        if (((HomeActivity) getActivity()).getResources().getString(R.string.current_lang).equals("ar")) {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            mToolBarIconBack.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }
        initialize();
        return view;
    }

    protected void initialize() {
        ((HomeActivity) getActivity()).hideBottomToolbar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
