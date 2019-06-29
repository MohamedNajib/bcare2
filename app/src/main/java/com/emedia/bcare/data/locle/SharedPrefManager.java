package com.emedia.bcare.data.locle;

import android.content.Context;
import android.content.SharedPreferences;

import static com.emedia.bcare.Constants.SharedKeys.POPUP_S;
import static com.emedia.bcare.Constants.SharedKeys.SHARED_PREF_NAME;


public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private Context mContext;

    private SharedPrefManager(Context context) {
        this.mContext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setPopUpState(String s){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POPUP_S, s);
        editor.apply();
    }

    public String getPopUpState(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(POPUP_S, null);
    }

//    public void setUserApiToken(String apiToken) {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(USER_API_TOKEN, apiToken);
//        editor.apply();
//    }
//
//    public String getUserApiToken() {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(USER_API_TOKEN, null);
//    }

    public void clare() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
