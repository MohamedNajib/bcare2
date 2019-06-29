package com.emedia.bcare.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emedia.bcare.R;
import com.emedia.bcare.data.model.api_model.booking.MapModel;
import com.emedia.bcare.ui.activity.HomeActivity;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.EditTextCustomFont;
import com.example.fontutil.TextViewCustomFont;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MapFragment extends Fragment {

    Unbinder unbinder;;

    @BindView(R.id.cl_first_popup)
    ConstraintLayout cl_first_popup;
    @BindView(R.id.cl_second_popup)
    ConstraintLayout cl_second_popup;

    @BindView(R.id.tv_address_location)
    TextViewCustomFont tv_address_location;
    @BindView(R.id.et_address)
    EditTextCustomFont et_address;
    @BindView(R.id.bt_choose)
    ButtonCustomFont bt_choose;
    @BindView(R.id.bt_pin)
    ButtonCustomFont bt_pin;


    @BindView(R.id.ed_building)
    EditTextCustomFont ed_building;
    @BindView(R.id.ed_flat)
    EditTextCustomFont ed_flat;
    @BindView(R.id.ed_landmark)
    EditTextCustomFont ed_landmark;

    @BindView(R.id.bt_confirm)
    ButtonCustomFont bt_confirm;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initilaize();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                setLatLng(mMap);

                initPinMap(mMap);
            }
        });


        return rootView;
    }

    public void initilaize()
    {
        cl_first_popup.setVisibility(View.VISIBLE);
        cl_second_popup.setVisibility(View.GONE);
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    Double lat;
    Double lng;

    public void setLatLng(final GoogleMap map)
    {

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }


        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lng = location.getLongitude();

                if (lat == 0.0) {
                    //lat = -1;
                    //lng = -1;
                } else {
                    if(getActivity() != null)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
                                try {
                                    List<Address> addresses = geoCoder.getFromLocation(lat, lng, 5);
                                    ArrayList<String> addressFragments = new ArrayList<String>();
                                    Double _lat = (double) (addresses.get(0).getLatitude());
                                    Double _lon = (double) (addresses.get(0).getLongitude());
                                    Log.d("lat-long", "" + _lat + "......." + _lon);
                                    lat = _lat;
                                    lng = _lon;
                                    final LatLng user = new LatLng(lat, lng);

                                    /*used marker for show the location */
                                    map.clear();
                                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(_lat, _lon), 18.0f));
                                    map.addMarker(new MarkerOptions()
                                            .position(new LatLng(lat, lng))
                                            .title("Spider Man")
                                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_path_location)));
                                            .icon(bitmapDescriptorFromVector(((HomeActivity) getActivity()).getApplicationContext(),
                                                    R.drawable.ic_address_icon)));


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Status Changed", String.valueOf(status));
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Provider Enabled", provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Provider Disabled", provider);
            }
        };

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        LocationManager locationManager = (LocationManager) getActivity().getApplicationContext().
                getSystemService(Context.LOCATION_SERVICE);
        Looper looper = null;
        locationManager.requestSingleUpdate(criteria, locationListener, looper);

    }

    public void initPinMap(GoogleMap mMap)
    {
        //== Camera Change Listener
        //====================================================================================
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                changeLocationAccordingToCamera(cameraPosition.target.latitude, cameraPosition.target.longitude);
            }
        });
    }

    String address = "";
    //== ChangeLocation According To Camera
    public void changeLocationAccordingToCamera(final double _lat, final double _lng) {
        lat = _lat;
        lng = _lng;
        address = "";
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
                Log.d("MAP", "1");
                try {
                    //Log.d("MAP", ed_search_map.getText().toString());
                    List<Address> addresses = geoCoder.getFromLocation(lat, lng, 20);
                    Log.d("MAP", "1_1");
                    ArrayList<String> addressFragments = new ArrayList<String>();
                    Log.d("MAP", "2");
                    if (addresses.size() > 0) {
                        for (int i = 0; i <= addresses.get(0).getMaxAddressLineIndex(); i++) {
                            address += " " + addresses.get(0).getAddressLine(i);
                        }
                        tv_address_location.setText(address);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("MAP", e.toString());
                }
            }
        });
    }

    @OnClick(R.id.bt_pin)
    protected void pin()
    {
        mapModel.setAddressPnned(tv_address_location.getText().toString().trim());
        mapModel.setLng(String.valueOf(lat));
        mapModel.setLng(String.valueOf(lng));
        mapModel.setFlat("");
        mapModel.setBuilding("");
        mapModel.setLandmark("");

        //To Confirm Screen
        ((HomeActivity) getActivity()).setFromMap();
        ((HomeActivity) getActivity()).changeFragment(6);
    }

    @OnClick(R.id.bt_choose)
    protected void enterAddress()
    {
        cl_second_popup.setVisibility(View.VISIBLE);
    }

    MapModel mapModel = new MapModel();
    @OnClick(R.id.bt_confirm)
    protected void confirmAddres()
    {
        if(! ed_building.getText().toString().trim().equals("")
                && ! ed_flat.getText().toString().trim().equals("")
                && ! ed_landmark.getText().toString().trim().equals(""))
        {
            mapModel.setAddressPnned(tv_address_location.getText().toString().trim());
            mapModel.setLng(String.valueOf(lat));
            mapModel.setLng(String.valueOf(lng));
            mapModel.setFlat( ed_flat.getText().toString().trim());
            mapModel.setBuilding(ed_building.getText().toString().trim());
            mapModel.setLandmark(ed_landmark.getText().toString().trim());

            ((HomeActivity) getActivity()).setMapModel(mapModel);

            //To Confirm Screen
            ((HomeActivity) getActivity()).setFromMap();
            ((HomeActivity) getActivity()).changeFragment(6);
        }
        else
        {
            cl_second_popup.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.IV_SelectSalonPackIcon)
    void back()
    {
        ((HomeActivity) getActivity()).onBackPressed();
    }
}