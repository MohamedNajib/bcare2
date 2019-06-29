package com.emedia.bcare.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

public class MyLocationProvider {

    Location location;
    LocationManager locationManager;
    boolean canGetLocation;
    Context context;
    final static  long MIN_TIME_BETWEEN_UPDATES = 5*1000;
    final static  float MIN_DISTANCE_BETWEEN_UPDATES = 20.0f;

    public MyLocationProvider(Context context){
        this.context=context;
        locationManager= (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        canGetLocation=false;
        location=null;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        boolean isNetworkEnabled=false;
        boolean isGPSEnabled=false;
        isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!isNetworkEnabled&&!isGPSEnabled){
            canGetLocation=false;
            location=null;
            return;
        }
        String provider=null;
        if(isNetworkEnabled)provider=LocationManager.NETWORK_PROVIDER;
        if(isGPSEnabled) provider=LocationManager.GPS_PROVIDER;

        location= locationManager.getLastKnownLocation(provider);

        if(location== null){
            location=getBestLastknownLocation();
        }
    }

    @SuppressLint("MissingPermission")
    public Location getBestLastknownLocation(){
        List<String> providers=locationManager.getProviders(false);

        for (int i = 0; i < providers.size(); i++) {
            String provider = providers.get(i);
            Location temp=locationManager.getLastKnownLocation(provider);
            if(location==null){
                location=temp;
                continue;
            }
            //location.getTime()
            if(temp!=null){
                if(temp.getAccuracy()>location.getAccuracy()){
                    location=temp;
                }
            }
        }
        return location;
    }
}
