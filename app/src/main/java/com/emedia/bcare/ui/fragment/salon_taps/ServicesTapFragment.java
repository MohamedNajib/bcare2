package com.emedia.bcare.ui.fragment.salon_taps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesTapFragment extends Fragment {


    public ServicesTapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services_tap, container, false);
    }

}
