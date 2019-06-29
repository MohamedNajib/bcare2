package com.emedia.bcare.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emedia.bcare.ui.fragment.SectionsFragment;
import com.emedia.bcare.ui.fragment.SpecialistsFragment;
import com.emedia.bcare.ui.fragment.user_cycle.ForgotPassword1;
import com.emedia.bcare.ui.fragment.user_cycle.ForgotPassword2;
import com.emedia.bcare.ui.fragment.user_cycle.ForgotPassword3;
import com.emedia.bcare.ui.fragment.user_cycle.LoginFragment1;
import com.emedia.bcare.ui.fragment.user_cycle.LoginFragment2;
import com.emedia.bcare.ui.fragment.user_cycle.SignUpFragment;
import com.emedia.bcare.ui.fragment.user_cycle.SignUpFragment1;
import com.emedia.bcare.util.HelperMethod;
import com.emedia.bcare.R;
import com.emedia.bcare.ui.fragment.SelectSalonFragment;

public class UserCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        System.out.println("ret act name : USERCYCLEACTIVITY");

        //startActivity(new Intent(this, BottomNavActivity.class));
        //startActivity(new Intent(this, SalonActivity.class));
        //startActivity(new Intent(this, BookingActivity.class));


        HelperMethod.replaceFragments(new LoginFragment1(), getSupportFragmentManager(), R.id.FragmentContainer);
        //HelperMethod.replaceFragments(new SpecialistsFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }


}
