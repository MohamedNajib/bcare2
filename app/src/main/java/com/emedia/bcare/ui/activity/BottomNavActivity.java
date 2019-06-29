package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.ui.fragment.ConfirmationFragment;
import com.emedia.bcare.ui.fragment.ReviewsSpecialFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.MyEventFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.ProfileFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.UserHomeFragment;

import static com.emedia.bcare.util.HelperMethod.replaceFragments;

public class BottomNavActivity extends AppCompatActivity {
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment = null;


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    fragment = new UserHomeFragment();
                    break;

                case R.id.navigation_time:
                    fragment = new MyEventFragment();
                    break;

                case R.id.navigation_account:
                    fragment = new ProfileFragment();
                    break;
            }
            replaceFragments(fragment, getSupportFragmentManager(), R.id.FrameLayoutFragment_Container);

            return true;
        }
    };



}
