package com.emedia.bcare.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;
import com.emedia.bcare.ui.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceProviderHomeFragment extends Fragment {



    Unbinder unbinder;

    public ServiceProviderHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_provider_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initialize();
        return view;
    }

    public void initialize() {
        ((HomeActivity) getActivity()).showBottomToolbar();
        ((HomeActivity) getActivity()).setToolbar(((HomeActivity) getActivity()).getResources().getString(R.string.welcome));
        ((HomeActivity) getActivity()).setSupToolbar(((HomeActivity) getActivity()).getResources().getString(R.string.The_application_combines));
        ((HomeActivity) getActivity()).setEventTitle(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
