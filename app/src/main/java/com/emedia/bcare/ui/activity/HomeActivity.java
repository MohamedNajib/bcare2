package com.emedia.bcare.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.Config.ContextWrapper;
import com.emedia.bcare.R;
import com.emedia.bcare.cash.SharedUser;
import com.emedia.bcare.data.model.api_model.booking.GetReserve;
import com.emedia.bcare.data.model.api_model.booking.MapModel;
import com.emedia.bcare.data.model.api_model.booking.Specialist_;
import com.emedia.bcare.data.model.api_model.booking.TimeAtHome;
import com.emedia.bcare.data.model.api_model.booking.TimeAtSalon;
import com.emedia.bcare.data.model.api_model.salons.SalonData;
import com.emedia.bcare.data.model.logoutUser.LogoutUser;
import com.emedia.bcare.data.rest.RetrofitClient;
import com.emedia.bcare.ui.fragment.AboutBcare;
import com.emedia.bcare.ui.fragment.BookingFragment;
import com.emedia.bcare.ui.fragment.ChatFragment;
import com.emedia.bcare.ui.fragment.ConfirmationFragment;
import com.emedia.bcare.ui.fragment.DiscountCode;
import com.emedia.bcare.ui.fragment.DiscountCodeFragment;
import com.emedia.bcare.ui.fragment.GenderFragment;
import com.emedia.bcare.ui.fragment.MapFragment;
import com.emedia.bcare.ui.fragment.MessagesFragment;
import com.emedia.bcare.ui.fragment.NotificationFragment;
import com.emedia.bcare.ui.fragment.PointsFragment;
import com.emedia.bcare.ui.fragment.ReviewsSpecialFragment;
import com.emedia.bcare.ui.fragment.SalonServicesFragment;
import com.emedia.bcare.ui.fragment.SectionsFragment;
import com.emedia.bcare.ui.fragment.SelectSalonFragment;
import com.emedia.bcare.ui.fragment.ServiceProviderHomeFragment;
import com.emedia.bcare.ui.fragment.SpecialistsFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.MyEventFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.ProfileFragment;
import com.emedia.bcare.ui.fragment.bottom_nav.UserHomeFragment;
import com.emedia.bcare.ui.fragment.SalonFragment;
import com.emedia.bcare.util.HelperMethod;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;
import java.util.Locale;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.emedia.bcare.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.emedia.bcare.util.HelperMethod.intentTo;
import static com.emedia.bcare.util.HelperMethod.replaceFragments;
import static com.emedia.bcare.util.HelperMethod.showToast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FrameLayout FrameLayoutFragmentContainer;
    FrameLayout fragmentContainerA;
    BottomNavigationView BottomNavView;
    private DrawerLayout drawerMain;
    private NavigationView navigationViewMAin;
    ImageView iv_drawer_menu;
    boolean drawer_open = false;
    ImageView imageView;
    Toolbar toolbar;
    TextView tvToolbar;

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
                case R.id.control_Board:
                    fragment = new ServiceProviderHomeFragment();
                    break;
            }
            replaceFragments(fragment, getSupportFragmentManager(), R.id.fragmentContainerA);
            return true;
        }
    };

    public void heden() {
        // Toolbar toolbar = findViewById(R.id.toolbar);
        // fragmentContainerA = findViewById(R.id.fragmentContainerA);
        // FrameLayoutFragmentContainer = findViewById(R.id.FrameLayoutFragment_Container);
        // BottomNavView = findViewById(R.id.Bottom_nav_view);
        // BottomNavView.setVisibility(View.GONE);
        // FrameLayoutFragmentContainer.setVisibility(View.GONE);
        // fragmentContainerA.setVisibility(View.VISIBLE);
        // toolbar.setVisibility(View.GONE);
    }

    public void show() {
        // Toolbar toolbar = findViewById(R.id.toolbar);
        // fragmentContainerA = findViewById(R.id.fragmentContainerA);
        // FrameLayoutFragmentContainer = findViewById(R.id.FrameLayoutFragment_Container);
        // BottomNavView = findViewById(R.id.Bottom_nav_view);
        // BottomNavView.setVisibility(View.VISIBLE);
        // FrameLayoutFragmentContainer.setVisibility(View.VISIBLE);
        // fragmentContainerA.setVisibility(View.VISIBLE);
        // toolbar.setVisibility(View.VISIBLE);

    }

    public void showBottomToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        BottomNavView = findViewById(R.id.Bottom_nav_view);
        BottomNavView.setVisibility(View.VISIBLE);
    }

    public void hideBottomToolbar() {
        toolbar.setVisibility(View.GONE);
        BottomNavView = findViewById(R.id.Bottom_nav_view);
        BottomNavView.setVisibility(View.GONE);
    }
    private TextViewCustomFont supTitle;
    public void setSupToolbar(String title) {
        supTitle  = findViewById(R.id.ToolBar_SupTitle1);

        if (title == null){
            supTitle.setVisibility(View.GONE);
        }else {
            supTitle.setVisibility(View.VISIBLE);
            supTitle.setText(title);
        }
    }

    public void setToolbar(String title) {
        if (title == null){
            tvToolbar.setVisibility(View.GONE);
        }else {
            tvToolbar = findViewById(R.id.textViewCustomFont2);
            tvToolbar.setText(title);
            tvToolbar.setVisibility(View.VISIBLE);
        }
    }
    ConstraintLayout eventConstraintLayout;
    private TextViewCustomFont eventTitle;
    public void setEventTitle(String title){
        eventConstraintLayout = findViewById(R.id.CL_EventTitle);
        eventTitle = findViewById(R.id.EventTitle);
        if (title == null){
            eventConstraintLayout.setVisibility(View.GONE);
        }else {
            eventConstraintLayout.setVisibility(View.VISIBLE);
            eventTitle.setText(title);
        }
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_navigation);
        //ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);

        BottomNavigationView navView = findViewById(R.id.Bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.findViewById(R.id.navIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open right drawer
                drawer.openDrawer(GravityCompat.START);
            }
        });

        ImageView imageView = drawer.findViewById(R.id.IV_NotificationIcon);
        ImageView imageView1 = drawer.findViewById(R.id.IV_MessageIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(7);
                //heden();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(5);
                //heden();
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout logoutNavL = navigationView.findViewById(R.id.LL_LogoutNav);
        ImageView signOutImage = navigationView.findViewById(R.id.IV_Logout);
        if (Locale.getDefault().getLanguage().equals("ar")) {
            signOutImage.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        } else {
            signOutImage.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        }

        logoutNavL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser(SharedUser.getSharedUser().getToken(), Integer.valueOf(SharedUser.getSharedUser().getUserId()));
            }
        });

    }

    /**
     * Sign Out User Api Call
     */
    private void logoutUser(String token, int userId) {
        Call<LogoutUser> logoutUserCall = RetrofitClient.getInstance().getApiServices().logoutUser(token, userId);
        logoutUserCall.enqueue(new Callback<LogoutUser>() {
            @Override
            public void onResponse(Call<LogoutUser> call, Response<LogoutUser> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        HelperMethod.showToast(HomeActivity.this, "Success: " + response.body().getData());
                        SharedUser.getSharedUser().clear();
                        Intent toLogin = new Intent(HomeActivity.this, LoginMainActivity.class);
                        toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(toLogin);
                        finish();
                    } else {
                        Toast.makeText(BCareApp.getInstance().getContext(), "Error: " + response.body().getCode()
                                + response.body().getData(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LogoutUser> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerA);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (currentFragment instanceof UserHomeFragment) {
            BCareApp.getInstance().showExitDialogue(this);
            return;
        } else if (currentFragment instanceof SalonServicesFragment) {
            //Back To select salon
            changeFragment(2);
        } else if (currentFragment instanceof ConfirmationFragment) {
            //Back To map
            changeFragment(16);
        } else if (currentFragment instanceof SelectSalonFragment) {
            //Back To Salon Services
            intentTo(this, GenderActivity.class);
            //changeFragment(0);
        } else if (currentFragment instanceof BookingFragment) {
            //Back To Salon Services
            changeFragment(3);
        } else if (currentFragment instanceof MyEventFragment) {
            changeFragment(1);
        } else if (currentFragment instanceof SalonFragment) {
            //selcet salon
            changeFragment(2);
        } else if (currentFragment instanceof MapFragment) {
            //to booking
            changeFragment(4);
        } else if (currentFragment instanceof SectionsFragment) {
            //to booking
            changeFragment(21);
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.services) {
            changeFragment(1);
            heden();
        } else if (id == R.id.favorite) {


        } else if (id == R.id.points) {
            changeFragment(19);
            heden();
        } else if (id == R.id.call_us) {

        } else if (id == R.id.about_bcare) {
            changeFragment(20);
            heden();

        } else if (id == R.id.change_language) {
            //changeFragment(21);
            BCareApp.getLanguage().setLang(this, getResources().getString(R.string.lang));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFragment(int fraId) {

        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (fraId) {
//            case 0:
//                fragment = new GenderFragment();
//                //fragment = new SalonServicesFragment();
//                break;
            case 1:
                fragment = new SectionsFragment();
                //fragment = new SelectSalonFragment();
                break;
            case 2:
                fragment = new SelectSalonFragment();
                //fragment = new SectionsFragment();
                break;
            case 3:
                fragment = new SalonServicesFragment();
                //fragment = new BookingFragment();
                break;
            case 4:
                fragment = new BookingFragment();
                //fragment = new ChatFragment();
                break;
            case 5:
                fragment = new ChatFragment();
                //fragment = new GenderFragment();
                break;
            case 6:
                fragment = new ConfirmationFragment();
                break;
            case 7:
                fragment = new NotificationFragment();
                break;
            case 8:
                fragment = new ReviewsSpecialFragment();
                break;
            case 9:
                fragment = new MessagesFragment();
                break;
            case 10:
                fragment = new DiscountCodeFragment();
                break;
            case 11:
                fragment = new ServiceProviderHomeFragment();
                break;
            case 12:
                fragment = new MyEventFragment();
                break;
            case 13:
                fragment = new SpecialistsFragment();
                break;
            case 14:
                fragment = new ReviewsSpecialFragment();
                break;
            case 15:
                fragment = new UserHomeFragment();
                break;
            case 16:
                fragment = new MapFragment();
                break;
            case 17:
                fragment = new DiscountCode();
                break;
            case 18:
                fragment = new DiscountCodeFragment();
                break;
            case 19:
                fragment = new PointsFragment();
                show();
                break;
            case 20:
                fragment = new AboutBcare();
                break;
            case 21:
                fragment = new UserHomeFragment();
                show();
                break;
            case 22:
                fragment = new SalonFragment();
                show();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainerA, fragment);
            ft.commit();
        }
    }

    @OnClick(R.id.iv_back)
    public void goBack() {
        onBackPressed();
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }

    private SearchView.OnQueryTextListener searchQueryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void HideMenu() {
        iv_drawer_menu.setVisibility(View.GONE);
        //iv_back.setVisibility(View.VISIBLE);
    }

    public void ShowMenu() {
        iv_drawer_menu.setVisibility(View.VISIBLE);
        //iv_back.setVisibility(View.GONE);
    }

    List<String> listOfCats;

    public void setListOfCats(List<String> cats) {
        this.listOfCats = cats;
    }

    public List<String> getListOfCats() {
        return listOfCats;
    }

    public GetReserve getReserve = new GetReserve();

    public void setGetReserveSalon(Specialist_ specialist_, String date, TimeAtSalon timeAtSalon, TimeAtHome timeAtHome) {
        getReserve.setToken(SharedUser.getSharedUser().getToken());
        //getReserve.setToken("Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp");
        getReserve.setLang(getResources().getString(R.string.current_lang));
        if (timeAtSalon != null)
            getReserve.setReservation_date(date + " " + timeAtSalon.getTime());

        if (timeAtHome != null)
            getReserve.setReservation_date(date + " " + timeAtHome.getTime());
        getReserve.setSpecialist_id(specialist_.getSpecialistId());
    }

    public GetReserve getGetReserve() {
        return getReserve;
    }

    public MapModel mapModel = new MapModel();

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
        //mapModel.setAddressPnned(address);
        //mapModel.setLat(lat);
        //mapModel.setLng(lng);
        //mapModel.setBuilding(building);
        //mapModel.setLandmark(landmark);
        //mapModel.setFlat(flat);
    }

    public MapModel getMapModel() {
        return this.mapModel;
    }

    boolean fromMap = false;

    public void setFromMap() {
        fromMap = true;
    }

    public boolean getFromMap() {
        return fromMap;
    }

    SalonData salon = new SalonData();

    public void setSalon(SalonData salon) {
        this.salon = salon;
    }

    public SalonData getSalon() {
        return salon;
    }

    String reserveDate;

    public void setReserveDate(String date) {
        System.out.println("ret date : " + date);
        this.reserveDate = date;
    }

    public String getReserveDate() {
        return this.reserveDate;
    }

    String reserveTime;

    public void setReserveTime(String time) {
        System.out.println("ret time : " + time);
        this.reserveTime = time;
    }

    public String getReserveTime() {
        return this.reserveTime;
    }

    String monthName;

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getMonthName() {
        return monthName;
    }

    String dayName;

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }


}
